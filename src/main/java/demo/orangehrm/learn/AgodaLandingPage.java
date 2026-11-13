package demo.orangehrm.learn;

import demo.orangehrm.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class AgodaLandingPage extends BasePage {
    public static final By closeDownloadAppButton = By.xpath("//button[contains(@data-element-name, \"app-download\")]");
    private final WebDriver webDriver;
    private final By welcomeMsg = By.cssSelector("h1");
    private final By tabNameList = By.xpath("//div[@role=\"tablist\"]/div//p");
    public AgodaLandingPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public String getWelcomeMessage() {
        return webDriver.findElement(welcomeMsg).getText();
    }

    public List<String> getTabNameList() {
        return getStringListFromLocator(tabNameList);
    }
}
