package test_steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class AddItemTest {

    @FindBy(xpath = "//*[@id='menu-item-72']/a")
    private WebElement allProducts;

    @FindBy(css = "input.wpsc_buy_button")
    private List<WebElement> addingToCards;

    @FindBy(css = "em.count")
    WebElement numberOfItems;

    private static Integer saveItemCount;
    protected WebDriver webDriver;
    private WebDriverWait waitDriver;

    @Before
    public void initBrowserAndDriver() throws IOException {
        webDriver = DriverFactory.getDriverForBrowser("chrome");
        webDriver.get("http://store.demoqa.com");
        PageFactory.initElements(webDriver, this);
        waitDriver = new WebDriverWait(webDriver, 10);
    }

    @After
    public void closeDriver() {
        webDriver.quit();
    }

    @Given("^Navigate to the page with items$")
    public void navigateToThePageWithItems() throws InterruptedException, IOException {
        allProducts.click();
        Thread.sleep(1000);
    }

    @And("^save current value of 'OldItemCount'$")
    public void saveCurrentValueOfOldItemCount() {
        saveItemCount = Integer.parseInt(numberOfItems.getText());
    }


    @When("^button of selected item will be clicked$")
    public void buttonOfSelectedItemWillBeClicked() throws InterruptedException {
        System.out.println(addingToCards.size());
        waitDriver.until(ExpectedConditions.elementToBeClickable(addingToCards
                .get(new Random().nextInt(2))))
                .click();
        Thread.sleep(3000);
    }


    @Then("^item should be add to card, and 'OldItemCount' value should increase$")
    public void itemShouldBeAddToCardAndValueShouldIncrease() throws InterruptedException {
        waitDriver.until(ExpectedConditions.visibilityOf(numberOfItems));
        Assertions.assertNotEquals(saveItemCount, Integer.parseInt(numberOfItems.getText()));
        Assertions.assertEquals(saveItemCount + 1, Integer.parseInt(numberOfItems.getText()));
    }
}
