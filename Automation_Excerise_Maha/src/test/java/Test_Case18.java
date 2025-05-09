import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_Case18 extends Test_Base {
  
    @Test(priority = 1)
    public void homePageVerification() {
        String title = driver.getTitle();
        assertEquals(title, "Automation Exercise", "Incorrect Title");
    }
    
    @Test(priority = 2)
    public void verifyCategoriesVisible() {
    	 WebElement element = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[1]/div/h2"));
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    
		    // 1. Enhanced scroll to ensure element is properly visible
		    js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
		    try { Thread.sleep(500); } catch (InterruptedException e) {}
        assertTrue(driver.findElement(By.xpath("/html/body/section[2]/div/div/div[1]/div/h2")).isDisplayed());
    }
    
    @Test(priority = 3)
    public void clickWomenCategory() {
        driver.findElement(By.xpath("//*[@id=\"accordian\"]/div[1]/div[1]/h4/a")).click();
    }
    
    @Test(priority = 4)
    public void clickWomenSubcategory() {
    	try { Thread.sleep(500); } catch (InterruptedException e) {}
        driver.findElement(By.xpath("//*[@id=\"Women\"]/div/ul/li[1]/a")).click();
    }
    
    @Test(priority = 5)
    public void verifyWomenCategoryPage() {
        String categoryText = driver.findElement(By.cssSelector("body > section > div > div.row > div.col-sm-9.padding-right > div > h2")).getText();
        Assert.assertTrue(categoryText.contains("WOMEN"));
    }
    
    @Test(priority = 6)
    public void clickMenSubcategory() {
        driver.findElement(By.xpath("//*[@id=\"accordian\"]/div[2]/div[1]/h4/a")).click();
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        driver.findElement(By.xpath("//*[@id=\"Men\"]/div/ul/li[1]/a")).click();
    }
    
    @Test(priority = 7)
    public void verifyMenCategoryPage() {
        String categoryText = driver.findElement(By.cssSelector("body > section > div > div.row > div.col-sm-9.padding-right > div > h2")).getText();
        Assert.assertTrue(categoryText.contains("MEN") );
    }

  }

