package demo.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

public class AdminPage extends BasePage {
    private final WebDriver webDriver;
    private final By firstRecordCheckBox = By.xpath("//*[contains(@class, \"table-body\")]/*[1]//input[@type=\"checkbox\"]");

    public AdminPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public boolean isFirstRecordCheckBoxChecked() {
        return webDriver.findElement(firstRecordCheckBox).isSelected();
    }

    public void clickFirstRecordCheckBox() {
        By checkboxLocator = new ByChained(firstRecordCheckBox, By.xpath(".//parent::*"));
        webDriver.findElement(checkboxLocator).click();
        //        chromeDriver.findElement(firstRecordCheckBox).findElement(By.xpath(".//parent::*")).click();
    }
}
