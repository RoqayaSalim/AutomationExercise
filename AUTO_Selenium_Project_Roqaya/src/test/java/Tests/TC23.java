package Tests;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TC23 {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void setupReport() {
    	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/TC23.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

     ChromeOptions options = new ChromeOptions();
     options.addArguments("disable-save-password-bubble");
     WebDriver driver = new ChromeDriver(options);

     System.setProperty("webdriver.chrome.driver", "F:\\\\chromedriver-win64\\\\chromedriver.exe");

    

       
     
     
     
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("Reports/TC23_VerifyAddressDetails.html");
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setupBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void verifyAddressDetails() throws InterruptedException {
        test = extent.createTest("TC23 - Verify address details in checkout page");

        // Step 1 + 2
        driver.get("https://automationexercise.com");
        test.info("Navigated to automationexercise.com");

        // Step 3
        Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Website for automation practice']")).isDisplayed());
        test.pass("Home page is visible successfully");

        // Step 4
        driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']")).click();

        // Step 5
        driver.findElement(By.name("name")).sendKeys("Ruqruqa");
        String email = "ruqruqa" + System.currentTimeMillis() + "@example.com";
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
        driver.findElement(By.xpath("//button[normalize-space()='Signup']")).click();

        driver.findElement(By.id("id_gender2")).click();
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("days")).sendKeys("10");
        driver.findElement(By.id("months")).sendKeys("May");
        driver.findElement(By.id("years")).sendKeys("1998");
        driver.findElement(By.id("first_name")).sendKeys("Ruqayya");
        driver.findElement(By.id("last_name")).sendKeys("Salim");
        driver.findElement(By.id("address1")).sendKeys("123 Test Street");
        driver.findElement(By.id("state")).sendKeys("Dakahlia");
        driver.findElement(By.id("city")).sendKeys("Mansoura");
        driver.findElement(By.id("zipcode")).sendKeys("12345");
        driver.findElement(By.id("mobile_number")).sendKeys("01012345678");
        driver.findElement(By.xpath("//button[normalize-space()='Create Account']")).click();

        // Step 6
        Assert.assertTrue(driver.getPageSource().contains("ACCOUNT CREATED!"));
        test.pass("Account created successfully");
        
        Thread.sleep(3000); 

        try {
            // استخدام JavaScriptExecutor للتعامل مع الـ Alert
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.alert = function(){return true;}"); // محاولة التعامل مع الـ Alert يدويًا.
            
            // بعدها تعامل مع الـ Alert بالطريقة المعتادة
            Alert alert = driver.switchTo().alert();
            alert.accept();  // اضغط على "أوافق"
        } catch (Exception e) {
            System.out.println("لا يوجد Alert");
        }
        
        
        
        Thread.sleep(3000);

        WebElement Continue = driver.findElement(By.id("Continue"));

       
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", "Continue");



        // Step 7
        Assert.assertTrue(driver.findElement(By.xpath("//a[normalize-space()='Logged in as Ruqruqa']")).isDisplayed());
        test.pass("User is logged in");

        // Step 8
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Add to cart')])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//u[normalize-space()='View Cart']")).click();

        // Step 9 + 10
        Assert.assertTrue(driver.getCurrentUrl().contains("view_cart"));
        test.pass("Cart page is displayed");

        // Step 11
        driver.findElement(By.xpath("//a[normalize-space()='Proceed To Checkout']")).click();

        // Step 12 + 13
        String deliveryAddress = driver.findElement(By.xpath("//ul[@id='address_delivery']")).getText();
        String billingAddress = driver.findElement(By.xpath("//ul[@id='address_invoice']")).getText();

        Assert.assertTrue(deliveryAddress.contains("123 Test Street"));
        Assert.assertTrue(billingAddress.contains("123 Test Street"));
        test.pass("Delivery and Billing addresses are as entered during registration");

        // Step 14
        driver.findElement(By.xpath("//a[normalize-space()='Delete Account']")).click();

        // Step 15
        Assert.assertTrue(driver.getPageSource().contains("ACCOUNT DELETED!"));
        test.pass("Account deleted successfully");

        driver.findElement(By.xpath("//a[normalize-space()='Continue']")).click();

        // Close browser
        driver.quit();
        test.info("Browser closed successfully");
    }

    @AfterTest
    public void tearDownReport() {
        extent.flush();
    }
}