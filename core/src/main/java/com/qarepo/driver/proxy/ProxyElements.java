package com.qarepo.driver.proxy;

import org.openqa.selenium.By;

public class ProxyElements {
    private ProxyElements() {
    }

    static By dropDown_FilterHttps() {
        return By.xpath("(//th[7]//select/option)[3]");
    }

    static By text_HostIpAddress(int elementNumber) {
        return By.xpath("(.//td[1])[" + elementNumber + "]");
    }

    static By text_Port(int elementNumber) {
        return By.xpath("(.//td[2])[" + elementNumber + "]");
    }
}
