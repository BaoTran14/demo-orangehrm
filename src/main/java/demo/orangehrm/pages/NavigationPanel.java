package demo.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationPanel extends BasePage {
    private final WebDriver webDriver;
    private final By adminMenuItem = By.cssSelector("a[href*=\"viewAdmin\"]");
    public NavigationPanel(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void clickAdminMenu() {
        webDriver.findElement(adminMenuItem).click();
    }
}
