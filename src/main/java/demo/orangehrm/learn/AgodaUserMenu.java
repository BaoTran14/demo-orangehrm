package demo.orangehrm.learn;

import org.openqa.selenium.By;

public class AgodaUserMenu {
    public static final By selectedLanguageText = By.xpath("//button[contains(@aria-describedby,\"language\")]/span");
    public static final By changeLanguageButton = By.xpath("//button[contains(@aria-describedby,\"language\")]");
}
