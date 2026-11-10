package demo.orangehrm;

import demo.orangehrm.pages.AdminPage;
import demo.orangehrm.pages.BasePage;
import demo.orangehrm.pages.LoginPage;
import demo.orangehrm.pages.NavigationPanel;
import demo.orangehrm.utils.DriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAdminFeature {
    private WebDriver driver;
    private BasePage basePage;
    DriverUtils driverUtils;

    @BeforeMethod
    public void prepareTestCondition() {
        //Init browser
        driverUtils = new DriverUtils();
        driver = driverUtils.initBrowser();
        //Access orange url
        basePage = new BasePage(driver);
        basePage.openUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterMethod
    public void tearDown() {
        //Quit browser
        driverUtils.quitBrowser(driver);
    }

    @Test
    void testRecordCheckBoxBehavior() {
        // Login account
        LoginPage loginPage = new LoginPage(driver);

        //Verify login button is enabled event username and password are not filled
        boolean loginButtonIsEnabled = loginPage.checkLoginButtonIsEnabled();
        Assert.assertTrue(loginButtonIsEnabled, "Login button is not enabled");
        loginPage.inputUsername("Admin");
        loginPage.inputPassword("admin123");
        loginPage.clickLoginButton();

        NavigationPanel navigationPanel = new NavigationPanel(driver);
        navigationPanel.clickAdminMenu();

        AdminPage adminPage = new AdminPage(driver);
        boolean firstRecordCheckBoxIsChecked = adminPage.isFirstRecordCheckBoxChecked();
        Assert.assertFalse(firstRecordCheckBoxIsChecked, "Default status of 1st record checkbox is checked!");
        adminPage.clickFirstRecordCheckBox();
        firstRecordCheckBoxIsChecked = adminPage.isFirstRecordCheckBoxChecked();
        Assert.assertTrue(firstRecordCheckBoxIsChecked, "1st record checkbox is not checked!");
    }
}
