import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Driver {

	static WebDriver driver;
	public static void main(String[] args) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		String dtTablePath = "C:/Users/Vikas/Desktop/TrainingMaterial/Xero/Framework/TestSuit.xls";
		
		String[][] recData = ReusableMethods.readExcel(dtTablePath, "Sheet4");
		
		String testCase;
		for(int i = 1; i <recData.length; i++){
			
			String flag=recData[i][1];
			if(flag.equalsIgnoreCase("Y")){
				
				String firefox= recData[i][3];
				if(firefox.equalsIgnoreCase("Y")){
					testCase = recData[i][2];
					System.out.println(testCase);
					ReusableMethods.startReport(testCase, "C:/Users/Vikas/Desktop/TrainingMaterial/Xero/Framework/TestResults/Firefox/Report/");
					System.setProperty("webdriver.gecko.driver", "C:/Users/Vikas/Desktop/TrainingMaterial/Xero/Framework/Lib/geckodriver.exe");
					driver = new FirefoxDriver();
					Method tc = AutomationScripts.class.getMethod(testCase);
					tc.invoke(tc);
					driver.quit();

					ReusableMethods.bw.close();
	
				}
				
				String chrome= recData[i][4];
				if(chrome.equalsIgnoreCase("Y")){
					testCase = recData[i][2];
					ReusableMethods.startReport(testCase, "C:/Users/Vikas/Desktop/TrainingMaterial/Xero/Framework/TestResults/Chrome/Report/");
					System.setProperty("webdriver.chrome.driver", "C:/Users/Vikas/Desktop/TrainingMaterial/Xero/Framework/Lib/chromedriver_win32/chromedriver.exe");
					driver = new ChromeDriver();
					Method tc = AutomationScripts.class.getMethod(testCase);
					tc.invoke(tc);
					driver.quit();

					ReusableMethods.bw.close();
				}
				
				
				String IE= recData[i][5];
				if(IE.equalsIgnoreCase("Y")){
					testCase = recData[i][2];
					ReusableMethods.startReport(testCase, "C:/Users/Vikas/Desktop/TrainingMaterial/Xero/Framework/TestResults/IE/Report/");
					System.setProperty("webdriver.ie.driver", "C:/Users/Vikas/Desktop/IEDriverServer_Win32_2.39.0/IEDriverServer.exe");
					driver = new InternetExplorerDriver();
					Method tc = AutomationScripts.class.getMethod(testCase);
					tc.invoke(tc);
					driver.quit();

					ReusableMethods.bw.close();
				}
				
				
				
			}
			
		}


	}

}
