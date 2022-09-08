package Utility_Methods;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.InputStream;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
public class InitialSetup{

	
	
	public Object[][] setupNew(String VersionNumber, String ApplicationName, String Region, String Env,
			String URL, String ReleaseMonth,String Type_of_Testing, String Browser) throws Exception{
		
		Properties prop = new Properties();
		File file = new File("Resources/Env.properties");  
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();}
	try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();}
		
		
		Object[][] objCSVWebDriver = new Object[1][2];
		CSVFileWriter CSVFileWriterNew = new CSVFileWriter();
		CSVFileWriterNew.setCommonData(URL,VersionNumber,Region,Env,ApplicationName,ReleaseMonth,Type_of_Testing);
        WebDriver driver = null;
		
		
		if (prop.getProperty("GRID").equalsIgnoreCase("No")) {
			try {
				if (Browser.equals("Chrome")) {
					if (prop.getProperty("PLATFORM").contains("win")) {
						System.setProperty("webdriver.chrome.driver",
								System.getProperty("user.dir") + "/Resources/Drivers/chromedriver.exe");
					} else if (prop.getProperty("PLATFORM").contains("LINUX")) {
						System.setProperty("webdriver.chrome.driver",
								System.getProperty("user.dir") + "/Resources/Drivers/LINUX/chromedriver");
					}
					ChromeOptions options = new ChromeOptions();
					options.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
					driver = new ChromeDriver(options);
					driver.get(URL);
					driver.manage().window().maximize();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	
			driver.manage().window().maximize(); 
			driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		
		
    	objCSVWebDriver[0][0]=driver;
    	objCSVWebDriver[0][1]=CSVFileWriterNew;
		return objCSVWebDriver;
	}
	 

	 
	 //Quitting Browser CSV Commands
	public void tearDown( String fileName,WebDriver driver,CSVFileWriter CSVWriterNew,String ApplicationName, String Region, String Env, String ReleaseMonth, String DashboardName, String Browser,String execution_start_time,String ReportPath)
	 {
		try{
			driver.quit();
		    CSVWriterNew.writeCsvList("Quit Browser", "Validate Quit Browser", "Exit browser",Browser+ " browser should quit",Browser+" browser has quit", "Pass");	
		}
		 catch(Exception e) 
		 {
		 CSVWriterNew.writeCsvList("Quit Browser", "Validate Quit Browser", "Exit browser",Browser+ " browser should quit",Browser+" browser has quit", "Fail");
		}
		
		finally{
			Utilitymethods obj = new Utilitymethods();
	        String curr_date=obj.getCurrentDate();
			String FinalfileName = ReportPath+fileName+"_"+curr_date+".csv";
			CSVWriterNew.writeCsvFile(FinalfileName);
		}
	  }		
}

