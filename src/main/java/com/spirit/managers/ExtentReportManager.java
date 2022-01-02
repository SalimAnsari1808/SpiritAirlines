package com.spirit.managers;

import java.io.File;

//import com.relevantcodes.extentreports.DisplayOrder;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.spirit.utility.TestUtil;
import com.aventstack.extentreports.ExtentReports;
import org.testng.ITestContext;

public class ExtentReportManager {

	private ExtentHtmlReporter extentHtmlReporter;
	private static ExtentReports extent;
	//private static Platform platform;
	//private static String reportFileName = "ExtentReports-Version3-Test-Automaton-Report.html";
	//private static String macPath = System.getProperty("user.dir")+ "/TestReport";
	//private static String windowsPath = System.getProperty("user.dir")+ "\\TestReport";
	//private static String macReportFileLoc = macPath + "/" + reportFileName;
	//private static String winReportFileLoc = windowsPath + "\\" + reportFileName;

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	//Create an extent report instance
	public static ExtentReports createInstance() {
		//platform = getCurrentPlatform();
		//String fileName = getReportFileLocation(platform);
		String appURL = "";
		String sysInfomation;

		//check the
		if(FileReaderManager.getInstance().getConfigReader().getApplicationURL().contains("qaepic")){
			appURL = "QAEpic_";
			sysInfomation = "Production";
		}else if(FileReaderManager.getInstance().getConfigReader().getApplicationURL().contains("nav")) {
			appURL = "Nav01_";
			sysInfomation = "Testing";
		}

		//String css = ".report-name { padding-left: 10px; } .report-name > img { float: left;height: 90%;margin-left: 30px;margin-top: 2px;width: auto; }";


		String fileName =  appURL + TestUtil.currentDateTimeStringForReport()+".html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/reports/"+fileName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().enableTimeline(false);
		htmlReporter.config().setDocumentTitle("Spirit Automation Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Spirit Automation Report");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		//extent.setSystemInfo("",);



		return extent;
	}

	//Select the extent report file location based on platform
	/*private static String getReportFileLocation (Platform platform) {
		String reportFileLocation = null;
		switch (platform) {
			case MAC:
				reportFileLocation = macReportFileLoc;
				createReportPath(macPath);
				System.out.println("ExtentReport Path for MAC: " + macPath + "\n");
				break;
			case WINDOWS:
				reportFileLocation = winReportFileLoc;
				createReportPath(windowsPath);
				System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
				break;
			default:
				System.out.println("ExtentReport path has not been set! There is a problem!\n");
				break;
		}
		return reportFileLocation;
	}

	//Create the report path if it does not exist
	private static void createReportPath (String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!" );
			} else {
				System.out.println("Failed to create directory: " + path);
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
	}

	//Get current platform
	private static Platform getCurrentPlatform () {
		if (platform == null) {
			String operSys = System.getProperty("os.name").toLowerCase();
			if (operSys.contains("win")) {
				platform = Platform.WINDOWS;
			} else if (operSys.contains("nix") || operSys.contains("nux")
					|| operSys.contains("aix")) {
				platform = Platform.LINUX;
			} else if (operSys.contains("mac")) {
				platform = Platform.MAC;
			}
		}
		return platform;
	}







	
	private static ExtentReports extent;
	
	public synchronized  static ExtentReports getInstance(String suiteEnv)  {
		if(extent==null) {
			String fileName = suiteEnv + TestUtil.currentDateTimeString()+".html";
			extent = new ExtentReports(System.getProperty("user.dir")+ "/reports/"+fileName,true,DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir")+ FileReaderManager.getInstance().getConfigReader().getReportExtentPath()));
			return extent;
		}else {
			return extent;
		}

	}

	public synchronized  static void getCloseInstance(){
		extent.flush();
		try{
			extent.close();
		}catch(Throwable t){

		}
		extent=null;
	}*/

}

