package com.qarepo.driver.proxy;

import com.qarepo.driver.Randomize;
import com.qarepo.driver.WebDriverRunnerImpl;
import com.qarepo.driver.WebDriverThreadManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class SeleniumProxyFinder implements Randomize {
    private static final Logger LOGGER = LogManager.getLogger(SeleniumProxyFinder.class);
    private String browser;
    private String url;

    public SeleniumProxyFinder(final String browser, final String url) {
        this.browser = browser;
        this.url = url;
    }

    @Override
    public String getRandomValue() {
        WebDriverRunnerImpl driver = new WebDriverRunnerImpl();
        ProxyService proxyService = new ProxyService();
        driver.startWebDriver(browser);
        WebDriverThreadManager.getDriver().get(url);
        proxyService.select_HttpsOptions_Yes();
        String proxyInfo = proxyService.getProxy(ThreadLocalRandom.current().nextInt(1, 20));
        LOGGER.info("[PROXY: " + proxyInfo + "]");
        driver.stopWebDriver();
        System.out.println(proxyInfo);
        return proxyInfo;
    }
}
