package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.Duration;

public class TC25 {
    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setUp() {
    	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/TC25.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        System.setProperty("webdriver.chrome.driver", "F:\\\\chromedriver-win64\\\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // تعريف الـ wait هنا
    }

    @Test
    public void testScrollFunctionality() throws InterruptedException{ 
        driver.get("https://automationexercise.com");

        // Step 1 + 2 + 3: Verify Home Page is visible
        WebElement slider = driver.findElement(By.id("slider-carousel"));
        Assert.assertTrue(slider.isDisplayed(), "Home page is not visible.");

        Thread.sleep(2000);

        

        // Step 4: Scroll to bottom
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        

        // Step 5: Verify 'Subscription' is visible
        WebElement subscription = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Subscription')]")));
        Assert.assertTrue(subscription.isDisplayed(), "'Subscription' section is not visible.");
        Thread.sleep(2000);

        // Step 6: Click on arrow to scroll up
        WebElement scrollUp = wait.until(ExpectedConditions.elementToBeClickable(By.id("scrollUp")));
        scrollUp.click();

        // Step 7: Verify top text is visible
        WebElement topText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[text()='Full-Fledged practice website for Automation Engineers']")));
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
