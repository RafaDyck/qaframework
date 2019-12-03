package com.qarepo.utils;

import com.qarepo.driver.WebDriverThreadManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class WebDriverUtils {
	private static final Logger logger = LogManager.getLogger(WebDriverUtils.class);
	private static FluentWait<WebDriver> wait;

	public static WebElement findElementWithWait(By by, long timeout, long polling) {
		WebDriver driver = WebDriverThreadManager.getDriver();
		wait = new FluentWait<>(driver);
		wait.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(polling))
				.ignoring(WebDriverException.class).until(e -> driver.findElement(by));
		return driver.findElement(by);
	}

	public static WebElement findElementWithWait(By by) {
		return findElementWithWait(by, 20, 1);
	}

	public static WebElement findElementWithClickableWait(By by, long timeout, long polling) {
		WebDriver driver = WebDriverThreadManager.getDriver();
		wait = new FluentWait<>(driver);
		wait.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(polling))
				.ignoring(WebDriverException.class).until(ExpectedConditions.elementToBeClickable(by));
		return driver.findElement(by);
	}

	public static WebElement findElementWithClickableWait(By by) {
		return findElementWithClickableWait(by, 20, 1);
	}

	public static void findElementWithInvisibilityWait(By by, long timeout, long polling) {
		WebDriver driver = WebDriverThreadManager.getDriver();
		wait = new FluentWait<>(driver);
		wait.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(polling)).until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public static void findElementWithInvisibilityWait(By by) {
		findElementWithInvisibilityWait(by, 20, 1);
	}

	public static WebElement findElementWithVisibilityWait(By by, long timeout, long polling) {
		WebDriver driver = WebDriverThreadManager.getDriver();
		wait = new FluentWait<>(driver);
		wait.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(polling))
				.ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOfElementLocated(by));
		return driver.findElement(by);
	}

	public static WebElement findElementWithVisibilityWaitWithoutException(By by, long timeout, long polling) {
		WebDriver driver = WebDriverThreadManager.getDriver();
		wait = new FluentWait<>(driver);
		wait.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(polling))
				.ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOfElementLocated(by));
		try{
			return driver.findElement(by);
		}
		catch (Exception e) {
			return null;
		}
	}

	public static WebElement findElementWithVisibilityWait(By by) {
		return findElementWithVisibilityWait(by, 10, 1);
	}

	public static List<WebElement> findElementsWithWait(By by, long timeout, long polling) {
		WebDriver driver = WebDriverThreadManager.getDriver();
		wait = new FluentWait<>(driver);
		wait.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(polling))
				.ignoring(WebDriverException.class).until(e -> driver.findElement(by));
		return driver.findElements(by);
	}

	public static List<WebElement> findElementsWithWait(By by) {
		return findElementsWithWait(by, 10, 1);
	}

	public static WebElement findElementWithTextContainsWait(By by, String textContains, long timeout, long polling) {
		WebDriver driver = WebDriverThreadManager.getDriver();
		wait = new FluentWait<>(driver);
		wait.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(polling))
				.ignoring(WebDriverException.class).until(ExpectedConditions.textToBePresentInElementLocated(by, textContains));
		return driver.findElement(by);
	}

	public static WebElement findElementWithTextContainsWait(By by, String textContains) {
		return findElementWithTextContainsWait(by, textContains, 10, 1);
	}

	public static void waitForUrl(String expectedURL, long timeout, long polling) {
		WebDriver driver = WebDriverThreadManager.getDriver();
		wait = new FluentWait<>(driver);
		wait.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(polling))
				.ignoring(WebDriverException.class).until(ExpectedConditions.urlContains(expectedURL));
	}

	public static void clickElementWithWait(By by, long timeout, long polling) {
		for (int i = 0; i < 10; i++) {
			try {
				WebDriver driver = WebDriverThreadManager.getDriver();
				wait = new FluentWait<>(driver);
				wait.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(polling))
						.ignoring(WebDriverException.class).until(ExpectedConditions.elementToBeClickable(by));
				WebDriverThreadManager.getDriver().findElement(by).click();
				break;
			} catch (ElementClickInterceptedException e) {
				logger.log(Level.WARN, "[WebDriver Hash: " + WebDriverThreadManager.getDriver().hashCode()
						+ "][attempt: " + i + "] Failed to click element: " + by.toString());
			}
		}
	}

	public static void clickElementWithWait(By by) {
		clickElementWithWait(by, 10, 1);
	}
}
