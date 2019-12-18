package com.qarepo.webactions;

import com.qarepo.driver.WebDriverThreadManager;
import com.qarepo.driver.WebDriverWaits;
import com.qarepo.pageobjects.NavigationElements;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class NavigationActions {
    private static final Logger LOGGER = LogManager.getLogger(NavigationActions.class);
    private String driverHash = "[WebDriver Hash: " + WebDriverThreadManager.getDriver().hashCode() + "]";

    public List<String> getList_NavigationLinks() {
        List<String> links = WebDriverWaits.findElementsWithWait(NavigationElements.list_Nav_Links(), 10, 1)
                                           .stream()
                                           .map(WebElement::getText)
                                           .collect(Collectors.toList());
        LOGGER.info(driverHash + " Get Nav links list: " + links.size());
        return links;
    }

    public String clickLink_Nav_Link(int elementNumber, String attribute) {
        LOGGER.info(driverHash + " Click 'Nav' link #" + elementNumber);
        WebDriverWaits.findElementWithClickableWait(NavigationElements.link_Nav_Link(elementNumber), 10, 1).click();
        String attr = WebDriverWaits.findElementWithClickableWait(NavigationElements.link_Nav_Link(elementNumber), 10, 1)
                                    .getAttribute(attribute);
        LOGGER.info(driverHash + " Nav. Page : " + attr);
        return attr;
    }
}
