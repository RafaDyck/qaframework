package com.qarepo.tests;

import com.qarepo.webactions.LinkActions;
import com.qarepo.webactions.NavigationActions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SeleniumLinkTests {
    private static final Logger LOGGER = LogManager.getLogger(SeleniumLinkTests.class);
    private static StringWriter sw = new StringWriter();

    @Test(groups = {"selenium-site-links"}
            , description = "All pages, images display with successful response code")
    public void checkLinks() {
        SoftAssert softAssert = new SoftAssert();
        NavigationActions nav = new NavigationActions();
        LinkActions linkActions = new LinkActions();

        for (int i = 1; i <= nav.getList_NavigationLinks().size(); i++) {
            nav.clickLink_Nav_Link(i, "href");
            List<String> aTags = new ArrayList<>(linkActions.get_A_Tag_Href());
            List<String> imgTags = new ArrayList<>(linkActions.get_Img_Tag_Src());
            List<String> linkTags = new ArrayList<>(linkActions.get_Link_Tag_Href());

            for (String aTag : aTags) {
                if (!(aTag.contains("javascript")) && !(aTag.contains("tel"))) {
                    try {
                        LOGGER.info("atag= " + aTag);
                        URL url = new URL(aTag);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        int responseCode = conn.getResponseCode();
                        LOGGER.info("[A-tag href:" + aTag + "] [Response Code:" + responseCode);
                        if (responseCode == 200) {
                            softAssert.assertEquals(200, responseCode);
                        } else if (responseCode == 301) {
                            softAssert.assertEquals(301, responseCode);
                        } else {
                            softAssert.fail("[URL: " + aTag + "] [Response Code: " + responseCode + "]");
                        }
                        conn.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace(new PrintWriter(sw));
                        LOGGER.error(sw.toString());
                    }
                }
            }
            for (String imgTag : imgTags) {
                try {
                    URL url = new URL(imgTag);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    int responseCode = conn.getResponseCode();
                    LOGGER.info("[Img-tag src:" + imgTag + "] [Response Code:" + responseCode);
                    if (responseCode == 200) {
                        softAssert.assertEquals(200, responseCode);
                    } else if (responseCode == 301) {
                        softAssert.assertEquals(301, responseCode);
                    } else {
                        softAssert.fail("[URL: " + imgTag + "] [Response Code: " + responseCode + "]");
                    }
                    conn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace(new PrintWriter(sw));
                    LOGGER.error(sw.toString());
                }
            }
            for (String linkTag : linkTags) {
                try {
                    URL url = new URL(linkTag);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    int responseCode = conn.getResponseCode();
                    LOGGER.info("[Link-tag href:" + linkTag + "] [Response Code:" + responseCode);
                    if (responseCode == 200) {
                        softAssert.assertEquals(200, responseCode);
                    } else if (responseCode == 301) {
                        softAssert.assertEquals(301, responseCode);
                    } else {
                        softAssert.fail("[URL: " + linkTag + "] [Response Code: " + responseCode + "]");
                    }
                    conn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace(new PrintWriter(sw));
                    LOGGER.error(sw.toString());
                }
            }
        }
        softAssert.assertAll();
    }
}

