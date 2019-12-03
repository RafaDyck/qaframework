package com.qarepo.driver;

import com.qarepo.utils.SeleniumUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ParameterManager {
    private static final Logger logger = LogManager.getLogger(ParameterManager.class);
    private String browser, username, password, URL, testStep;

    protected String getBrowser() {
        return browser;
    }

    protected void setBrowser(String browser) {
        this.browser = browser;
    }

    protected String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    protected String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    protected String getURL() {
        return URL;
    }

    protected void setURL(String URL) {
        this.URL = URL;
    }

    protected String getTestStep() {
        logger.log(Level.INFO, getDriverHash() + " [Test Step: " + testStep + "]");
        return testStep;
    }

    protected void setTestStep(String testStep) {
        this.testStep = testStep;
    }

    protected String getDriverHash() {
        return "[Driver Hash: " + WebDriverThreadManager.getDriver().hashCode() + "]";
    }

    protected String printResults(String expectedResult, String actualResult) {
        return "\n" + getDriverHash() + "\nExpected Result: " + expectedResult + "\nActual Result: " + actualResult
                + "\n\n";
    }

    protected void captureScreenshotAndException(Exception e, String screenshotName) {
        try {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            SeleniumUtils.takeScreenshot(getBrowser(), screenshotName);
            logger.log(Level.ERROR, getDriverHash() + " " + sw.toString());
        } catch (IOException e1) {
            StringWriter sw = new StringWriter();
            e1.printStackTrace(new PrintWriter(new StringWriter()));
            logger.log(Level.ERROR, getDriverHash() + " " + sw.toString());
        }
    }

}