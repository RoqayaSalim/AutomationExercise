import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_case13 extends Test_Base{
  @Test(priority = 1)
  public void ViewProduct() {
	  WebElement element = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[2]/div"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    
	    // 1. Enhanced scroll to ensure element is properly visible
	    js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
	    try { Thread.sleep(500); } catch (InterruptedException e) {}
	    driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[2]/div/div[2]/ul/li/a")).click();
  }
  @Test (priority = 2)
  public void VerfiyView() {
	  String Details= driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[1]")).getText();
	  Assert.assertTrue(Details.contains("Category"));
  }
  
  @Test(priority = 3)
  public void increaseQuantity() {
	  WebElement QuBtn =driver.findElement(By.id("quantity"));
	  QuBtn.clear();
	  QuBtn.sendKeys("4");
	  
  }
  
  @Test(priority = 4)
  public void AddtoCart() {
	  driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/button")).click();
	  try { Thread.sleep(500); } catch (InterruptedException e) {}
	  driver.findElement(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a/u")).click();
  }
  
  @Test (priority = 5)
  public void checking() {
	  List<WebElement> FirstProdDetails = driver.findElements(By.id("product-1"));
	  HashMap<Integer, String> map =new HashMap<Integer, String>();
		 map.put(1, "Blue Top");
		 map.put(1, "Woman > Tops");
		 map.put(1, "Rs. 500");
		 map.put(1, "4");
		 map.put(1, "Rs. 2000");
		 for(int i=0 ;i<FirstProdDetails.size();i++) {
			 assertTrue(FirstProdDetails.get(i).getText().contains(map.get(1)));}
  }
  
  
  
}
