package com.qarepo.driver.proxy;

import com.qarepo.driver.WebDriverWaits;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProxyService {
    private static final Logger LOGGER = LogManager.getLogger(ProxyService.class);

    void select_HttpsOptions_Yes() {
        LOGGER.info("Select HTTPS filter option: 'yes'");
        WebDriverWaits.findElementWithClickableWait(ProxyElements.dropDown_FilterHttps(), 10, 1)
                      .click();
    }

    private String getProxyHostByIndex(int elementNumber) {
        String iPAddress = WebDriverWaits.findElementWithVisibilityWait(ProxyElements.text_HostIpAddress(elementNumber), 10, 1)
                                         .getText();
        LOGGER.info("[Proxy #" + elementNumber + " Host IPAddress: " + iPAddress + "]");
        return iPAddress;
    }

    private String getProxyPortByIndex(int elementNumber) {
        String port = WebDriverWaits.findElementWithVisibilityWait(ProxyElements.text_Port(elementNumber), 10, 1)
                                    .getText();
        LOGGER.info("[Proxy #" + elementNumber + " Port: " + port + "]");
        return port;
    }

    public String getProxy(int elementNumber) {
        return "https://" + getProxyHostByIndex(elementNumber) + ":" + getProxyPortByIndex(elementNumber);
    }
}
