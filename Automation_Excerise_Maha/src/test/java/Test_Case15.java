import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_Case15 extends Test_Base {
  
    @Test(priority = 1)
    public void homePageVerification() {
        String title = driver.getTitle();
        System.out.println(title);
        assertEquals(title, "Automation Exercise", "Incorrect Title");
    }
    
    @Test(priority = 2)
    public void clickSignupLoginButton() {
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")).click();
    }
    
    @Test(priority = 3)
    public void fillSignupDetails() {
        driver.findElement(By.name("name")).sendKeys("New User");
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")).sendKeys("newuser" + System.currentTimeMillis() + "@example.com");
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button")).click();
        
        // Fill account information
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys("securepassword");
        
        Select days = new Select(driver.findElement(By.id("days")));
        days.selectByVisibleText("10");
        
        Select months = new Select(driver.findElement(By.id("months")));
        months.selectByVisibleText("June");
        
        Select years = new Select(driver.findElement(By.id("years")));
        years.selectByVisibleText("1995");
        
        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();
        
        driver.findElement(By.id("first_name")).sendKeys("New");
        driver.findElement(By.id("last_name")).sendKeys("User");
        driver.findElement(By.id("address1")).sendKeys("456 Main St");
        
        Select country = new Select(driver.findElement(By.id("country")));
        country.selectByVisibleText("Canada");
        
        driver.findElement(By.id("state")).sendKeys("Ontario");
        driver.findElement(By.id("city")).sendKeys("Toronto");
        driver.findElement(By.id("zipcode")).sendKeys("M5V 2T6");
        driver.findElement(By.id("mobile_number")).sendKeys("4165551234");
        
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/form/button")).click();
    }
    
    @Test(priority = 4)
    public void verifyAccountCreated() {
        String accountCreatedText = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b")).getText();
        Assert.assertTrue(accountCreatedText.contains("ACCOUNT CREATED!"));
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
    }
    
    @Test(priority = 5)
    public void verifyLoggedIn() {
    	assertTrue(driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a,'Logged in as')]")).isDisplayed());
    }
    
    @Test(priority = 6)
    public void addProductsToCart() {
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
		   try { Thread.sleep(1000); } catch (InterruptedException e) {}
		   
		   WebElement contshop = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[1]/div/div/div[3]/button"));
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
    
    @Test(priority = 7)
    public void verifyCartPage() {
        assertTrue(driver.findElement(By.id("cart_info")).isDisplayed());
    }
    
    @Test(priority = 8)
    public void clickProceedToCheckout() {
        driver.findElement(By.xpath("//*[@id=\"do_action\"]/div[1]/div/div/a")).click();
    }
    
    @Test(priority = 9)
    public void verifyAddressAndReviewOrder() {
        assertTrue(driver.findElement(By.id("address_delivery")).isDisplayed());
        assertTrue(driver.findElement(By.id("cart_info")).isDisplayed());
    }
    
    @Test(priority = 10)
    public void enterCommentAndPlaceOrder() {
        driver.findElement(By.name("message")).sendKeys("Please deliver before 5pm");
        driver.findElement(By.xpath("//*[@id=\"cart_items\"]/div/div[7]/a")).click();
    }
    
    @Test(priority = 11)
    public void enterPaymentDetails() {
        driver.findElement(By.name("name_on_card")).sendKeys("New User");
        driver.findElement(By.name("card_number")).sendKeys("5555555555554444");
        driver.findElement(By.name("cvc")).sendKeys("321");
        driver.findElement(By.name("expiry_month")).sendKeys("10");
        driver.findElement(By.name("expiry_year")).sendKeys("2025");
    }
    
    @Test(priority = 12)
    public void clickPayAndConfirm() {
        driver.findElement(By.id("submit")).click();
    }
    
    @Test(priority = 13)
    public void verifyOrderSuccess() {
        String successMessage = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b")).getText();
        assertTrue(successMessage.contains("ORDER PLACED!"));
    }
    
//    @Test(priority = 14)
//    public void clickDeleteAccount() {
//        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a")).click();
//    }
//    
//    @Test(priority = 15)
//    public void verifyAccountDeleted() {
//        String accountDeletedText = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b")).getText();
//        assertEquals(accountDeletedText, "ACCOUNT DELETED!");
//        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
//    }
}