package pages.summary;

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
import pages.main.BasePage;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutPage extends BasePage {

    final static Logger logger = Logger.getLogger(CheckoutPage.class);
    private static List<Product> checkoutProducts;
    @FindBy(css = ".pricedisplay")
    private WebElement totalPrice;
    @FindBy(css = " .checkout_cart tr.product_row")
    private List<WebElement> productRow;
    @FindBy(className = "step2")
    private WebElement continueCheckout;

    public CheckoutPage(WebDriver webDriver, Actions actions, WebDriverWait waitDriver) {
        super(webDriver, actions, waitDriver);
        checkoutProducts = new LinkedList<>();
        PageFactory.initElements(webDriver, this);
    }

    public static List<Product> getProductsFromCheckoutTable() {
        return checkoutProducts;
    }

    public CheckoutPage fillListWithCheckoutProducts() {
        for (WebElement eL : productRow) {
            checkoutProducts.add(new Product(eL));
        }
        getLogger().info(fetchAndPrintProductData());
        return this;
    }

    private CheckoutPage fetchAndPrintProductData() {
        for (Product tB : checkoutProducts)
            System.out.println(tB.toString());
        return this;
    }

    public ContactDetailsPage continueCheckout() throws InterruptedException {
        waitUntilElementBeClickable(continueCheckout)
                .click();
        Thread.sleep(5000);
        return PageObjectFactory.createContactDetailsPage(webDriver);
    }

    public CheckoutPage compereCheckoutWithOrderedProducts() {
        checkoutProducts.sort(Comparator.comparing(Product::getName));
        for (int i = 0; i < checkoutProducts.size(); i++) {
            assertEquals(checkoutProducts.get(i), OrderedProducts.getOrderedProducts().get(i));
        }
        getLogger().info("Checkout products was verified in Checkout");
        return this;
    }

    protected Logger getLogger() {
        return logger;
    }
}
