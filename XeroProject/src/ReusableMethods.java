import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebElement;


public class ReusableMethods extends Driver{
	
	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String htmlname;
	static String objType;
	static String objName;
	static String TestData;
	static String rootPath;
	static int report;


	static Date cur_dt = null;
	static String filenamer;
	static String TestReport;
	int rowcnt;
	static String exeStatus = "True";
	static int iflag = 0;
	static int j = 1;

	static String fireFoxBrowser;
	static String chromeBrowser;

	static String result;

	static int intRowCount = 0;
	static String dataTablePath;
	static int i;
	static String browserName;
	
	
	public static void validateNewWindow(){
		String parent = driver.getWindowHandle();
		   for (String child : driver.getWindowHandles()) {
		    
		     if(!child.equals(parent)){
		     driver.switchTo().window(child);
		     String expected = "Xero Terms of Use | Xero";
		     String actual = driver.getTitle();
		     if(expected.equals(actual)){
		      System.out.println("Pass : Terms of Use page is opened");
		    //  driver.close();
		     }else{
		    	 System.out.println("Fail : This is not a Terms of Use page");
		     }
			
			}
		 }
	}
	
	public static void validateErrorMessage(String actualMessage,String ExpectedMessage) throws IOException{
		
        if(actualMessage.equals(ExpectedMessage)){
			
			Update_Report("Pass", "Error message ", "Proper error message is displayed on the page ");
		}
		else{
			
			Update_Report("Fail", "Error message ", "Improper error message is displayed on the page ");
			
		}
	}

	/* Name of the Method: enterText
	 * Brief Description: Enter text to text field
	 * Arguments: obj --> Object, textVal --> Text value to be entered , objName --> Object Name
	 * Created By: Nandini
	 * Creation Date : Dec 18 2017
	 * Last Modified : Dec 18 2017
	 * */
	public static void enterText(WebElement obj, String textVal, String objName) throws IOException{
		if(obj.isDisplayed()){
			obj.sendKeys(textVal);
			Update_Report("Pass", "enterText", textVal+ " is entered in " + objName + " field");
			System.out.println("Pass: " + textVal+ " is entered in " + objName + " field");
		}else{
			Update_Report("Fail", "enterText", objName + " field is not displayed, please check your application.");
			System.out.println("Fail: " + objName + " field is not displayed, please check your application.");
		}
	}
	
	
	/* Name of the Method: clickObj 
	 * Brief Description: Click on object
	 * Arguments: obj --> Object, objName --> Object Name
	 * Created By: Nandini
	 * Creation Date : Dec 18 2017
	 * Last Modified : Dec 18 2017
	 * */
	public static void clickObj(WebElement obj, String objName) throws IOException{
		if(obj.isDisplayed()){
			obj.click();
			System.out.println("Pass: " + objName + " is clicked");
			Update_Report("Pass", "Click Object", objName + " is clicked");
		}else{
			System.out.println("Fail: " + objName + " field is not displayed, please check your application.");
			Update_Report("Fail", "Click Object", objName + " is not clicked");
		}
	}
	
	
	
	public static void startReport(String scriptName, String ReportsPath) throws IOException{

		String strResultPath = null;


		String testScriptName =scriptName;


		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") { 

			ReportsPath = "C:\\";
		}

		if (ReportsPath.endsWith("\\")) { 
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath + "Log" + "/" +testScriptName +"/"; 
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";



		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ "FireFox " + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");


	}
	
	public static void Update_Report(String Res_type,String Action, String result) throws IOException {
		String str_time;
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);
		if (Res_type.startsWith("Pass")) {
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ result + "</FONT></TD></TR>");

		} else if (Res_type.startsWith("Fail")) {
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "
					+ htmlname
					+ "  style=\"color: #FF0000\"> Failed </a>"

				+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
				+ result + "</FONT></TD></TR>");

		} 
	}
	
	
	/* Name of the Method: readExcel
	 * Brief Description: read data from excel sheet (.xls)
	 * Arguments: dtTablePath --> path of excel sheet, sheetName--> Name of the sheet
	 * Created By: Nandini
	 * Creation Date : Dec 18 2017
	 * Last Modified : Dec 18 2017
	 * */
	
	public static String[][] readExcel(String dtTablePath, String sheetName ) throws IOException{

		/*Step 1: Get the XL Path*/
		File xlFile = new File(dtTablePath);

		/*step2: Access the Xl File*/
		FileInputStream xlDoc = new FileInputStream(xlFile);

		/*Step3: Access the work book (POI jar file) */
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);

		/*Step4: Access the Sheet */
		HSSFSheet sheet = wb.getSheet(sheetName);

		int iRowCount = sheet.getPhysicalNumberOfRows();
		int iColCount = sheet.getRow(0).getLastCellNum();


		String[][] xlData = new String[iRowCount][iColCount];

		for(int i =0; i<iRowCount; i++){
			for(int j = 0; j <iColCount; j++){
				xlData[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();

			}

		}
		
		return xlData;
	}


}
