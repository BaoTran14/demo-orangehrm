package demo.orangehrm;

import demo.orangehrm.pages.BasePage;
import demo.orangehrm.pages.LoginPage;
import demo.orangehrm.pages.TopHeader;
import demo.orangehrm.utils.DriverUtils;
import demo.orangehrm.utils.Log;
import demo.orangehrm.utils.TestDataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Properties;

public class TestLogin {
    private WebDriver driver;
    private BasePage basePage;
    private DriverUtils driverUtils;

//    @BeforeClass
//    public static void setUp() {
//        new TestDataHelper().loadTestData();
//    }

    @BeforeMethod
    public void prepareTestCondition(Method method) {
        Log.info(this.getClass(), "Start testing ", method.getName());
        //Init browser
        driverUtils = new DriverUtils();
        driver = driverUtils.initBrowser();
        //Access orange url
        basePage = new BasePage(driver);
        basePage.openUrl(TestDataHelper.getDataOf("orange_url"));
    }

    @AfterMethod
    public void tearDown() {
        //Quit browser
        driverUtils.quitBrowser(driver);
    }

    @Test
    void testLoginSuccessfully() {
        // Login account
        LoginPage loginPage = new LoginPage(driver);

        //Verify login button is enabled event username and password are not filled
        boolean loginButtonIsEnabled = loginPage.checkLoginButtonIsEnabled();
        Assert.assertTrue(loginButtonIsEnabled, "Login button is not enabled");
        loginPage.inputUsername(TestDataHelper.getDataOf("admin_username"));
        loginPage.inputPassword(TestDataHelper.getDataOf("admin_password"));
        loginPage.clickLoginButton();
        // Verify: Url/Dashboard page/Profile image
        String currentUrl = basePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"));
        // getText()

        TopHeader topHeader = new TopHeader(driver);
        String currentPageTitle = topHeader.getPageTitle();
        Assert.assertEquals(currentPageTitle, TestDataHelper.getDataOf("dashboard_page_title"));
        // isVisible()
        boolean profileImageDisplay = topHeader.getDisplayOfProfileImage();
        Assert.assertTrue(profileImageDisplay, "Profile image is not displayed!");
    }

    @Test
    void loginSuccessfullyUsingForm() {
        // Login account
        LoginPage loginPage = new LoginPage(driver);

        //Verify login button is enabled event username and password are not filled
        boolean loginButtonIsEnabled = loginPage.checkLoginButtonIsEnabled();
        Assert.assertTrue(loginButtonIsEnabled, "Login button is not enabled");
        loginPage.inputUsername("Admin");
        loginPage.inputPassword("admin123");
        loginPage.submitFormLogic();
        // Verify: Url/Dashboard page/Profile image
        String currentUrl = basePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"));
        // getText()

        TopHeader topHeader = new TopHeader(driver);
        String currentPageTitle = topHeader.getPageTitle();
        Assert.assertEquals(currentPageTitle, "Dashboard");
        // isVisible()
        boolean profileImageDisplay = topHeader.getDisplayOfProfileImage();
        Assert.assertTrue(profileImageDisplay, "Profile image is not displayed!");
    }

    @Test
    void verifyLoginFormCssStyleAndTagAttribute() {
        // Login account
        LoginPage loginPage = new LoginPage(driver);

        //Check background login button color
        String loginButtonBackgroundColor = loginPage.getBackgroundColorOfLoginButton();
        String loginButtonColor = loginPage.getColorOfLoginButton();
        Assert.assertEquals(loginButtonBackgroundColor, "rgba(21, 244, 233, 1)");
        Assert.assertEquals(loginButtonColor, "rgba(6, 0, 0, 1)");
        String usernameAttributeName = loginPage.getAttributeOfUserName();
        Assert.assertEquals(usernameAttributeName, "username");
    }
}
