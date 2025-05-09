package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class TC23 {
    WebDriver driver = new FirefoxDriver();
    String baseURL = "https://automationexercise.com/";
    private ExtentReports extent;
    private ExtentTest test;
    private JavascriptExecutor js;

    @BeforeTest
    public void setup() {
        // Initialize ExtentReports
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("Reports/TC23_VerifyAddressDetails.html");
        extent.attachReporter(spark);
        
        // Browser setup
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to(baseURL);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void verifyAddressDetails() throws InterruptedException {
        test = extent.createTest("TC23 - Verify address details in checkout page");
        
        // Step 1-3: Verify home page
        Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Website for automation practice']")).isDisplayed());
        test.pass("Home page is visible successfully");

        // Step 4-7: Sign up and verify account creation
        driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']")).click();
        
        String email = "ruqruqa" + System.currentTimeMillis() + "@example.com";
        driver.findElement(By.name("name")).sendKeys("Ruqruqa");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
        driver.findElement(By.xpath("//button[normalize-space()='Signup']")).click();
        
        // Fill account information
        driver.findElement(By.id("id_gender2")).click();
        driver.findElement(By.id("password")).sendKeys("password123");
        new Select(driver.findElement(By.id("days"))).selectByVisibleText("10");
        new Select(driver.findElement(By.id("months"))).selectByVisibleText("May");
        new Select(driver.findElement(By.id("years"))).selectByVisibleText("1998");
        driver.findElement(By.id("first_name")).sendKeys("Ruqayya");
        driver.findElement(By.id("last_name")).sendKeys("Salim");
        driver.findElement(By.id("address1")).sendKeys("123 Test Street");
        driver.findElement(By.id("state")).sendKeys("Dakahlia");
        driver.findElement(By.id("city")).sendKeys("Mansoura");
        driver.findElement(By.id("zipcode")).sendKeys("12345");
        driver.findElement(By.id("mobile_number")).sendKeys("01012345678");
        driver.findElement(By.xpath("//button[normalize-space()='Create Account']")).click();
        
        Assert.assertTrue(driver.getPageSource().contains("ACCOUNT CREATED!"));
        test.pass("Account created successfully");
        
        handleAlert();
        clickContinue();
        
        Assert.assertTrue(driver.findElement(By.xpath("//a[normalize-space()='Logged in as Ruqruqa']")).isDisplayed());
        test.pass("User is logged in");

        // Step 8-10: Add product to cart
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Add to cart')])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//u[normalize-space()='View Cart']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("view_cart"));
        test.pass("Cart page is displayed");

        // Step 11-13: Verify addresses
        driver.findElement(By.xpath("//a[normalize-space()='Proceed To Checkout']")).click();
        
        String deliveryAddress = driver.findElement(By.xpath("//ul[@id='address_delivery']")).getText();
        String billingAddress = driver.findElement(By.xpath("//ul[@id='address_invoice']")).getText();

        Assert.assertTrue(deliveryAddress.contains("123 Test Street"));
        Assert.assertTrue(billingAddress.contains("123 Test Street"));
        test.pass("Addresses verified successfully");

        // Step 14-15: Delete account
        driver.findElement(By.xpath("//a[normalize-space()='Delete Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("ACCOUNT DELETED!"));
        test.pass("Account deleted successfully");
        driver.findElement(By.xpath("//a[normalize-space()='Continue']")).click();
    }

    private void handleAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            test.info("No alert present");
        }
    }

    private void clickContinue() throws InterruptedException {
        Thread.sleep(3000);
        WebElement continueButton = driver.findElement(By.xpath("//a[contains(text(),'Continue')]"));
        js.executeScript("arguments[0].click();", continueButton);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            test.info("Browser closed successfully");
        }
        extent.flush();
    }
}