
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AutomationScripts extends ReusableMethods {
	
	public static void Navigate_to_XERO() throws IOException, InterruptedException{
				
	    Modules.launchApp();
		String title = driver.getCurrentUrl();
		
		if(title.equals("https://go.xero.com/Dashboard/")){
			Update_Report("Pass", "Home Page Navigation", "Navigated to Home page successfully");
		}
		else{
			Update_Report("Fail", "Home Page Navigation", "Unable to navigate to Home page");
		}
			
	}
	
	public static void Incorrect_Password() throws IOException, InterruptedException{
		
		String dtTablePath = "C:/Users/Vikas/Desktop/TrainingMaterial/Xero/Framework/TestData/Data.xls";
		String[][] recData = readExcel(dtTablePath, "Sheet5");
		
		Modules.login(recData[2][0], recData[2][1]);
		Thread.sleep(2000);
		
		String actualMessage = driver.findElement(By.xpath("//*[@id='contentTop']/div[2]/div[1]/div[2]/p")).getText();
		String ExpectedMessage = "Your email or password is incorrect";
		
		validateErrorMessage(actualMessage,ExpectedMessage);		
		
				
	}
	

	public static void Incorrect_Email() throws IOException, InterruptedException{
		
		String dtTablePath = "C:/Users/Vikas/Desktop/TrainingMaterial/Xero/Framework/TestData/Data.xls";
		String[][] recData = readExcel(dtTablePath, "Sheet5");
		
		Modules.login(recData[3][0], recData[3][1]);
		Thread.sleep(2000);
		
		String actualMessage = driver.findElement(By.xpath("//*[@id='contentTop']/div[2]/div[1]/div[2]/p")).getText();
		String ExpectedMessage = "Your email or password is incorrect";
		
		validateErrorMessage(actualMessage,ExpectedMessage);			
				
	}
	
	public static void Forgot_Password() throws IOException, InterruptedException{
		
		String dtTablePath = "C:/Users/Vikas/Desktop/TrainingMaterial/Xero/Framework/TestData/Data.xls";
		String[][] recData = readExcel(dtTablePath, "Sheet5");
		
		driver.get("https://login.xero.com/");
		driver.manage().window().maximize();
		WebElement forgotPwd = driver.findElement(By.xpath("//*[@id='contentTop']/div[2]/div[1]/a"));
		clickObj(forgotPwd, "Forgot Password link");
		
		Thread.sleep(3000);
		
		WebElement Email = driver.findElement(By.xpath("//*[@id='UserName']"));
		enterText(Email, recData[1][0], "Email");
		
		WebElement sendLink = driver.findElement(By.xpath("//*[@id='submitButton']/a"));
		clickObj(Email, "SendLink button");
		Thread.sleep(10000);	
		
       /* String actualMessage = driver.findElement(By.xpath("//*[@id='contentTop']/div/p[1]")).getText();
        
        System.out.println(actualMessage);*/
		
		//String ExpectedMessage = "Your email or password is incorrect";
		
		//validateErrorMessage(actualMessage,ExpectedMessage);*/			
		
	}
	
	public static void SignUp_to_XDC_A() throws IOException, InterruptedException{
		
        String dtTablePath = "C:/Users/Vikas/Desktop/TrainingMaterial/Xero/Framework/TestData/Data.xls";
		
		String[][] recData = readExcel(dtTablePath, "Sheet3");
		
		Modules.freeTrial();
		
		WebElement firstName = driver.findElement(By.xpath("//*[@name='FirstName']"));
		enterText(firstName, recData[1][0], "First Name");
		WebElement lastName = driver.findElement(By.xpath("//*[@name='LastName']"));
		enterText(lastName, recData[1][1], "Last Name");
		WebElement emailAddress = driver.findElement(By.xpath("//*[@name='EmailAddress']"));	
		enterText(emailAddress, recData[1][2], "Email Address");
		WebElement phoneNumber = driver.findElement(By.xpath("//*[@name='PhoneNumber']"));
		enterText(phoneNumber, recData[1][3], "Phone Number");
		
		WebElement selectmenu= driver.findElement(By.xpath("//*[@name='LocationCode']"));
		selectmenu.click();
		
		Thread.sleep(4000);
		
		String country = recData[1][4];
		 Select select=new Select(selectmenu);
		 select.selectByVisibleText(country);
			Thread.sleep(4000);
		
	    //code to find the index of an iframe
		/*List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		int size = frames.size();
		System.out.println(size);
		
		for(int i=0; i<size; i++){
			driver.switchTo().frame(i);
			int total=driver.findElements(By.xpath("//div[@class='recaptcha-checkbox-checkmark']")).size();
			System.out.println(total);
		    driver.switchTo().defaultContent();
		}*/
			
			driver.switchTo().frame(3);	
			WebElement robotCheckkbox = driver.findElement(By.xpath("//*[@id='recaptcha-anchor']/div[5]"));
			clickObj(robotCheckkbox, "Robot Checkkbox");
			Thread.sleep(4000);
			
			
	}
	
	public static void SignUp_to_XDC_B() throws IOException, InterruptedException{
		
		driver.get("https://www.xero.com/us/");
		driver.manage().window().maximize();
		WebElement freeTrial = driver.findElement(By.xpath("//a[@class='btn btn-primary global-ceiling-bar-btn']"));
		clickObj(freeTrial, "Free Trial button");
		Thread.sleep(2000);
		WebElement getStarted = driver.findElement(By.xpath("//*[@class='btn btn-primary btn-disabled']"));
		clickObj(getStarted, "getStarted"); // unable to click element is disabled - webdriver does not support disabled button
		Thread.sleep(2000);
		 
	}
	
	
   public static void SignUp_to_XDC_C() throws IOException, InterruptedException{
		
		Modules.freeTrial();
		WebElement termsOfUse = driver.findElement(By.linkText("terms of use"));
		clickObj(termsOfUse, "terms of use link"); 
		Thread.sleep(2000);
		
		String parent = driver.getWindowHandle();
		   for (String child : driver.getWindowHandles()) {
		    
		     if(!child.equals(parent)){
		     driver.switchTo().window(child);
		     String expected = "Xero Terms of Use | Xero";
		     String actual = driver.getTitle();
		     if(expected.equals(actual)){
		      System.out.println("Pass : Terms of Use page is opened");
		      Update_Report("Pass", "click on terms of use ", "Terms of Use page is opened");
		    //  driver.close();
		     }else{
		    	 System.out.println("Fail : This is not a Terms of Use page");
		    	 Update_Report("Pass", "click on terms of use ", "Terms of Use page does not opened");
		     }
			
			}
		 }
		   
		   driver.switchTo().window(parent);
		   Thread.sleep(5000);
		   
		   
		   WebElement privacyPolicy = driver.findElement(By.linkText("privacy policy"));
			clickObj(privacyPolicy, "privacy policy link"); 
			Thread.sleep(4000);
		  
			//results is getting as Fail 
			//String parent1 = driver.getWindowHandle();
			 for (String child : driver.getWindowHandles()) {
				    
			     if(!child.equals(parent)){
			     driver.switchTo().window(child);
			     String expected = "https://www.xero.com/us/signup/";
			     String actual = driver.getCurrentUrl();
			     System.out.println(actual);
			 
			     if(expected.equals(actual)){
			      System.out.println("Pass : Privacy Policy page is opened");
			      Update_Report("Pass", "click on privacy policy ", "Privacy Policy page is opened");
			      driver.close();
			     }else{
			    	 System.out.println("Fail : This is not a Privacy Policy page");
			    	 Update_Report("Fail", "click on privacy policy ", "This is not a Privacy Policy page");
			     }
				
				}
			 }
		  
	 }
   
   public static void SignUp_to_XDC_D() throws IOException, InterruptedException{
	   
	    Modules.freeTrial();
		WebElement offerDetails = driver.findElement(By.linkText("offer details"));
		clickObj(offerDetails, "offer details link"); 
		Thread.sleep(2000);
		
		String parent = driver.getWindowHandle();
		   for (String child : driver.getWindowHandles()) {
		    
		     if(!child.equals(parent)){
		     driver.switchTo().window(child);
		     String expected = "Offer details | Xero";
		     String actual = driver.getTitle();
		     if(expected.equals(actual)){
		      System.out.println("Pass : Offer details page is opened");
		      Update_Report("Pass", "click on offer details", "Offer details page is opened");
		    //  driver.close();
		     }else{
		    	 System.out.println("Fail : Offer details page does not opened");
		    	 Update_Report("Fail", "click on offer details", "Offer details page does not opened");
		     }
			
			}
		 }
		   
    }

   public static void SignUp_to_XDC_E() throws IOException, InterruptedException{
	   
	    Modules.freeTrial();
		WebElement offerDetails = driver.findElement(By.linkText("accountant or bookkeeper"));
		clickObj(offerDetails, "accountant or bookkeeper link"); 
		Thread.sleep(2000);
		
		String parent = driver.getWindowHandle();
		   for (String child : driver.getWindowHandles()) {
		    
		     if(!child.equals(parent)){
		     driver.switchTo().window(child);
		     String expected = "Sign up for the Xero Partner Program | Xero";
		     String actual = driver.getTitle();
		     if(expected.equals(actual)){
		      System.out.println("Pass : accountant or bookkeeper page is opened");
		      Update_Report("Pass", "click on accountant or bookkeeper", "Let's start a great partnership web page is opened");
		      driver.close();
		     }else{
		    	 System.out.println("Fail : accountant or bookkeeperpage does not opened");
		    	 Update_Report("Fail", "click on accountant or bookkeeper", "Let's start a great partnership web page does opened");
		     }
			
			}
		 }
		   
   }
   
   public static void Test_all_tabs_page() throws IOException, InterruptedException{
	   
        Modules.launchApp();
	      
	   List<WebElement> tags = driver.findElements(By.tagName("a"));
	   System.out.println(tags.size());
	   
	   WebElement dashboard = driver.findElement(By.xpath("//a[@id='Dashboard']"));
	   clickObj(dashboard, "Dashboard tab"); 
	   Thread.sleep(2000);
	   
	   WebElement accounts = driver.findElement(By.xpath("//a[@id='Accounts']"));
	   clickObj(accounts, "Accounts tab"); 
	   Thread.sleep(2000);
	   
	   WebElement reports = driver.findElement(By.xpath("//a[@id='Reports']"));
	   clickObj(reports, "Reports tab"); 
	   Thread.sleep(2000);
	   
	   WebElement contacts = driver.findElement(By.xpath("//a[@id='Contacts']"));
	   clickObj(contacts, "Contacts tab"); 
	   Thread.sleep(2000);
	   
	   WebElement settings = driver.findElement(By.xpath("//a[@id='Settings']"));
	   clickObj(settings, "Settings tab"); 
	   Thread.sleep(2000);
	   
	   WebElement newtab = driver.findElement(By.xpath("//a[@id='quicklaunchTab']"));
	   clickObj(newtab, "+ / new tab"); 
	   Thread.sleep(2000);
	   
	   WebElement files = driver.findElement(By.xpath("//a[@class='files']"));
	   clickObj(files, "files tab"); 
	   Thread.sleep(2000);
	   
	   WebElement notification = driver.findElement(By.xpath("//a[@data-js='inbox']"));
	   clickObj(notification, "notification tab"); 
	   Thread.sleep(2000);
	   
	   WebElement search = driver.findElement(By.xpath("//a[@class='search']"));
	   clickObj(search, "search tab"); 
	   Thread.sleep(2000);
	   
	   WebElement help = driver.findElement(By.xpath("//a[@class='help']"));
	   clickObj(help, "? / help tab"); 
	   Thread.sleep(2000);
   }
   
   
   public static void Test_Logout_Functionality() throws IOException, InterruptedException{
	   
	  /* String dtTablePath = "C:/Users/Vikas/Desktop/TrainingMaterial/Xero/Framework/TestData/Data.xls";
		
		String[][] recData = readExcel(dtTablePath, "Sheet5");
*/		
	    Modules.launchApp();
		
		Thread.sleep(3000);
		WebDriverWait wait=new WebDriverWait(driver, 20);
		WebElement usernameDrop = driver.findElement(By.xpath("//a[@class='username']"));
		wait.until(ExpectedConditions.elementToBeClickable(usernameDrop));
		clickObj(usernameDrop, "username Dropdown"); 
		Thread.sleep(2000);
		WebElement logout = driver.findElement(By.linkText("Logout"));
		clickObj(logout, "Logout button"); 
	   
   }
   
   public static void Create_Account() throws IOException, InterruptedException{
	   
	    Modules.launchApp();
		
		Thread.sleep(5000);
		
		Thread.sleep(3000);
		WebDriverWait wait=new WebDriverWait(driver, 20);
		WebElement usernameDrop = driver.findElement(By.xpath("//a[@class='username']"));
		wait.until(ExpectedConditions.elementToBeClickable(usernameDrop));
		clickObj(usernameDrop, "username Dropdown"); 
		Thread.sleep(4000);
		
		WebElement profile = driver.findElement(By.linkText("Profile"));
		clickObj(profile, "Profile button"); 
		Thread.sleep(2000);
		
		
		
		WebElement uploadImg = driver.findElement(By.xpath("//span[@id='button-1041-btnInnerEl']"));
		clickObj(uploadImg, "Upload Image button"); 
		Thread.sleep(5000);
	   
		/* //code to find the index of an iframe
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		int size = frames.size();
		System.out.println(size);
		
		for(int i=0; i<size; i++){
			driver.switchTo().frame(i);
			int total=driver.findElements(By.xpath("//input[@name='file']")).size();
			System.out.println(total);
		    driver.switchTo().defaultContent();
		}*/
		
		
		/*WebDriverWait wait1 =new WebDriverWait(driver, 30);
		WebElement browse = driver.findElement(By.id("filefield-1174-button-fileInputEl"));
		wait1.until(ExpectedConditions.presenceOfElementLocated((By) browse));
		browse.click();
		browse.sendKeys("C:/Users/Vikas/Desktop/Pic/testPic.jpg");
	    clickObj(uploadImg, "Upload Image button"); 
		Thread.sleep(2000);*/
	   
   }
   
} // end main
