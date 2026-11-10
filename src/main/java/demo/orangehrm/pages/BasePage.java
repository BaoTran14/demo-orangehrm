package demo.orangehrm.pages;

import demo.orangehrm.utils.Log;
import demo.orangehrm.utils.TestDataHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    private final WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void openUrl(String url) {
        webDriver.get(url);
        Log.info(this.getClass(), "Opened address successfully: ", url);
    }

    public String getCurrentUrl() {
        Log.info(this.getClass(), "Current URL is: ", webDriver.getCurrentUrl());
        return webDriver.getCurrentUrl();
    }

    public String getCssValueOf(String cssAttribute, By locator) {
        return webDriver.findElement(locator).getCssValue(cssAttribute);
    }
}
