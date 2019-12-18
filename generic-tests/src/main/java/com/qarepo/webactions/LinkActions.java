package com.qarepo.webactions;

import com.qarepo.driver.WebDriverThreadManager;
import com.qarepo.driver.WebDriverWaits;
import com.qarepo.pageobjects.LinkElements;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.stream.Collectors;

public class LinkActions {
    private Logger LOGGER = LogManager.getLogger(LinkActions.class);
    private String driverHash = "[WebDriver Hash: " + WebDriverThreadManager.getDriver().hashCode() + "]";

    public Set<String> get_Img_Tag_Src() {
        Set<String> set = WebDriverWaits.findElementsWithWait(LinkElements.list_All_ImgTag_Src(), 30, 1)
                                        .stream()
                                        .map(e -> e.getAttribute("src"))
                                        .collect(Collectors.toSet());
        LOGGER.info("[WebDriver Hash: " + driverHash + "] [Img-tags Found: " + set.size() + "]");
        return set;
    }

    public Set<String> get_A_Tag_Href() {
        Set<String> set = WebDriverWaits.findElementsWithWait(LinkElements.list_All_ATag_Href(), 30, 1)
                                        .stream()
                                        .map(e -> e.getAttribute("src"))
                                        .collect(Collectors.toSet());
        LOGGER.info("[WebDriver Hash: " + driverHash + "] [A-tags Found: " + set.size() + "]");
        return set;
    }

    public Set<String> get_Link_Tag_Href() {
        Set<String> set = WebDriverWaits.findElementsWithWait(LinkElements.list_All_LinkTag_Href(), 30, 1)
                                        .stream()
                                        .map(e -> e.getAttribute("src"))
                                        .collect(Collectors.toSet());
        LOGGER.info("[WebDriver Hash: " + driverHash + "] [Link-tags Found: " + set.size() + "]");
        return set;
    }
}
