package demo.orangehrm.pages;

import demo.orangehrm.utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TopHeader extends BasePage {
    private final WebDriver webDriver;
    private final By pageTitleHeading = By.cssSelector("span[class*=\"topbar-header-breadcrumb\"]");
    private final By profileImage = By.cssSelector("img[class*=\"userdropdown-img\"]");
    public TopHeader(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public String getPageTitle() {
        return webDriver.findElement(pageTitleHeading).getText();
    }

    public boolean getDisplayOfProfileImage() {
        return webDriver.findElement(profileImage).isDisplayed();
    }
}
