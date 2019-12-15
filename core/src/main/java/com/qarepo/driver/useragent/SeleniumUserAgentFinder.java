package com.qarepo.driver.useragent;

import com.qarepo.driver.Randomize;
import com.qarepo.driver.WebDriverRunner;
import com.qarepo.driver.WebDriverThreadManager;
import com.qarepo.driver.WebDriverWaits;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SeleniumUserAgentFinder implements Randomize {
    private static final Logger LOGGER = LogManager.getLogger(SeleniumUserAgentFinder.class);
    private String browser;
    private String url;

    public SeleniumUserAgentFinder(final String browser, final String url) {
        this.browser = browser;
        this.url = url;
    }

    public List<String> getUserAgentList() {
        return WebDriverWaits.findElementsWithWait(By.xpath("//td/a"))
                             .stream()
                             .map(WebElement::getText)
                             .collect(Collectors.toList());
    }

    @Override
    public String getRandomValue() {
        String userAgent = null;
        WebDriverRunner driver = new WebDriverRunner();
        try {
            driver.startWebDriver(browser);
            WebDriverThreadManager.getDriver()
                                  .get(url);
            List<String> userAgentList = getUserAgentList();
            userAgent = userAgentList.get(new Random().nextInt(userAgentList.size()));
            LOGGER.info("[USER_AGENT: " + userAgent + "]");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            LOGGER.error(sw.toString());
        } finally {
            driver.stopWebDriver();
        }
        return userAgent;
    }
}
