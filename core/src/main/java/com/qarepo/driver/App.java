package com.qarepo.driver;


import com.qarepo.driver.proxy.SeleniumProxyFinder;
import com.qarepo.driver.useragent.JSoupUserAgentFinder;
import com.qarepo.driver.useragent.SeleniumUserAgentFinder;

public class App {

    public static void main(String[] args) {
        /*
         * TODO: convert below to unit tests
         */
        new SeleniumUserAgentFinder("Google Chrome Headless", "https://developers.whatismybrowser.com/useragents/explore/software_name/chrome/").getRandomValue();
        new JSoupUserAgentFinder("https://developers.whatismybrowser.com/useragents/explore/software_name/chrome/").getRandomValue();
        new SeleniumProxyFinder("Google Chrome Headless", "https://free-proxy-list.net/").getRandomValue();
    }
}
