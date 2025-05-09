import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class Test_Base {
  WebDriver driver = new FirefoxDriver();
  String baseURL = "https://automationexercise.com/";
  @BeforeTest
  public void beforeTest() {
	  driver.manage().window().maximize();
	  driver.navigate().to(baseURL);
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
