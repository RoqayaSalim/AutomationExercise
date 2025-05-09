package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class 	TC21{

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void setUp() {
    	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/TC21.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        System.setProperty("webdriver.chrome.driver", "F:\\\\chromedriver-win64\\\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize(); // اختياري: يفتح المتصفح بالحجم الكامل
        driver.get("https://automationexercise.com");
    }

    @Test
    public void addProductToCart() throws InterruptedException {
        // Click on 'Products' page
        WebElement productsMenu = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a"));
        productsMenu.click();

        // Wait briefly for page to load (ممكن نستخدم WebDriverWait لاحقًا)
        Thread.sleep(3000);
       // 6. Verify  ALL PRODUCTS  is displayed
        WebElement  ALLPRODUCTS = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        Assert.assertTrue(ALLPRODUCTS.getText().contains("ALL PRODUCTS"));
     // Step 5: Click on 'View Product' button of first product
        driver.findElement(By.xpath("(//a[contains(text(),'View Product')])[1]")).click();

        // Step 6: Verify 'Write Your Review' is visible
     // Step 6: Scroll down to 'Write Your Review' section and verify it is visible
        WebElement reviewSection = driver.findElement(By.id("review-form"));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reviewSection);

        Assert.assertTrue(reviewSection.isDisplayed());
        System.out.println("Write Your Review section is visible.");

        // Step 7: Enter name, email and review
        driver.findElement(By.id("name")).sendKeys("Ruqayya");
        driver.findElement(By.id("email")).sendKeys("ruqayya@example.com");
        driver.findElement(By.id("review")).sendKeys("This is a great product!");
        Thread.sleep(3000);
        // Step 8: Click 'Submit' button
        driver.findElement(By.id("button-review")).click();

        // Step 9: Verify success message 'Thank you for your review.'
        WebElement successMsg = driver.findElement(By.xpath("//span[contains(text(),'Thank you for your review.')]"));
        Assert.assertTrue(successMsg.isDisplayed());
        System.out.println("Review submitted successfully. Message displayed: " + successMsg.getText());
    }

    
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
}

