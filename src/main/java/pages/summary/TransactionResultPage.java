package pages.summary;

import model.OrderedProducts;
import model.Product;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.main.BasePage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionResultPage extends BasePage {

    final static Logger logger = Logger.getLogger(TransactionResultPage.class);

    @FindBy(xpath = "//p[contains(text(), 'Total Shipping')]")
    private WebElement transactionTotalShipping;
    @FindBy(css = ".wpsc-purchase-log-transaction-results > tbody > tr")
    private List<WebElement> products;

    public TransactionResultPage(WebDriver webDriver, Actions actions, WebDriverWait waitDriver) {
        super(webDriver, actions, waitDriver);
        PageFactory.initElements(webDriver, this);
    }

    public TransactionResultPage getAndAssertDataFromTable() {
        for (WebElement pr : products) {
            List<WebElement> td = pr.findElements(By.cssSelector("td"));
            String name = td.get(0).getText().replaceAll("-", "");
            BigDecimal price = new BigDecimal(td.get(1).getText().replaceAll("[$,]", ""));
            Integer quantity = Integer.parseInt(td.get(2).getText());
            BigDecimal totalItemPrice = new BigDecimal(td.get(3).getText().replaceAll("[$,]", ""));
            assertResultWithOrderProducts(name, price, quantity, totalItemPrice);
        }
        return this;
    }

    private void assertResultWithOrderProducts(String name, BigDecimal price, Integer quantity, BigDecimal total) {
        Optional<Product> expectedProduct = OrderedProducts.getOrderedProducts().stream().filter(t -> t.getName().equals(name)).findAny();
        expectedProduct.ifPresent(elem -> {
            assertEquals(elem.getName(), name);
            assertEquals(elem.getPrice(), price);
            assertEquals(elem.getQuantity(), quantity);
            assertEquals(elem.getTotal(), total);
        });
        getLogger().info(expectedProduct.toString() + "  was verified with ordered products");
    }

    public TransactionResultPage assertTotalTransactionCost() {
        BigDecimal totalPaymentForItems = OrderedProducts.getOrderedProducts().stream()
                .map(Product::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        assertTrue(
                waitUntilElementBeVisible(transactionTotalShipping)
                        .getText()
                        .replaceAll("[$,]", "")
                        .contains(String.valueOf(totalPaymentForItems.add(ContactDetailsPage.returnTotalShipping()))));
        getLogger().info("Payment for items: " + totalPaymentForItems + " was verified with TotalShipping.");
        return this;
    }

    protected Logger getLogger() {
        return logger;
    }
}
