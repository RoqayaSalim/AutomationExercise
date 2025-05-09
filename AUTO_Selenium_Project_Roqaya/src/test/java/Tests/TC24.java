package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.Duration;

public class TC24 {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setUp() {
    	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/TC24.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        System.setProperty("webdriver.chrome.driver", "F://chromedriver-win64//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testDownloadInvoice() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationexercise.com");

        // Step 3: Verify that home page is visible successfully
        WebElement homePageLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//img[@alt='Website for automation practice']")));
        Assert.assertTrue(homePageLogo.isDisplayed(), "Home page is not visible.");

        // Step 4: Add products to cart
        driver.findElement(By.xpath("(//a[@data-product-id='1'])[1]"))
                .click();
        driver.findElement(By.xpath("//button[text()='Continue Shopping']"))
                .click();

        // Step 5: Click 'Cart' button
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();

        // Step 6: Verify that cart page is displayed
        WebElement cartPage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//section[@id='cart_items']")));
        Assert.assertTrue(cartPage.isDisplayed(), "Cart page is not displayed.");

        // Step 7: Click Proceed To Checkout
        driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();

        // Step 8: Click 'Register / Login' button
        driver.findElement(By.xpath("//u[text()='Register / Login']")).click();

        // Step 9: Fill all details in Signup and create account
        driver.findElement(By.name("name")).sendKeys("Ruqruqa");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']"))
                .sendKeys("ruqruq24@example.com");
        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        // Fill account details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2"))).click();
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("days")).sendKeys("10");
        driver.findElement(By.id("months")).sendKeys("May");
        driver.findElement(By.id("years")).sendKeys("1998");
        driver.findElement(By.id("first_name")).sendKeys("Ruqayya");
        driver.findElement(By.id("last_name")).sendKeys("Elebedy");
        driver.findElement(By.id("address1")).sendKeys("Mansoura");
        driver.findElement(By.id("state")).sendKeys("Dakahlia");
        driver.findElement(By.id("city")).sendKeys("Mansoura");
        driver.findElement(By.id("zipcode")).sendKeys("12345");
        driver.findElement(By.id("mobile_number")).sendKeys("01124546201");
        driver.findElement(By.xpath("//button[text()='Create Account']")).click();

        // Step 10: Verify 'ACCOUNT CREATED!' and click 'Continue' button
        WebElement accountCreatedMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'ACCOUNT CREATED!')]")));
        Assert.assertTrue(accountCreatedMessage.isDisplayed(), "'ACCOUNT CREATED!' message is not visible.");
        driver.findElement(By.xpath("//a[text()='Continue']")).click();

        // Step 11: Verify 'Logged in as username' at top
        WebElement loggedInAs = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(text(),'Logged in as')]")));
        Assert.assertTrue(loggedInAs.isDisplayed(), "User is not logged in.");

        // Step 12: Click 'Cart' button again
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();

        // Step 13: Click 'Proceed To Checkout' button
        driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();

        // Step 14: Verify Address Details and Review Your Order
        WebElement addressDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='Address Details']")));
        Assert.assertTrue(addressDetails.isDisplayed(), "Address details are not visible.");

        // Step 15: Enter description in comment text area and click 'Place Order'
        driver.findElement(By.name("message")).sendKeys("Thanks!");
        driver.findElement(By.xpath("//a[text()='Place Order']")).click();

        // Step 16: Enter payment details
        driver.findElement(By.name("name_on_card")).sendKeys("Ruqayya");
        driver.findElement(By.name("card_number")).sendKeys("1234123412341234");
        driver.findElement(By.name("cvc")).sendKeys("123");
        driver.findElement(By.name("expiry_month")).sendKeys("12");
        driver.findElement(By.name("expiry_year")).sendKeys("2026");

        // Step 17: Click 'Pay and Confirm Order' button
        driver.findElement(By.id("submit")).click();

        // Step 18: Verify success message
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Your order has been placed successfully!')]")));
        Assert.assertTrue(successMessage.isDisplayed(), "Order success message is not visible.");

        // Step 19: Click 'Download Invoice' button
        driver.findElement(By.xpath("//a[text()='Download Invoice']")).click();
        Thread.sleep(3000); // Optional wait for file to download

        // Step 20: Click 'Continue' button
        WebElement continueButton = driver.findElement(By.id("Continue"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();","Continue" );

        // Step 21: Click 'Delete Account' button
        driver.findElement(By.xpath("//a[text()='Delete Account']")).click();

        // Step 22: Verify 'ACCOUNT DELETED!' and click 'Continue' button
        WebElement deletedMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'ACCOUNT DELETED!')]")));
        Assert.assertTrue(deletedMessage.isDisplayed(), "Account deletion message is not visible.");
        driver.findElement(By.xpath("//a[text()='Continue']")).click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            extent.flush();
        }
    }
}
