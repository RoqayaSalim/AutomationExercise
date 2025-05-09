import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Test_Case11 extends Test_Base {
  
	@Test(priority = 1)
	  public void homePageVerfication() {
		String title =driver.getTitle();
		System.out.println(title);
		assertEquals(title, "Automation Exercise","Incorrect Title");
	  }
	  
	
   @Test (priority = 2)
  public void CartBtnverfiy() {
	   driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[3]/a")).click();
	   
   }
   
   @Test(priority = 3)
   public void ScrollDown() {
 	  JavascriptExecutor js =(JavascriptExecutor) driver;
 	  js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");  
   }
   @Test(priority = 4)
   public void VerfiySub() {
 	  String text = driver.findElement(By.xpath("//*[@id=\"footer\"]/div[1]/div/div/div[2]/div/h2")).getText();
 	  assertEquals(text, "SUBSCRIPTION");
   }
   
   @Test (priority = 5)
   public void EmailBttn() {
 	  driver.findElement(By.id("susbscribe_email")).sendKeys("mahamky74@gmail.com");
 	  driver.findElement(By.id("subscribe")).click();
   }
   @Test (priority = 6)
   
   public void SucessMsg() {
 	  WebElement alretMsg = driver.findElement(By.xpath("//*[@id=\"success-subscribe\"]/div"));
 	  Assert.assertTrue(alretMsg.getText().contains("You have been successfully subscribed!"));
   }
   
  
}
