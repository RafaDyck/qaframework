package com.qarepo.pageobjects;

import org.openqa.selenium.By;

public class NavigationElements {

    private NavigationElements() {
    }

    public static By list_Nav_Links() {
        return By.xpath("//a[contains (@class, 'nav-link section-')]");
    }

    public static By link_Nav_Link(int elementNumber) {
        return By.xpath("(//a[contains (@class, 'nav-link section-')])[" + elementNumber + "]");
    }
}
