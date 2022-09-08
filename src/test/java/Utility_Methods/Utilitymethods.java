package Utility_Methods;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.testng.ITestResult;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
public class Utilitymethods {
	public static WebElement ElementClickability(WebDriver driver,String xpath,Duration sec) {
		WebDriverWait wait = new WebDriverWait(driver,sec);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath))));
			return element;
			
	}
	public static WebElement ElementVisibility(WebDriver driver,String xpath,Duration sec) {
		WebDriverWait wait = new WebDriverWait(driver,sec);
		WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return e;	
	}
	public static WebElement ScrollInToView(WebDriver driver,String xpath,int sec) throws InterruptedException {
	      WebElement e=driver.findElement(By.xpath(xpath));
	      ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", e);
	      Thread.sleep(sec);
		return e;	
	}
	public static void captureScreenshot(String screenshotName,WebDriver driver,String execution_start_time,String TestResultsScreenshotsPath) throws IOException
	{
		TakesScreenshot screenshot=(TakesScreenshot) driver;
		
		File src=screenshot.getScreenshotAs(OutputType.FILE);
		
		try {
		File dest=new File(TestResultsScreenshotsPath+screenshotName + ".jpg");
		FileUtils.copyFile(src, dest);
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		//my code
		/*Utilitymethods obj = new Utilitymethods();
		WebElement e = obj.ScrollInToView(driver, xpath, 800);
		Shutterbug.shootPage(driver).save(System.getProperty("user.dir")+"\\Resources\\Screenshots\\fullPageScreenshot111.png");
		//Full page screenshot code:
		//Shutterbug.shootPage(driver, Capture.FULL ,500,true).save(System.getProperty("user.dir")+"\\Resources\\Screenshots\\fullPageScreenshot.png");
	*/
	}
	
	public static String getCurrentDate() 
	{
		
		 Date date = new Date();
	    SimpleDateFormat simpledateformat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		return simpledateformat.format(date);
		
	}
	public static WebElement ElementNotVisibility(WebDriver driver,String xpath,Duration sec) {
		WebDriverWait wait = new WebDriverWait(driver,sec);
		WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return e;	
	}
	
	public static WebElement ElementPresence(WebDriver driver,String xpath,Duration sec) {
		WebDriverWait wait = new WebDriverWait(driver,sec);
		WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		e = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		return e;	
	}
	
	
	
	

	
	
}
