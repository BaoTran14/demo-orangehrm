package demo.orangehrm;

import demo.orangehrm.pages.BasePage;
import demo.orangehrm.pages.LoginPage;
import demo.orangehrm.pages.TopHeader;
import demo.orangehrm.utils.DriverUtils;
import demo.orangehrm.utils.Log;
import demo.orangehrm.utils.TestDataHelper;
import demo.orangehrm.utils.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestLogin {
    private WebDriver driver;
    private BasePage basePage;
    private DriverUtils driverUtils;
    private final User user = new User();

    @BeforeClass
    public static void setUp() {
        
    }
    
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

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        //Quit browser
        driverUtils.quitBrowser(driver);
    }

    @Test
    void testLoginSuccessfully() {
        // Login account
        LoginPage loginPage = new LoginPage(driver);

        //Verify login button is enabled event username and password are not filled
        boolean loginButtonIsEnabled = user.isOn(loginPage).checkLoginButtonIsEnabled();
        Assert.assertTrue(loginButtonIsEnabled, "Login button is not enabled");
        user.isOn(loginPage).inputUsername(TestDataHelper.getDataOf("admin_username"));
        user.isOn(loginPage).inputPassword(TestDataHelper.getDataOf("admin_password"));
        user.isOn(loginPage).clickLoginButton();
        // Verify: Url/Dashboard page/Profile image
        String currentUrl = basePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"));
        // getText()

        TopHeader topHeader = new TopHeader(driver);
        String currentPageTitle = user.isOn(topHeader).getPageTitle();
        Assert.assertEquals(currentPageTitle, TestDataHelper.getDataOf("dashboard_page_title"));
        // isVisible()
        boolean profileImageDisplay = user.isOn(topHeader).getDisplayOfProfileImage();
        Assert.assertTrue(profileImageDisplay, "Profile image is not displayed!");
    }

    @Test
    void loginSuccessfullyUsingForm() {
        // Login account
        LoginPage loginPage = new LoginPage(driver);

        //Verify login button is enabled event username and password are not filled
        boolean loginButtonIsEnabled = user.isOn(loginPage).checkLoginButtonIsEnabled();
        Assert.assertTrue(loginButtonIsEnabled, "Login button is not enabled");
        user.isOn(loginPage).inputUsername("Admin");
        user.isOn(loginPage).inputPassword("admin123");
        user.isOn(loginPage).submitFormLogic();
        // Verify: Url/Dashboard page/Profile image
        String currentUrl = basePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"));
        // getText()

        TopHeader topHeader = new TopHeader(driver);
        String currentPageTitle = user.isOn(topHeader).getPageTitle();
        Assert.assertEquals(currentPageTitle, "Dashboard");
        // isVisible()
        boolean profileImageDisplay = user.isOn(topHeader).getDisplayOfProfileImage();
        Assert.assertTrue(profileImageDisplay, "Profile image is not displayed!");
    }

    @Test
    void verifyLoginFormCssStyleAndTagAttribute() {
        // Login account
        LoginPage loginPage = new LoginPage(driver);

        //Check background login button color
        String loginButtonBackgroundColor = user.isOn(loginPage).getBackgroundColorOfLoginButton();
        String loginButtonColor = user.isOn(loginPage).getColorOfLoginButton();
        Assert.assertEquals(loginButtonBackgroundColor, "rgba(21, 244, 233, 1)");
        Assert.assertEquals(loginButtonColor, "rgba(6, 0, 0, 1)");
        String usernameAttributeName = user.isOn(loginPage).getAttributeOfUserName();
        Assert.assertEquals(usernameAttributeName, "username");
    }
}
