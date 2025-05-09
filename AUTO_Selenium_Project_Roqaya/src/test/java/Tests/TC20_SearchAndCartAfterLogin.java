package Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TC20_SearchAndCartAfterLogin {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        // إعداد تقرير ExtentReports
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/TC20_SearchAndCartAfterLogin.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        test = extent.createTest("TC20 - Search Product and Add to Cart After Login")
                     .assignCategory("Regression")
                     .assignAuthor("Roqruqa");

        // إعداد المتصفح
        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
        test.info("Opened Automation Exercise homepage");
    }

    @Test
    public void searchAndAddToCartAfterLogin() {
        try {
            // Click on 'Products'
            driver.findElement(By.xpath("//a[@href='/products']")).click();
            test.info("Clicked on 'Products'");

            Thread.sleep(2000);

            // Verify 'ALL PRODUCTS' is displayed
            WebElement allProducts = driver.findElement(By.xpath("//h2[@class='title text-center']"));
            Assert.assertTrue(allProducts.getText().contains("ALL PRODUCTS"));
            test.pass("'ALL PRODUCTS' section is visible");

            // Search for product
            driver.findElement(By.id("search_product")).sendKeys("Tshirt");
            driver.findElement(By.id("submit_search")).click();
            test.info("Searched for 'Tshirt'");

            // Verify 'SEARCHED PRODUCTS' is visible
            WebElement searchedHeader = driver.findElement(By.xpath("//h2[contains(text(),'Searched Products')]"));
            Assert.assertTrue(searchedHeader.isDisplayed());
            test.pass("'Searched Products' is visible");

            // Verify products visible
            WebElement product = driver.findElement(By.xpath("//div[@class='productinfo text-center']"));
            Assert.assertTrue(product.isDisplayed());
            test.pass("Searched product is displayed");

            // Scroll and click add to cart
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,600);");

            WebElement addToCartBtn = driver.findElement(By.xpath("(//a[@data-product-id])[1]"));
            addToCartBtn.click();
            test.info("Clicked 'Add to Cart' on product");

            Thread.sleep(2000);

            driver.findElement(By.xpath("//button[contains(text(),'Continue Shopping')]")).click();
            test.info("Clicked 'Continue Shopping'");

            // Go to cart
            driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
            WebElement cartItem = driver.findElement(By.xpath("//td[@class='cart_description']"));
            Assert.assertTrue(cartItem.isDisplayed());
            test.pass("Product is in cart");

            // Click login
            driver.findElement(By.xpath("//a[@href='/login']")).click();
            driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("testuser@example.com");
            driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("test123");
            driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
            test.info("User logged in");

            // Go to cart again
            driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
            WebElement cartAfterLogin = driver.findElement(By.xpath("//td[@class='cart_description']"));
            Assert.assertTrue(cartAfterLogin.isDisplayed());
            test.pass("Cart still contains product after login");

        } catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}
