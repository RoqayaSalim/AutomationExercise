import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_Case17 extends Test_Base {
	@Test(priority = 1)
	  public void homePageVerfication() {
		String title =driver.getTitle();
		System.out.println(title);
		assertEquals(title, "Automation Exercise","Incorrect Title");
	  }
	
	
	@Test(priority = 2)
	public void AddingProducts() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[2]/div"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
	    try { Thread.sleep(500); } catch (InterruptedException e) {}
	    
	    WebElement firstProduct = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[2]/div"));
	    actions.moveToElement(firstProduct).perform();
	    
	    try { Thread.sleep(500); } catch (InterruptedException e) {}
	    
	    WebElement addToCart1 = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[2]/div/div[1]/div[2]/div/a"));
		   addToCart1.click();
		   wait.until(ExpectedConditions.elementToBeClickable(addToCart1));
		   
		   WebElement contshop = driver.findElement(By.cssSelector("#cartModal > div > div > div.modal-footer > button"));
		    try { Thread.sleep(500); } catch (InterruptedException e) {}
		    contshop.click();
		    
		    WebElement secondProduct = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[3]"));
		    actions.moveToElement(secondProduct).perform();
		    
		    try { Thread.sleep(500); } catch (InterruptedException e) {}
		    
		    WebElement addToCart2 = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[3]/div/div[1]/div[2]/div/a"));
			   addToCart2.click();
			   wait.until(ExpectedConditions.elementToBeClickable(addToCart2));
			   
			    try { Thread.sleep(500); } catch (InterruptedException e) {}
			    contshop.click();  
			    driver.findElement(By.id("scrollUp")).click();
			    driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[3]/a")).click();
	}
	
	@Test (priority = 3)
	public void delete() {
		List<WebElement> elements = driver.findElements(By.className("cart_quantity_delete"));

		// Click each element
		for (WebElement element : elements) {
		    element.click();
		}
	}
	 @Test (priority = 4)
	 
	 public void checkempty() {
		 String emptyCart =driver.findElement(By.id("empty_cart")).getText();
	     Assert.assertTrue(emptyCart.contains("is empty!"));
	 }
	
	

}
