package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.Duration;

public class TC26 {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setUp() {
    	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/TC26.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        System.setProperty("webdriver.chrome.driver", "F:\\\\chromedriver-win64\\\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testScrollWithoutArrow() throws InterruptedException {
        // Step 1 + 2: Launch browser and navigate to URL
        driver.get("https://automationexercise.com");

        // Step 3: Verify home page is visible successfully
        WebElement homeBanner = driver.findElement(By.id("slider-carousel"));
        if (homeBanner.isDisplayed()) {
            System.out.println("✅ Home page is visible.");
        } else {
            System.out.println("❌ Home page is not visible.");
        }

        // Step 4: Scroll down to bottom
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);

        // Step 5: Verify 'SUBSCRIPTION' is visible
        WebElement subscriptionText = driver.findElement(By.xpath("//h2[contains(text(),'Subscription')]"));
        Assert.assertTrue(subscriptionText.isDisplayed(), "'SUBSCRIPTION' is not visible.");

        // Step 6: Scroll up to top using JavaScript (without arrow)
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(2000);

        // Step 7: Verify top text is visible
        WebElement topText = driver.findElement(
                By.xpath("//*[text()='Full-Fledged practice website for Automation Engineers']"));
        Assert.assertTrue(topText.isDisplayed(), "Top text is not visible after scrolling up.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            extent.flush();
        }
    }
}

