package pages.summary;


import factory.PageObjectFactory;
import factory.UserFactory;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.main.BasePage;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactDetailsPage extends BasePage {

    final static Logger logger = Logger.getLogger(ContactDetailsPage.class);
    private static BigDecimal shippingValue;
    @FindBy(id = "shippingSameBilling")
    private WebElement bindingField;
    @FindBy(xpath = "//*[contains(text(), 'Item Cost')]/following-sibling::*")
    private WebElement itemCost;
    @FindBy(xpath = "//*[@class='pricedisplay checkout-shipping']/span")
    private WebElement totalShipping;
    @FindBy(xpath = "//*[@id='checkout_total']/span")
    private WebElement totalPrice;
    @FindBy(id = "wpsc_checkout_form_2")
    private WebElement fName;
    @FindBy(id = "wpsc_checkout_form_3")
    private WebElement lName;
    @FindBy(id = "wpsc_checkout_form_4")
    private WebElement address;
    @FindBy(id = "wpsc_checkout_form_5")
    private WebElement city;
    @FindBy(id = "wpsc_checkout_form_9")
    private WebElement email;
    @FindBy(id = "wpsc_checkout_form_6")
    private WebElement province;
    @FindBy(css = "#wpsc_checkout_form_7")
    private WebElement countrySelector;
    @FindBy(id = "wpsc_checkout_form_8")
    private WebElement postalCode;
    @FindBy(id = "wpsc_checkout_form_18")
    private WebElement phoneNumb;
    @FindBy(css = "input[value='Purchase']")
    private WebElement purchase;
    private User randomUser;

    public ContactDetailsPage(WebDriver webDriver, Actions actions, WebDriverWait waitDriver) {
        super(webDriver, actions, waitDriver);
        randomUser = getRandomUser();
        PageFactory.initElements(webDriver, this);
    }

    public static BigDecimal returnTotalShipping() {
        logger.info("Shipping value: " + shippingValue);
        return shippingValue;
    }

    public ContactDetailsPage selectSameBindingAddress() {
        waitUntilElementBeClickable(bindingField)
                .click();
        return this;
    }

    public ContactDetailsPage fillTheContactForm() {
        email.sendKeys(randomUser.getEmail());
        fName.sendKeys(randomUser.getfName());
        lName.sendKeys(randomUser.getlName());
        address.sendKeys(randomUser.getAddress());
        city.sendKeys(randomUser.getCity());
        province.sendKeys(randomUser.getProvince());
        selectCountry();
        postalCode.sendKeys(randomUser.getPostalCode());
        phoneNumb.sendKeys(randomUser.getPhoneNumb());
        return this;
    }

    public TransactionResultPage purchaseCheckout() {
        waitUntilElementBeClickable(purchase)
                .click();
        return PageObjectFactory.createTransactionResultPage(webDriver);
    }

    private User getRandomUser() {
        getLogger().info("Generated random User!");
        return UserFactory.getRandomUser();
    }

    private void selectCountry() {
        getLogger().info("Country was Selected!");
        new Select(countrySelector).selectByVisibleText("Poland");
    }

    public ContactDetailsPage verifyTotalShippingPrice() {
        String iC = itemCost.getText().replaceAll("[$,]", "");
        String sC = totalShipping.getText().replaceAll("[$,]", "");
        BigDecimal tP = new BigDecimal(totalPrice.getText().replaceAll("[$,]", ""));
        BigDecimal addSumPrices = new BigDecimal(iC).add(new BigDecimal(sC));
        assertEquals(tP, addSumPrices);
        getLogger().info("VerificationShipping- correct!\n" + "Total: " + tP + "PricesSum: " + addSumPrices);
        return this;
    }

    public ContactDetailsPage verifyItemPrice() {
        BigDecimal sumPrice = CheckoutPage.getProductsFromCheckoutTable().stream().
                map(Product::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        assertEquals(sumPrice, new BigDecimal(itemCost.getText().replaceAll("[$,]", "")));
        shippingValue = new BigDecimal(totalShipping.getText().replaceAll("[$,]", ""));
        getLogger().info("Item price was verified! ");
        return this;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
