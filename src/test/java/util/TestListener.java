package util;
import driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    private Logger log = LogManager.getRootLogger();
    @Override
    public void onTestStart(ITestResult Result) {
        System.out.println(Result.getName()+" test case started");
    }
    @Override
    public void onTestSuccess(ITestResult Result) {
        System.out.println("The name of the testcase passed is :"+Result.getName());
    }
    @Override
    public void onTestFailure(ITestResult Result) {
        saveScreenshot();
        System.out.println("The name of the testcase failed is :"+Result.getName());

    }
    @Override
    public void onTestSkipped(ITestResult Result) {
        System.out.println("The name of the testcase Skipped is :"+Result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }
    @Override
    public void onStart(ITestContext Result) {
        System.out.println("Testing start");
    }
    @Override
    public void onFinish(ITestContext Result) {
        System.out.println("Testing finish:");
    }

    private void saveScreenshot(){
        File screenCapture = ((TakesScreenshot)DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/"
                            + getCurrentTimeAsString() +
                            ".png"));
        } catch (IOException e) {
            log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd_HH-mm-ss" );
        return ZonedDateTime.now().format(formatter);
    }
}