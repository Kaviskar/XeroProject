import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class Modules extends ReusableMethods{
	
	public static void launchApp() throws IOException, InterruptedException{
		
        String dtTablePath = "C:/Users/Vikas/Desktop/TrainingMaterial/Xero/Framework/TestData/Data.xls";
		
		String[][] recData = readExcel(dtTablePath, "Sheet5");
		
		login(recData[1][0], recData[1][1]);
		
	}
	
	public static void login(String email, String password) throws IOException, InterruptedException{
		driver.get("https://login.xero.com/");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		WebElement email_id = driver.findElement(By.xpath("//*[@id='email']"));
		enterText(email_id, email, "Email id");
	    WebElement pwd = driver.findElement(By.xpath("//*[@id='password']"));
		enterText(pwd, password, "Password");
		WebElement login = driver.findElement(By.xpath("//*[@id='submitButton']"));
		clickObj(login, "Login Button");
		Thread.sleep(5000);
	}
		
	
	public static void freeTrial() throws IOException, InterruptedException{

		driver.get("https://www.xero.com/us/");
		driver.manage().window().maximize();
		WebElement freeTrial = driver.findElement(By.xpath("//a[@class='btn btn-primary global-ceiling-bar-btn']"));
		clickObj(freeTrial, "Free Trial button");
		Thread.sleep(2000);
		
	}

}
