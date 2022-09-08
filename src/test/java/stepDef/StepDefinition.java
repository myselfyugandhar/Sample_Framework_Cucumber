package stepDef;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;


import Test.GoogleSearch;
import Test.GoogleSearch2;
import Utility_Methods.CSVFileWriter;
import Utility_Methods.InitialSetup;
import Utility_Methods.Utilitymethods;
import Utility_Methods.WordDocument;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class StepDefinition {
	WebDriver driver;
	CSVFileWriter CSVWriterNew;
	Object[][] objCSVWebDriver = null;
	InitialSetup objSetup = new InitialSetup();
	public XSSFWorkbook workbook;
	private String VersionNumber;
	public String outputCsvFileName;
	private String ApplicationName;
	private String Region;
	private String Env;
	private String URL;
	private String ReleaseMonth;
	public String DashboardName = "";
	private String Type_of_Testing="NA";
	public String execution_start_time;
	public XWPFDocument doc;
	public String outputDocName="dummy";
	public String TestResultsDocPath,ReportPath,TestResultsScreenshotsPath;
	public FileInputStream inputStream;
public XSSFSheet SearchYugandharRoyal,SearchCheGuevara,ListOfInstances;

	
	
	@Before()
	public void initialsetup1() throws IOException {
		Map<String,String> hashMap  = new HashMap<String,String>();
		Utility_Methods.ListOfInstancesDataProviderClass obj = new Utility_Methods.ListOfInstancesDataProviderClass();
		hashMap = obj.dataMethod();
		this.VersionNumber = hashMap.get("App Version");
		this.ApplicationName = hashMap.get("Application Name");
		this.Region = hashMap.get("Region");
		this.Env = hashMap.get("Environment");
		this.URL =hashMap.get("URL");
		this.ReleaseMonth=hashMap.get("NA");
		this.Type_of_Testing="Regression";
		this.inputStream = new FileInputStream(System.getProperty("user.dir")+"\\Resources\\InputData\\InputSheet.xlsx");
		this.workbook = new XSSFWorkbook(inputStream);	
		this.inputStream = new FileInputStream(System.getProperty("user.dir")+"\\Resources\\InputData\\InputSheet.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		this.SearchYugandharRoyal= workbook.getSheet("SearchYugandharRoyal");
		this.SearchCheGuevara= workbook.getSheet("SearchCheGuevara");
		this.ListOfInstances= workbook.getSheet("ListOfInstances");

	}
	
	

	@After()
	public void ExecutionTimeForTest() throws IOException, XmlException {String Browser = "Chrome";
		WordDocument.closingWordDoc(doc,outputDocName,TestResultsDocPath);
   CSVWriterNew.writeCsvList( "Time taken to Run the Test", "Time taken to Run the Test","Time taken to Run the Test","Time taken to Run the Test is :","~Minutes", "Pass");		
objSetup.tearDown(outputCsvFileName,driver, CSVWriterNew, ApplicationName, Region, Env, ReleaseMonth, DashboardName,Browser,execution_start_time,ReportPath);

	}
	@Parameters("Browser")
	@Given("Launching the browser")
	public void launching_the_browser() throws InterruptedException {String Browser = "Chrome";
		
		
		//Fetching Execution Start Date and Tie
				Utilitymethods obj = new Utilitymethods();
				this.execution_start_time=obj.getCurrentDate();
				//Setting up Path for Test Results Word Document
				this.TestResultsDocPath = System.getProperty("user.dir")+"/Resources/Test Results/Test Results_ "+execution_start_time+"/Word Document/";
		        File file1 = new File(TestResultsDocPath);
		        if (!file1.exists()) {
		        	try {
		        	file1.mkdirs();System.out.println("Path for Word Document Test Results : "+TestResultsDocPath);}
		        	catch(Exception e) {
		        		System.out.println("Failed to create Path for Word Document Test Results");}
		        }
		        
		        
		      //Setting up Path for Test Results CSV Report
		        this.ReportPath = System.getProperty("user.dir")+"/Resources/Test Results/Test Results_ "+execution_start_time+"/CSVReport/";
		        File file2 = new File(ReportPath);
		        if (!file2.exists()) {
		        	try{
		        		file2.mkdirs();
		        		System.out.println("Path for CSV Report Test Results : "+ReportPath);
		        	}catch(Exception e) {
		        		System.out.println("Failed to create Path for CSV Report Test Results");}	
		        }
		        
		        
		        //Setting up Path for Test Results Screenshots
		        this.TestResultsScreenshotsPath = System.getProperty("user.dir")+"/Resources/Test Results/Test Results_ "+execution_start_time+"/Screenshots/";
		        File file3 = new File(TestResultsScreenshotsPath);
		        if (!file3.exists()) {
		        	try{
		        	file3.mkdirs();
		        	System.out.println("Path for Screenshots Test Results : "+TestResultsScreenshotsPath);}
		        	catch(Exception e) {
		        		System.out.println("Failed to create Path for Screenshots Test Results");
		        	}
		        }
		        
		        
				//pumping common data
				try {
					objCSVWebDriver = objSetup.setupNew(VersionNumber, ApplicationName, Region, Env, URL, ReleaseMonth,Type_of_Testing, Browser);

				} catch (Exception e) {
					e.printStackTrace();
				}
				driver = (WebDriver) objCSVWebDriver[0][0];
				CSVWriterNew = (CSVFileWriter) objCSVWebDriver[0][1];
	}
	
	@Given("Searching for Yugandhar Royal")
	public void searching_for_yugandhar_royal() throws Exception {
		
		this.outputDocName="Yugandhar_Royal_Search_Result";	
		this.outputCsvFileName="Yugandhar_Royal_Search_Result";	
		this.doc=WordDocument.CreatingWordDocument("Yugandhar_Royal_Search_Result");
				GoogleSearch obj =new GoogleSearch(driver,CSVWriterNew,doc);
				obj.GoogleSearch(SearchYugandharRoyal,execution_start_time,TestResultsDocPath,TestResultsScreenshotsPath);
				
	}
	
	@Given("Searching for Che Guevara")
	public void searching_for_che_guevara() throws Exception {
		this.outputDocName="Che Guevara_Search_Result";
		this.outputCsvFileName="Che Guevara_Search_Result";	
	this.doc=WordDocument.CreatingWordDocument("Che Guevara_Search_Result");
			GoogleSearch2 obj =new GoogleSearch2(driver,CSVWriterNew,doc);
			obj.GoogleSearch(SearchCheGuevara,execution_start_time,TestResultsDocPath,TestResultsScreenshotsPath);
	}
	
	
}




