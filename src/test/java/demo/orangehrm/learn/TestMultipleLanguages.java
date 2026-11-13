package demo.orangehrm.learn;

import demo.orangehrm.pages.BasePage;
import demo.orangehrm.utils.DriverUtils;
import demo.orangehrm.utils.TestDataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMultipleLanguages {
    DriverUtils driverUtils = new DriverUtils();
    WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        webDriver = driverUtils.initBrowser();
        BasePage basePage = new BasePage(webDriver);
        basePage.openUrl(TestDataHelper.getDataOf("agoda_url"));
        // Set test language
        basePage.setAgodaAppLanguage(BasePage.testedLanguage);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driverUtils.quitBrowser(webDriver);
    }

    @Test
    void test_landing_page_display() {
        // Verify welcome message
        AgodaLandingPage agodaLandingPage = new AgodaLandingPage(webDriver);
        String actualWelcomeMsg = agodaLandingPage.getWelcomeMessage();
        String designedWelcomeMessage = TestDataHelper.getDataOf("agoda_landing_page_welcome_message");
        Assert.assertEquals(actualWelcomeMsg.toUpperCase(), designedWelcomeMessage);
        // Verify tab list
        List<String> actualTabList = agodaLandingPage.getTabNameList();
        List<String> expectedTabList = Stream.of(TestDataHelper.getDataOf("agoda_landing_page_tab_name_list").split(",")).map(String::trim).collect(Collectors.toList());
        Assert.assertEquals(actualTabList, expectedTabList);
    }
}
