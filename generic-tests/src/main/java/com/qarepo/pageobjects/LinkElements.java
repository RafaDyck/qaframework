package com.qarepo.pageobjects;

import org.openqa.selenium.By;

public class LinkElements {
    private LinkElements() {
    }
    public static By list_All_ImgTag_Src() {
        return By.xpath("//img");
    }

    public static By list_All_ATag_Href() {
        return By.xpath("//a[@href]");
    }

    public static By list_All_LinkTag_Href() {
        return By.xpath("//link[@href]");
    }
}
