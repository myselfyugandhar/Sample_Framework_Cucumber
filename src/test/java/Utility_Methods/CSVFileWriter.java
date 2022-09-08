package Utility_Methods;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
	public class CSVFileWriter {		
		public CSVFormatReporterNew1 reporter;
		int StepNo=0;
		String ExecutionTime;
        Date cur_dt = null;
        String URL;
    	String AppVersionNumber;
    	String Region;
    	String Env;
    	String ApplicationName;
    	String ReleaseMonth;
    	String Type_Of_Testing;
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		public List<CSVFormatReporterNew1> CSVList = new ArrayList<CSVFormatReporterNew1>();
		final String FILE_HEADER = "Release Month,Application Version,Region,Environment,Application Name,URL,Type Of Testing,Step No,Test Script,TestStep Description,Test Step,Expected Result,Actual Result,Execution Time,Status";
		private String TypeOfTesting;
		private String TestScript;
		private String TestStepDescription;
		private String TestStep;
		private String ExpectedResult;
		private String ActualResult;
		private String Status;
		private Object TestCaseName;
		public void setCommonData(String URL,String AppVersionNumber,String Region,String Env,String ApplicationName, String ReleaseMonth,String Type_of_Testing){
			this.URL=URL;
			this.AppVersionNumber=AppVersionNumber;
			this.Region=Region;
			this.Env=Env;
			this.ApplicationName=ApplicationName;
			this.ReleaseMonth=ReleaseMonth;
			this.Type_Of_Testing=Type_of_Testing;
		}
		
		
		public void writeCsvList( String TestScript, String TestStepDescription, String  TestStep, String ExpectedResult, String ActualResult,
				String Status) {
			StepNo++;
			cur_dt = new Date();
            SimpleDateFormat simpledateformat = new SimpleDateFormat("MM-dd-yyyy 'at' HH:mm:ss z");
            ExecutionTime = simpledateformat.format(cur_dt);
			reporter = new CSVFormatReporterNew1(Type_Of_Testing, StepNo,  TestScript, TestStepDescription,  TestStep, ExpectedResult, ActualResult, ExecutionTime, Status, URL, AppVersionNumber, Region,Env, ApplicationName,ReleaseMonth);
            CSVList.add(reporter);
		}
		//Final Writing to excel
		public void writeCsvFile(String fileName) {
			FileWriter fileWriter = null;					
			try {
				fileWriter = new FileWriter(fileName);
				fileWriter.append(FILE_HEADER.toString());				
				fileWriter.append(NEW_LINE_SEPARATOR);	
				for (CSVFormatReporterNew1 tempReporter : CSVList) {	
					fileWriter.append(String.valueOf(tempReporter.getReleaseMonth()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(tempReporter.getAppVersionNumber()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(tempReporter.getRegion()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(tempReporter.getClientName()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(tempReporter.getApplicationName()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(tempReporter.getURL()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(tempReporter.getTypeOfTesting());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(tempReporter.getStepNo()));
					fileWriter.append(COMMA_DELIMITER);								
					fileWriter.append(tempReporter.getTestScript());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(tempReporter.getTestStepDescription());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(tempReporter.getTestStep());
					fileWriter.append(COMMA_DELIMITER);	
					fileWriter.append(tempReporter.getExpectedResult());
					fileWriter.append(COMMA_DELIMITER);	
					fileWriter.append(tempReporter.getActualResult());
					fileWriter.append(COMMA_DELIMITER);	
					fileWriter.append(tempReporter.getExecutionTime());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(tempReporter.getStatus()));
					fileWriter.append(NEW_LINE_SEPARATOR);					
				}
				System.out.println(CSVList);
				System.out.println("CSV file was created successfully !!!");				
			} catch (Exception e) {
				System.out.println("Error in CsvFileWriter !!!");
				e.printStackTrace();
			} finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					System.out.println("Error while flushing/closing fileWriter !!!");
	                e.printStackTrace();
				}
				CSVList.clear();
			}
		}

	}
	class CSVFormatReporterNew1
	{
		private long StepNo =10000;
		private String TestScript;
		private String TestStepDescription;
		private String TestStep;
		private String ExpectedResult;
		private String ActualResult;
		private String ExecutionTime;
		private String Status;
		private String URL;
		private String ClientName;
		private String AppVersionNumber;
		private String Region;
		private String ApplicationName;
		private String TestCaseName;
		private String TypeOfTesting;
		private String ReleaseMonth;
		public CSVFormatReporterNew1(String TypeOfTesting, long StepNo, String TestScript, String TestStepDescription,
				String TestStep, String ExpectedResult, String ActualResult, String ExecutionTime, String Status,
				String URL, String AppVersionNumber, String Region, String ClientName, String ApplicationName, String ReleaseMonth) {
			this.StepNo = StepNo;
			this.TypeOfTesting = TypeOfTesting;
			this.TestScript = TestScript;
			this.TestStepDescription = TestStepDescription;
			this.TestStep = TestStep;
			this.ExpectedResult = ExpectedResult;
			this.ActualResult = ActualResult;
			this.ExecutionTime = ExecutionTime;
			this.Status = Status;
			this.URL = URL;
			this.AppVersionNumber = AppVersionNumber;
			this.Region = Region;
			this.ClientName = ClientName;
			this.ApplicationName = ApplicationName;
			this.ReleaseMonth = ReleaseMonth;
			this.TestCaseName = TestCaseName;
		}
		public long getStepNo() {
			return StepNo;
		}
		public String getTypeOfTesting() {
			return TypeOfTesting;
		}
		public String getTestScript() {
			return TestScript;
		}
		public String getTestStepDescription() {
			return TestStepDescription;
		}
		public String getTestStep() {
			return TestStep;
		}
		public String getExpectedResult() {
			return ExpectedResult;
		}
		public String getActualResult() {
			return ActualResult;
		}
		public String getExecutionTime() {
			return ExecutionTime;
		}
		public String getStatus() {
			return Status;
		}
		public String getURL() {
			return URL;
		}
		public void setURL(String URL) {
			this.URL = URL;
		}
		public String getRegion() {
			return Region;
		}
		public void setRegion(String Region) {
			this.Region = Region;
		}
		public String getClientName() {
			return ClientName;
		}
		public void setClientName(String ClientName) {
			this.ClientName = ClientName;
		}
		public String getAppVersionNumber() {
			return AppVersionNumber;
		}
		public void setAppVersionNumber(String AppVersionNumber) {
			this.AppVersionNumber = AppVersionNumber;
		}
		public String getReleaseMonth() {
			return ReleaseMonth;
		}
		public void setReleaseMonth(String ReleaseMonth) {
			this.ReleaseMonth = ReleaseMonth;
		}
		public String getApplicationName() {
			return ApplicationName;
		}
		public void setApplicationName(String ApplicationName) {
			this.ApplicationName = ApplicationName;
		}
		public String getTestCaseName() {
			return TestCaseName;
		}
		public void setTestCaseName(String TestCaseName) {
			this.TestCaseName = TestCaseName;
		}
		
	}
