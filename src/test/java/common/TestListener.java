package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        if (extent == null) {
            // Khởi tạo ExtentReports và đính kèm SparkReporter
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
            extent.attachReporter(spark);
        }
        System.out.println("===============================================");
        System.out.println("BẮT ĐẦU CHẠY SUITE: " + context.getName());
        System.out.println("===============================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("\n[ĐANG CHẠY] Test Case: " + result.getName() + " - " + result.getMethod().getDescription());
        // Đảm bảo extent không null trước khi tạo test
        if (extent != null) {
            ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
            extentTest.assignCategory(result.getTestClass().getName());
            test.set(extentTest);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[THÀNH CÔNG] \u2714\uFE0F Test Case Passed: " + result.getName());
        if (test.get() != null) {
            test.get().log(Status.PASS, "Test Case Passed: <b>" + result.getName() + "</b>");
            String screenshotPath = Utilities.captureScreenshotAndReturnPath("PASS", result.getName());
            if (screenshotPath != null) {
                attachScreenshotToReport(screenshotPath);
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.err.println("[THẤT BẠI] \u274C Test Case Failed: " + result.getName());
        if (result.getThrowable() != null) {
            System.err.println("Lý do lỗi: " + result.getThrowable().getMessage());
        }

        if (test.get() != null) {
            test.get().log(Status.FAIL, "Test Case Failed: <b>" + result.getName() + "</b>");

            if (result.getThrowable() != null) {
                test.get().log(Status.FAIL, "<pre style='color:red;'>" + result.getThrowable().getMessage() + "</pre>");
                test.get().log(Status.FAIL, result.getThrowable());
            }

            String screenshotPath = Utilities.captureScreenshotAndReturnPath("FAIL", result.getName());
            if (screenshotPath != null) {
                attachScreenshotToReport(screenshotPath);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[BỎ QUA] \u26A0\uFE0F Test Case Skipped: " + result.getName());
        if (test.get() != null) {
            test.get().log(Status.SKIP, "Test Case Skipped: " + result.getName());
            if (result.getThrowable() != null) {
                test.get().log(Status.SKIP, result.getThrowable());
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\n===============================================");
        System.out.println("ĐÃ CHẠY XONG SUITE: " + context.getName());
        System.out.println("Kết quả chi tiết được lưu tại: target/ExtentReport.html");
        System.out.println("===============================================");
        if (extent != null) {
            extent.flush();
        }
    }

    private void attachScreenshotToReport(String screenshotPath) {
        try {
            String base64Image = encodeFileToBase64Binary(new File(screenshotPath));
            if (base64Image != null && test.get() != null) {
                test.get().info("Screenshot", com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
            }
        } catch (Exception e) {
            System.err.println("Could not attach screenshot to report: " + e.getMessage());
        }
    }

    private String encodeFileToBase64Binary(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            System.err.println("Could not read screenshot file: " + e.getMessage());
            return null;
        }
    }
}