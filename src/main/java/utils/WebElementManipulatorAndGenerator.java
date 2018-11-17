package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.main.BasePage;

import java.math.BigDecimal;
import java.util.Random;

public abstract class WebElementManipulatorAndGenerator extends BasePage {

    public WebElementManipulatorAndGenerator(WebDriver webDriver, Actions actions, WebDriverWait waitDriver) {
        super(webDriver, actions, waitDriver);
    }

    public static int generateRandomIntervalNumber(int lowBound, int maxBound) {
        return new Random().nextInt((maxBound - lowBound) + 1) + lowBound;
    }

    protected String modifyStringToNumbers(String text) {
        return text.replaceAll("[$,]", "");
    }

    protected String replaceUnnecessaryCharactersInName(String text) {
        return text.replace("-", "")
                .replace("-", "");
    }

    protected BigDecimal getBigDecimalPriceFromElement(WebElement element) {
        return new BigDecimal(element.getText()
                .replaceAll("[$,]", ""));
    }

    protected void click(WebElement webElement) {
        getLogger().info("Clicking on: " + webElement.getText());
        waitUntilElementBeClickable(webElement).click();
    }

    protected void sendKeys(WebElement webElement, String keys) {
        getLogger().info("Setting text to: " + keys + " on element:" + webElement.getText());
        waitUntilElementBeVisible(webElement).sendKeys(keys);
    }
}
