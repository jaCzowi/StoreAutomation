package pages.main;

import factory.PageObjectFactory;
import model.OrderedProducts;
import model.Product;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.WebElementManipulatorAndGenerator.generateRandomIntervalNumber;

public class ProductPage extends BasePage {

    final static Logger logger = Logger.getLogger(ProductPage.class);

    @FindBy(className = "prodtitle")
    WebElement productTitle;

    @FindBy(xpath = "//*[@id='single_product_page_container']/div/div/form/div/p[2]/span")
    WebElement productPrice;

    @FindBy(css = "input.wpsc_buy_button")
    WebElement addToCart;

    @FindBy(css = "em.count")
    WebElement numberOfItems;

    @FindBy(css = ".alert.addtocart")
    WebElement textInfo;

    public ProductPage(WebDriver webDriver, Actions actions, WebDriverWait waitDriver) {
        super(webDriver, actions, waitDriver);
        PageFactory.initElements(webDriver, this);
    }

    protected String getProductName() {
        return waitUntilElementBeVisible(productTitle).getText();
    }

    public CategoryPage addProductToCart() throws InterruptedException {
        int productCount = generateRandomIntervalNumber(1, 3);
        for (int i = 1; i <= productCount; i++) {
            waitUntilElementBeClickable(addToCart)
                    .click();
        }
        BigDecimal price = new BigDecimal(productPrice.getText().replace("$", ""));
        OrderedProducts.addProduct(new Product(productTitle.getText().replaceAll("–", ""), productCount, price));
        logAndWaitToTextElementBePresent(textInfo, "Item has been added to your cart!");
        logAndWaitToTextElementBePresent(numberOfItems, numberOfItems.getText());
        productCount += Integer.parseInt(numberOfItems.getText());
        Thread.sleep(8000);
        verifyNumberOfOrderedProducts(productCount);
        return PageObjectFactory.createCategoryPage(webDriver);
    }

    private void verifyNumberOfOrderedProducts(int productCount) {
        logAndWaitToTextElementBePresent(numberOfItems, String.valueOf(productCount));
        assertEquals(productCount, Integer.parseInt(numberOfItems.getText()));
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
