package Selenium_project.Automation_Excerise;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class Login_tests {
	 WebDriver driver = new ChromeDriver();
	 String baseURL ="https://the-internet.herokuapp.com/login";
	 
	 @BeforeTest
	 public void openBrowser() {
		 driver.navigate().to(baseURL);
	 }
	 
	 
	 @Test
	 public void testTitle1() {
		 String ActualTitle = driver.getTitle();
		 String ExpectedResult ="The Internet";
		 Assert.assertEquals(ActualTitle, ExpectedResult); 
	 }
	 @Test
	 public void testTitle2() {
		 String ActualTitle = driver.getTitle();
		 String ExpectedResult ="The internet";
		 Assert.assertEquals(ActualTitle, ExpectedResult); 
	 }
	 
	 @AfterTest
	 public void closeBrowser() { driver.close();}
	 
}
