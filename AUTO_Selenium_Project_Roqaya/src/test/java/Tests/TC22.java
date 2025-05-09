package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TC22 {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setUp() {
    	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/TC22.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

    	        System.setProperty("webdriver.chrome.driver", "F:\\\\chromedriver-win64\\\\chromedriver.exe");

    	        ChromeOptions options = new ChromeOptions();
    	        options.addArguments("--disable-notifications");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void addRecommendedItemToCart() throws InterruptedException {
        driver.get("https://automationexercise.com");

        // Scroll to bottom
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);

        // Verify 'RECOMMENDED ITEMS' are visible
        WebElement recommendedTitle = driver.findElement(By.xpath("//h2[contains(text(),'recommended items')]"));
        Assert.assertTrue(recommendedTitle.isDisplayed(), "'RECOMMENDED ITEMS' is not visible.");

        // Ensure carousel is active
        WebElement carousel = driver.findElement(By.id("recommended-item-carousel"));
        Assert.assertTrue(carousel.isDisplayed(), "Recommended carousel is not visible.");

        // Choose specific product inside carousel
        WebElement addToCartButton = driver.findElement(By.xpath("(//div[@id='recommended-item-carousel']//a[@class='btn btn-default add-to-cart'])[1]"));

        // Use JavaScript click to avoid ElementClickInterceptedException
        js.executeScript("arguments[0].click();", addToCartButton);

        // Wait and then click on 'View Cart'
        Thread.sleep(2000);
        WebElement viewCartBtn = driver.findElement(By.xpath("//u[normalize-space()='View Cart']"));
        viewCartBtn.click();

        // Verify that product is in the cart
        WebElement productInCart = driver.findElement(By.xpath("//td[@class='cart_description']"));
        Assert.assertTrue(productInCart.isDisplayed(), "Product is not displayed in the cart.");
    }
        
       
    public void tearDown() {
        driver.quit();
        extent.flush();
    }   
            
    }
