package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener
{
	 public ExtentReports extent;
	    public ExtentTest logger;
	    public ExtentSparkReporter sparkReporter;

	    // Called before the tests start
	    public void onStart(ITestContext testContext) {
	        // Generate a timestamp for the report file name
	        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	        String repName = "Test-Report-" + timeStamp + ".html";

	        // Set up SparkReporter with file path
	        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

	        // Set the configuration for the report
	        sparkReporter.config().setDocumentTitle("Test Automation Report"); // Title of report
	        sparkReporter.config().setReportName("Test Execution Report"); // Name of the report
	      //  sparkReporter.config().setTheme(ExtentSparkReporter.Theme.DARK); // Dark theme

	        // Initialize the ExtentReports instance
	        extent = new ExtentReports();

	        // Attach the reporter to the ExtentReports instance
	        extent.attachReporter(sparkReporter);

	        // Add system information
	        extent.setSystemInfo("Host", "localhost");
	        extent.setSystemInfo("Environment", "QA");
	        extent.setSystemInfo("User", "TestUser");
	    }

	    // Called when a test is successful
	    public void onTestSuccess(ITestResult tr) {
	        logger = extent.createTest(tr.getName()); // Create a new test entry in the report
	        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // Pass info with green color
	    }

	    // Called when a test fails
	    public void onTestFailure(ITestResult tr) {
	        logger = extent.createTest(tr.getName()); // Create a new test entry in the report
	        logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // Fail info with red color

	        // Capture screenshot (if exists) and add it to the report
	        String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";
	        File f = new File(screenshotPath);
	        if (f.exists()) {
	            logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
	        }
	    }

	    // Called when a test is skipped
	    public void onTestSkipped(ITestResult tr) {
	        logger = extent.createTest(tr.getName()); // Create a new test entry in the report
	        logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE)); // Skip info with orange color
	    }

	    // Called after all tests finish
	    public void onFinish(ITestContext testContext) {
	        // Finalize and generate the report
	        extent.flush();
	    }
}
