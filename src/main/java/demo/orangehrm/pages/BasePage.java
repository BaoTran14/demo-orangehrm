package demo.orangehrm.pages;

import demo.orangehrm.learn.AgodaHeader;
import demo.orangehrm.learn.AgodaLandingPage;
import demo.orangehrm.learn.AgodaUserMenu;
import demo.orangehrm.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BasePage extends Abstraction {
    public static final String testedLanguage = System.getProperty("language", "english").toLowerCase();
    private final WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void openUrl(String url) {
        webDriver.get(url);
        // Close download app float window in agoda if it is displayed
        try {
            WebElement closeDownloadAppBtn = webDriver.findElement(AgodaLandingPage.closeDownloadAppButton);
            if (closeDownloadAppBtn.isDisplayed()) closeDownloadAppBtn.click();
        } catch (NoSuchElementException e) {
            Log.info(this.getClass(), "Download app float is not displayed. Ignore close action!");
        }
        Log.info(this.getClass(), "Opened address successfully: ", url);
    }

    public String getCurrentUrl() {
        Log.info(this.getClass(), "Current URL is: ", webDriver.getCurrentUrl());
        return webDriver.getCurrentUrl();
    }

    public String getCssValueOf(String cssAttribute, By locator) {
        return webDriver.findElement(locator).getCssValue(cssAttribute);
    }

    public List<String> getStringListFromLocator(By locator) {
        List<WebElement> webElementList = webDriver.findElements(locator);
        List<String> result = new ArrayList<>();
        for (WebElement element : webElementList) {
            result.add(element.getText());
        }
        Log.info(this.getClass(), "String list from ", locator, " is ", result);
        return result;
    }

    /**
     * Check:
     * If current language same as testedLanguage then do nothing
     * Else set application language to testedLanguage
     *
     * @param testedLanguage expected language to be set
     */
    public void setAgodaAppLanguage(String testedLanguage) {
//        webDriver.findElement(new AgodaHeader(webDriver).userMenuButton).click();
        webDriver.findElement(new AgodaHeader(webDriver).userMenuButton).click();
        String currentLanguage = webDriver.findElement(AgodaUserMenu.selectedLanguageText).getText();
        String convertedLanguage = convertLanguageToUIDisplay(testedLanguage);
        if (!currentLanguage.equalsIgnoreCase(testedLanguage)) {
            webDriver.findElement(AgodaUserMenu.changeLanguageButton).click();
            By targetLanguageLocator = By.xpath(String.format("//p[text()=\"%s\"]", convertedLanguage));
            webDriver.findElement(targetLanguageLocator).click();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String convertLanguageToUIDisplay(String language) {
        switch (language.toLowerCase()) {
            case "english":
                return "English";
            case "vietnamese":
                return "Tiếng Việt";
            case "chinese":
                return "简体中文";
            case "german":
                return "Deutsch";
            case "italian":
                return "Italiano";
            case "korean":
                return "한국어";
            case "japanese":
                return "日本語";
            default:
                throw new RuntimeException("Unsupported language " + language);
        }
    }
}
