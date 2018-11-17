package pages.main;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {

    protected WebDriver webDriver;
    protected Actions action;
    protected WebDriverWait waitDriver;

    public BasePage(WebDriver webDriver, Actions actions, WebDriverWait waitDriver) {
        this.webDriver = webDriver;
        this.action = actions;
        this.waitDriver = waitDriver;
    }

    protected WebElement waitUntilElementBeClickable(WebElement webElement) {
        return waitDriver.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected List<WebElement> waitUntilElementsBeVisible(List<WebElement> webElements) {
        return waitDriver.until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    protected WebElement waitUntilElementBeVisible(WebElement webElement) {
        return waitDriver.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void logAndWaitToTextElementBePresent(WebElement element, String txt) {
        getLogger().info("Waiting for text: " + txt + "\nOn Element: " + element.getAttribute("name"));
        waitDriver.until(ExpectedConditions.textToBePresentInElement(element, txt));
    }

    protected abstract Logger getLogger();

}

