package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public abstract class DriverFactory {
    private static final String chromeDiver = "webdriver.chrome.driver";
    private static final String fireFoxDiver = "webdriver.gecko.driver";
    private static final String ieDiver = "webdriver.edge.driver";

    private static WebDriver webDriver = null;

    public static WebDriver getDriverForBrowser(String browserName) {
        switch (browserName.toLowerCase()) {
            case "fifrefox":
                System.setProperty(fireFoxDiver, getAbsoluteVariablePath("geckodriver.exe"));
                webDriver = new FirefoxDriver();
                webDriver.manage().window().maximize();
                break;
            case "chrome":
                System.setProperty(chromeDiver, getAbsoluteVariablePath("chromedriver.exe"));
                webDriver = new ChromeDriver(new ChromeOptions().addArguments("disable-extensions"));
                webDriver.manage().window().maximize();
                break;
            case "ie":
                System.setProperty(ieDiver, getAbsoluteVariablePath("MicrosoftWebDriver.exe"));
                webDriver = new InternetExplorerDriver();
                webDriver.manage().window().maximize();
                break;
        }
        return Optional.of(webDriver).orElseThrow(() -> new NoSuchElementException("Driver was not found!"));
    }

    public static void closeDriver() {
        webDriver.close();
        webDriver.quit();
    }

    public static String getAbsoluteVariablePath(String nameDriver) {
        return new File(Objects.requireNonNull(DriverFactory.class
                .getClassLoader()
                .getResource(nameDriver))
                .getFile())
                .getAbsolutePath();
    }
}
