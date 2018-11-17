package pages.main;

import factory.PageObjectFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static utils.WebElementManipulatorAndGenerator.generateRandomIntervalNumber;

public class MainPage extends BasePage {
    final static Logger logger = Logger.getLogger(MainPage.class);

    @FindBy(xpath = "//*[@id='menu-item-15']/a")
    private WebElement home;
    @FindBy(xpath = "//*[@id='menu-item-33']/a")
    private WebElement productCategory;
    @FindBy(xpath = "//*[@id='menu-item-16']/a")
    private WebElement noTittle;
    @FindBy(xpath = "//*[@id='menu-item-72']/a")
    private WebElement allProducts;

    @FindBy(xpath = "//*[@id='menu-item-34']/a")
    private WebElement accesories;

    @FindBy(xpath = "//*[@id='menu-item-35']/a")
    private WebElement iMacs;
    @FindBy(xpath = "//*[@id='menu-item-36']/a")
    private WebElement iPads;
    @FindBy(xpath = "//*[@id='menu-item-37']/a")
    private WebElement iPhones;
    @FindBy(xpath = "//*[@id='menu-item-38']/a")
    private WebElement iPods;
    @FindBy(xpath = "//*[@id='menu-item-39']/a")
    private WebElement MacBooks;


    public MainPage(WebDriver webDriver, Actions actions, WebDriverWait waitDriver) {
        super(webDriver, actions, waitDriver);
        PageFactory.initElements(webDriver, this);
    }

    public MainPage expandProductCategory() {
        action.moveToElement(
                waitUntilElementBeVisible(productCategory))
                .perform();
        return this;
    }

    public CategoryPage selectProductCategory() {
        waitUntilElementBeClickable(getRandomCategory())
                .click();
        return PageObjectFactory.createCategoryPage(webDriver);
    }

    private WebElement getRandomCategory() {
        return getCategoriesList()
                .get(generateRandomIntervalNumber(0, 5));
    }

    private List<WebElement> getCategoriesList() {
        return Arrays.asList(accesories, iMacs, iPads, iPhones, iPods, MacBooks);
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
