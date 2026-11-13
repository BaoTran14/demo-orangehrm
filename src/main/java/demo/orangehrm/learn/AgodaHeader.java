package demo.orangehrm.learn;

import demo.orangehrm.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AgodaHeader extends BasePage {
    public static By userMenuButton = By.xpath("//button[@aria-label=\"Account Menu\"]");

    public AgodaHeader(WebDriver webDriver) {
        super(webDriver);
        // Option 1
        switch (BasePage.testedLanguage) {
            case "vietnamese":
                userMenuButton = By.xpath("//button[@aria-label=\"Trình đơn Tài khoản\"]");
                break;
        }
    }

    // Option 2
    public static void initLocator() {
        switch (BasePage.testedLanguage) {
            case "vietnamese":
                userMenuButton = By.xpath("//button[@aria-label=\"Account Menu\"]");
                break;
        }
    }
}
