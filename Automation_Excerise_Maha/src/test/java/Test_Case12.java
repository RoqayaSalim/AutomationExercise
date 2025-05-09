import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_Case12 extends Test_Base {
	
	
	@Test(priority = 1)
	  public void homePageVerfication() {
		String title =driver.getTitle();
		System.out.println(title);
		assertEquals(title, "Automation Exercise","Incorrect Title");
	  }
	
	 @Test (priority = 2)
	  public void productsBtnverfiy() {
	  driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a")).click();
		   
	   }
	 @Test (priority = 3)
	 public void addFirstProductToCart() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    Actions actions = new Actions(driver);
		    WebElement element = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]"));
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    
		    // 1. Enhanced scroll to ensure element is properly visible
		    js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
		    
		    // 2. Small pause to allow scrolling to complete (remove in production if not needed)
		    try { Thread.sleep(500); } catch (InterruptedException e) {}
		    
		    WebElement firstProduct = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]"));
		    actions.moveToElement(firstProduct).perform();
		    
		    try { Thread.sleep(500); } catch (InterruptedException e) {}

		    
		   WebElement addToCart = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/a"));
		   addToCart.click();
		   wait.until(ExpectedConditions.elementToBeClickable(addToCart));
//		    WebElement alretMsg = driver.findElement(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[1]"));
//		 	  Assert.assertTrue(alretMsg.getText().contains("added to cart"));
		    WebElement contshop = driver.findElement(By.xpath("//*[@id=\"cartModal\"]/div/div/div[3]/button"));
		    try { Thread.sleep(500); } catch (InterruptedException e) {}
		    contshop.click();
//		    wait.until(ExpectedConditions.elementToBeClickable(contshop));
		}
	 
	 @Test (priority = 4)
	 public void addsecondProductToCart() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    Actions actions = new Actions(driver);
		    WebElement element = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[3]/div/div[1]/div[1]"));
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    
		    // 1. Enhanced scroll to ensure element is properly visible
		    js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
		    
		    // 2. Small pause to allow scrolling to complete (remove in production if not needed)
		    try { Thread.sleep(500); } catch (InterruptedException e) {}
		    
		    WebElement SecondProduct = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[3]/div/div[1]/div[1]"));
		    actions.moveToElement(SecondProduct).perform();
		    
		    try { Thread.sleep(500); } catch (InterruptedException e) {}

		    
		   WebElement addToCart = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[3]/div/div[1]/div[2]/div/a"));
		   addToCart.click();
		   wait.until(ExpectedConditions.elementToBeClickable(addToCart));
//		    WebElement alretMsg = driver.findElement(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[1]"));
//		 	  Assert.assertTrue(alretMsg.getText().contains("added to cart"));
		    WebElement viewcart = driver.findElement(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a/u"));
		    try { Thread.sleep(500); } catch (InterruptedException e) {}
		    viewcart.click();
//		    wait.until(ExpectedConditions.elementToBeClickable(contshop));
		}
	 
	 @Test (priority = 5)
	 public void checking() {
		 List<WebElement> FirstProdDetails = driver.findElements(By.id("product-1"));
		 List<WebElement> SecondProdDetails = driver.findElements(By.id("product-2"));
		 HashMap<Integer, String> map =new HashMap<Integer, String>();
		 map.put(1, "Blue Top");
		 map.put(1, "Woman > Tops");
		 map.put(1, "Rs. 500");
		 map.put(1, "1");
		 map.put(1, "Rs. 500");
		 map.put(2, "Men Tshirt");
		 map.put(2, "Men > Tshirts");
		 map.put(2, "Rs. 400");
		 map.put(2, "1");
		 map.put(2, "Rs. 400");
		 for(int i=0 ;i<FirstProdDetails.size();i++) {
			 assertTrue(FirstProdDetails.get(i).getText().contains(map.get(1)));
			 assertTrue(SecondProdDetails.get(i).getText().contains(map.get(2))); 
		 }
	 }
	 
	
	
	
}
