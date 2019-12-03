package com.qarepo.driver;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class WebDriverRunnerImpl implements WebDriverRunner {
    private static final Logger logger = LogManager.getLogger(WebDriverRunnerImpl.class);
    private WebDriver driver = null;

    public void startWebDriver(String browser) {
        driver = DriverFactory.createDriverInstance(browser, "");
        WebDriverThreadManager.setWebDriver(driver);
        logger.log(Level.INFO, "[WebDriver Hash: " + driver.hashCode() + "] WebDriver Created");
    }

    public void stopWebDriver() {
        int driverHash = 0;
        if (driver != null) {
            driverHash = WebDriverThreadManager.getDriver().hashCode();
            WebDriverThreadManager.getDriver().quit();
        }
        logger.log(Level.INFO, "[WebDriver Hash: " + driverHash + "] WebDriver Stopped");
    }
}
