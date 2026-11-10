package demo.orangehrm.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.lang.reflect.Method;
import java.time.Duration;

public class DriverUtils {
    private final Logger logger = LogManager.getLogger(this.getClass());
    public WebDriver initBrowser() {
        String browserName = System.getProperty("browser", "chrome"); // Get system property. If null return default "chrome"
        WebDriver webDriver;
        switch (browserName) {
            case "chrome":
                webDriver = new ChromeDriver();
                break;
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            case "edge":
                webDriver = new EdgeDriver();
                break;
            case "safari":
                webDriver = new SafariDriver();
                break;
            default:
                throw new RuntimeException(String.format("Unsupported browser: %s. Allow [\"chrome\",\"firefox\", \"edge\"]", browserName));
        }
        logger.info("Initialize browser {} {}", browserName, "...");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //Set implicit wait
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(3)); //Set page load timeout max
        return webDriver;
    }

    public void quitBrowser(WebDriver webDriver) {
        Log.info(this.getClass(), "Quit driver: ", webDriver);
        webDriver.quit();
    }
}
