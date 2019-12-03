package com.qarepo.utils;

import com.qarepo.driver.WebDriverThreadManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.UUID;

public final class SeleniumUtils {

    private static String singletoneUniqueValueInsideTest = null;

    /*
     * Private constructor to avoid instantiation of the class
     */
    private SeleniumUtils() {
    }

    /*
     * Method will generate a screenshot in the form of a file and save to the
     * Screenshots folder in test-output
     */
    public static void takeScreenshot(String browser, String screenshotName) throws IOException {
        File screenshot = ((TakesScreenshot) WebDriverThreadManager.getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("./test-output/Reports/Screenshots/" + browser + " - "
                + WebDriverThreadManager.getDriver().hashCode() + " - " + screenshotName + ".png"));

    }

    /*
     * Method will generate and return the path of an existing screenshot to add to
     * the HTML Report
     */
    public static String addScreenShotPathToReport(String browser, String screenshotName) {
        String imagePath = "./Screenshots/" + browser + " - " + WebDriverThreadManager.getDriver().hashCode() + " - "
                + screenshotName + ".png";
        return imagePath;
    }

    /*
     * Method will switch to a frame by extracting the name attribute from an iframe
     * based on an xpath locator
     */
    public static void switchToFrameByName(WebDriver driver, String frameXPath) {
        String frame = driver.findElement(By.xpath(frameXPath)).getAttribute("name");
        driver.switchTo().frame(frame);
    }

    /*
     * Method will switch to a frame by using the id attribute in the iframe
     */
    public static void switchToFrameByID(WebDriver driver, String id) {
        driver.switchTo().frame(id);
    }

    /*
     * Method will return a date as a String based on current date +/- a certain
     * number of days Syntax for TemporalAmount = Period.OfDays(int days),
     * Period.OfWeeks(int weeks), Period.OfMonths(int months), Period.OfYears(int
     * years)
     */
    public static String dateGenerator(TemporalAmount amountToAdd) {
        LocalDateTime localDate = LocalDateTime.now().plus(amountToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formatedDateTime = localDate.format(formatter);
        return formatedDateTime;
    }

    /*
     * Method will execute javascript to scroll to a specific element on the page
     * located by a xpath expression
     */
    public static void scrollToElement(WebDriver driver, WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    /*
     * Method will execute javascript to inject a geolocation in the browser
     */
    public static void injectGeoLocation(WebDriver driver, String latitude, String longitude) {
        ((JavascriptExecutor) driver).executeScript(
                "window.navigator.geolocation.getCurrentPosition = function(success){ var position = {\"coords\" : {\"latitude\": \""
                        + latitude + "\",\"longitude\": \"" + longitude + "\"}};success(position);}");
    }

    /*
     * Switches from parent window to sub-window and back to parent window.
     */
    public static String switchWindowsAndReturnToParent(WebDriverWait wait, String expectedURL) {
        String currentURL = null;
        String parentWindow = WebDriverThreadManager.getDriver().getWindowHandle();
        try {
            for (final String subWindow : WebDriverThreadManager.getDriver().getWindowHandles()) {
                WebDriverThreadManager.getDriver().switchTo().window(subWindow);
                if (!WebDriverThreadManager.getDriver().getWindowHandle().contentEquals(parentWindow)) {
                    wait.until(ExpectedConditions.urlContains(expectedURL));
                    currentURL = WebDriverThreadManager.getDriver().getCurrentUrl();
                    WebDriverThreadManager.getDriver().close();
                }
            }
            WebDriverThreadManager.getDriver().switchTo().window(parentWindow);
        } catch (Exception e) {
            return e.toString();
        }
        return currentURL;
    }

    /*
     * Switches from parent window to sub-window and stay in sub-window.
     */
    public static String switchWindowsAndDoNotReturnToParent(WebDriverWait wait, String expectedURL) {
        String currentURL = null;
        try {
            String parentWindow = WebDriverThreadManager.getDriver().getWindowHandle();
            for (final String subWindow : WebDriverThreadManager.getDriver().getWindowHandles()) {
                WebDriverThreadManager.getDriver().switchTo().window(subWindow);
                if (!WebDriverThreadManager.getDriver().getWindowHandle().contentEquals(parentWindow)) {
                    wait.until(ExpectedConditions.urlContains(expectedURL));
                    currentURL = WebDriverThreadManager.getDriver().getCurrentUrl();
                    WebDriverThreadManager.getDriver().close();
                }
            }
        } catch (Exception e) {
            return e.toString();
        }
        return currentURL;
    }

    public static String convertProperNameToVariableName(String string) {
        StringBuilder strBuilder = new StringBuilder();
        String[] splitString = string.split(" ");
        strBuilder.append(splitString[0].toLowerCase());
        for (int i = 1; i < splitString.length; i++) {
            String firstLetter = splitString[i].substring(0, 1);
            strBuilder.append(splitString[i].replace(firstLetter, firstLetter.toUpperCase()));
        }
        return strBuilder.toString()
                .replaceAll("-op", "Op")
                .replaceAll("hd/sd", "hdSd")
                .replace("office/division", "officeDivision")
                .replace("adServer,Other", "adServerOther")
                .replace("howWillThisBeServed,Other", "adServer")
                .replace("bannerSpecRequirement", "specRequirement")
                .replace("codingIn-House", "codingInHouse")
                .replace("customSize", "customSizes")
                .replace("email&Pre-HeaderSubjectLineGuidelines", "emailPreHeaderSubjectLineGuidelines")
                .replace("isThisANewCampaignOrBackendCreativeSwap", "newCampaignOrSwap")
                .replace("numberOfBannersThaTNeedToBeProducedForThisProject", "numberOfBannersNeeded")
                .replace("liveDate", "airDate")
                .replace("&", "And")
                .replace("length-OtherDefined", "lengthOtherDefined")
                .replace("creativeduedate", "creativeDueDate");
    }

    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
        }
    }

    public static String getShortUUID(){
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }

    public static String generateUniqueVersion(){
        return "Version_" + getShortUUID() + "_"+ Integer.toString(WebDriverThreadManager.getDriver().hashCode());
    }

    public static int getNumberOfObjects(By xpath){
        java.util.List<WebElement> lst = WebDriverThreadManager.getDriver().findElements(xpath);
        return lst.size();
    }

    public static String getSingletoneUniqueValueInsideTest(){
        if (singletoneUniqueValueInsideTest == null)
            singletoneUniqueValueInsideTest = "Version_" + getShortUUID() + "_" + WebDriverThreadManager.getDriver().hashCode();
        return singletoneUniqueValueInsideTest;
    }
}
