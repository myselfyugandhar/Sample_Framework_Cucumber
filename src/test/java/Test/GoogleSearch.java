package Test;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utility_Methods.CSVFileWriter;
import Utility_Methods.Utilitymethods;

public class GoogleSearch {
	 WebDriver driver;
	 CSVFileWriter CSVWriterNew;
	 XWPFDocument doc;
	    Utility_Methods.WordDocument WordDocument;
	    public String Browser;
	    public String SplunkVersionNumber = "8.0.5";
	    public WebElement elementBeforeshadowRoot1; 
	    public GoogleSearch(WebDriver driver,CSVFileWriter CSVWriterNew,XWPFDocument doc)
	    {
	    this.driver = driver;
	    this.CSVWriterNew = CSVWriterNew;
	    this.doc = doc;
	    PageFactory.initElements(driver, this);
	    }
		public void GoogleSearch(XSSFSheet sheet,String execution_start_Time,String TestResultsDocPath,String TestResultsScreenshotsPath) throws Exception
		{
		try
		
		{		
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).sendKeys(sheet.getRow(1).getCell(0).getStringCellValue());
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[2]/div[2]/div[5]/center/input[1]")).click();
		Thread.sleep(5000);
		Utilitymethods obj = new Utilitymethods();
		
		obj.captureScreenshot("Yugandhar_Royal_SearchResult",driver,execution_start_Time,TestResultsScreenshotsPath);
		WordDocument.pasteScreenshotToWord(doc,"Yugandhar_Royal_SearchResult",TestResultsScreenshotsPath,"Taking Search Results for Yugandhar Royal","Document Name",TestResultsDocPath);
	
		
			Thread.sleep(1000);
		System.out.println("Successfully searched for Yugandhar Royal");
		CSVWriterNew.writeCsvList( "Search for Yugandhar Royal", "Searching for Yugandhar Royal", "Searching for Yugandhar Royal", "Results should found for Yugandhar Royal", "Results found for Yugandhar Royal", "Pass");
		}
		catch(Exception e)
		{System.out.println("Failed to search for Yugandhar Royal");
			CSVWriterNew.writeCsvList( "Search for Yugandhar Royal", "Searching for Yugandhar Royal", "Searching for Yugandhar Royal", "Results should found for Yugandhar Royal", "No Results found for Yugandhar Royal", "Fail");
			}}
}
