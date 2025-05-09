package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class TestCase19 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void setUp() {
        // إعداد التقرير
    	

    	ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/TestCase19_Report.html");
    	extent = new ExtentReports();
    	extent.attachReporter(sparkReporter);

        test = extent.createTest("Test Case 19 - Verify Brand Products", "Click on brands and verify brand pages");

        // إعداد المتصفح
        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // الدخول للموقع
        driver.get("https://automationexercise.com");
        test.info("Opened Automation Exercise website");
    }

    @Test
    public void testBrandPages() {
        try {
            // الضغط على Products
            WebElement productsMenu = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a"));
            productsMenu.click();
            test.info("Clicked on 'Products'");

            Thread.sleep(3000);

            // التأكد إن جزء الـ Brands ظاهر
            WebElement brandsSection = driver.findElement(By.xpath("//div[@class='brands_products']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brandsSection);
            Thread.sleep(2000);

            Assert.assertTrue(brandsSection.isDisplayed(), "Brands section is not visible");
            test.pass("Brands section is visible");

            // الضغط على Polo
            WebElement brandPolo = driver.findElement(By.xpath("//a[@href='/brand_products/Polo']"));
            brandPolo.click();
            Thread.sleep(2000);

            WebElement poloHeader = driver.findElement(By.xpath("//h2[@class='title text-center']"));
            Assert.assertTrue(poloHeader.getText().contains("BRAND - POLO"));
            test.pass("Polo brand page is displayed");

            // الضغط على H&M
            WebElement brandHM = driver.findElement(By.xpath("//a[@href='/brand_products/H&M']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brandHM);
            brandHM.click();
            Thread.sleep(2000);

            WebElement hmHeader = driver.findElement(By.xpath("//h2[@class='title text-center']"));
            Assert.assertTrue(hmHeader.getText().contains("BRAND - H&M"));
            test.pass("H&M brand page is displayed");

        } catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}
