package demo.orangehrm.learn;

import demo.orangehrm.pages.BasePage;
import demo.orangehrm.utils.DriverUtils;
import demo.orangehrm.utils.TestDataHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestSelectMenu {
    DriverUtils driverUtils = new DriverUtils();
    WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        webDriver = driverUtils.initBrowser();
        BasePage basePage = new BasePage(webDriver);
        basePage.openUrl("https://demoqa.com/select-menu");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driverUtils.quitBrowser(webDriver);
    }

    @Test
    void select_menu_using_select_selenium_test() {
        WebElement colorSelector = webDriver.findElement(By.id("oldSelectMenu"));
        Select selection = new Select(colorSelector);
        // Select by value
        selection.selectByValue("1");
        // Select by text
        selection.selectByVisibleText("Black");
        // Select by index
        selection.selectByIndex(6);
        for (WebElement allSelectedOption : selection.getAllSelectedOptions()) {
            System.out.println(allSelectedOption.getText());
        }
        String firstOption = selection.getFirstSelectedOption().getText();
        System.out.println("first option " + firstOption);

        // Multiple selection
        Select multiSelect = new Select(webDriver.findElement(By.id("cars")));
        multiSelect.selectByContainsVisibleText("l");
        multiSelect.selectByValue("audi");
        multiSelect.selectByValue("saab");
        for (WebElement allSelectedOption : multiSelect.getAllSelectedOptions()) {
            System.out.println(allSelectedOption.getText());
        }
        multiSelect.deselectByValue("audi");
        multiSelect.deselectByIndex(0);
        multiSelect.deselectByVisibleText("Saab");
        for (WebElement allSelectedOption : multiSelect.getAllSelectedOptions()) {
            System.out.println(allSelectedOption.getText());
        }
    }
}
