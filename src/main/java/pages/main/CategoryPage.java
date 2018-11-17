package pages.main;

import factory.PageObjectFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.summary.CheckoutPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.WebElementManipulatorAndGenerator.generateRandomIntervalNumber;

public class CategoryPage extends BasePage {
    final static Logger logger = Logger.getLogger(CategoryPage.class);

    @FindBy(className = "wpsc_product_title")
    protected List<WebElement> products;

    @FindBy(xpath = "//*[@id='header_cart']/a")
    WebElement checkout;


    public CategoryPage(WebDriver webDriver, Actions actions, WebDriverWait waitDriver) {
        super(webDriver, actions, waitDriver);
        PageFactory.initElements(webDriver, this);
    }

    public ProductPage getRandomProduct() {
        WebElement randProduct = products.get(generateRandomIntervalNumber(0, products.size() - 1));
        String prodName = randProduct.getText();
        waitUntilElementBeClickable(randProduct)
                .click();
        ProductPage productPage = PageObjectFactory.createProductPage(webDriver);
        verifyProductName(productPage.getProductName(), prodName);
        return productPage;
    }

    private void verifyProductName(String expected, String generated) {
        assertEquals(expected, generated);
        getLogger().info("Product was verified: " + generated);
    }

    public CheckoutPage goToCheckoutPage() {
        waitUntilElementBeClickable(checkout)
                .click();
        return PageObjectFactory.createCheckoutPage(webDriver);
    }

    public CategoryPage repeatPickingProductOperations() throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            getLogger().info("Picking product...");
            PageObjectFactory.createMainPage(webDriver)
                    .expandProductCategory()
                    .selectProductCategory().getRandomProduct().addProductToCart();
        }
        goToCheckoutPage();
        return this;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
