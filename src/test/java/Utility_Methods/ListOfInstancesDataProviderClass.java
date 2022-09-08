package Utility_Methods;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ListOfInstancesDataProviderClass {
	@DataProvider()
    public static Map<String, String> dataMethod() {
		Map<String,String> hashMap  = new HashMap<String,String>();
		String[][] arrayExcelData = null ;
    	try{
    		 String filePath = System.getProperty("user.dir")+"/Resources/InputData/";
    		 System.out.println("filePath check:"+filePath);
    		 String fileName = "InputSheet.xlsx";
    		 String sheetName = "ListOfInstances";
    		 
    		 File file =    new File(filePath+fileName);
    		 System.out.println(filePath+fileName);
    		 FileInputStream inputStream = new FileInputStream(file);
    		 Workbook workbook = null;
    		 String fileExtensionName = fileName.substring(fileName.indexOf("."));
    		 if(fileExtensionName.equals(".xlsx")){
    			 workbook = new XSSFWorkbook(inputStream);
    		 }
    		 else if(fileExtensionName.equals(".xls")){
    			 workbook = new HSSFWorkbook(inputStream);
    		 }

    		 Sheet sheet = workbook.getSheet(sheetName);
    		 System.out.println("No of rows " + sheet.getLastRowNum());
    		
    		 int rowCount = sheet.getLastRowNum();
    		 int colCount = sheet.getRow(0).getLastCellNum();
    		 arrayExcelData = new String[rowCount][colCount];
    		 for (int i = 1; i <=rowCount;i++) {
    			 Row row = sheet.getRow(i);
    			 Row headerRow = sheet.getRow(0);
    			 System.out.println("Row Number : "+i);
    			 int colcount=row.getLastCellNum();
    				 System.out.println("No of columnss "+row.getLastCellNum());
        			 for (int j = 0; j < colcount; j++) {
        				 System.out.println(row.getCell(j).getStringCellValue());
        				 arrayExcelData[i-1][j]=row.getCell(j).getStringCellValue();
        				 hashMap.put(headerRow.getCell(j).getStringCellValue(),row.getCell(j).getStringCellValue());
        			 }
    		 }
    	} catch (Exception e) {
    		e.printStackTrace();
    		}
		return hashMap;	
    } 	
}
