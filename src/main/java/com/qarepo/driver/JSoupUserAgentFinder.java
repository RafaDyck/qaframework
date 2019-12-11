package com.qarepo.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Random;

public class JSoupUserAgentFinder implements Randomize {
    private static final Logger LOGGER = LogManager.getLogger(JSoupUserAgentFinder.class);
    private String url;
    Document doc = null;

    public JSoupUserAgentFinder(String url) {
        this.url = url;
    }

    @Override
    public String getRandomValue() {
        String userAgent = null;
        try {
            doc = Jsoup.connect(url)
                    .timeout(3000)
                    .get();
            List<Element> userAgentList = doc.select("td.useragent");
            System.out.println(userAgentList.size());
            for (int i = 0; i < userAgentList.size(); i++) {
                userAgent = userAgentList.get(new Random().nextInt(userAgentList.size())).text();
            }
            LOGGER.info("[USER_AGENT: " + userAgent + "]");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            LOGGER.error(sw.toString());
        }
        return userAgent;
    }
}
