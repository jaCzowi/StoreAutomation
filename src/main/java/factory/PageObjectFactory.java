package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.main.CategoryPage;
import pages.main.MainPage;
import pages.main.ProductPage;
import pages.summary.CheckoutPage;
import pages.summary.ContactDetailsPage;
import pages.summary.TransactionResultPage;

public class PageObjectFactory {


    private PageObjectFactory() {
    }

    public static CategoryPage createCategoryPage(WebDriver webDriver) {
        return new CategoryPage(webDriver, new Actions(webDriver), new WebDriverWait(webDriver, 15));
    }

    public static ProductPage createProductPage(WebDriver webDriver) {
        return new ProductPage(webDriver, new Actions(webDriver), new WebDriverWait(webDriver, 15));
    }

    public static CheckoutPage createCheckoutPage(WebDriver webDriver) {
        return new CheckoutPage(webDriver, new Actions(webDriver), new WebDriverWait(webDriver, 10));
    }

    public static MainPage createMainPage(WebDriver webDriver) {
        return new MainPage(webDriver, new Actions(webDriver), new WebDriverWait(webDriver, 15));
    }

    public static ContactDetailsPage createContactDetailsPage(WebDriver webDriver) {
        return new ContactDetailsPage(webDriver, new Actions(webDriver), new WebDriverWait(webDriver, 15));
    }

    public static TransactionResultPage createTransactionResultPage(WebDriver webDriver) {
        return new TransactionResultPage(webDriver, new Actions(webDriver), new WebDriverWait(webDriver, 15));
    }
}

