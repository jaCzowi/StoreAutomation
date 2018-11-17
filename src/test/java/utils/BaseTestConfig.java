package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public abstract class BaseTestConfig {

    protected WebDriver webDriver;

    @BeforeEach
    public void getBrowserDriver() throws IOException {
        webDriver = DriverFactory.getDriverForBrowser(PropertyLoader.loadDriverByProperty("browser"));
        webDriver.get("http://store.demoqa.com");
    }

    @AfterEach
    public void TearDown() {
        DriverFactory.closeDriver();
    }
}
