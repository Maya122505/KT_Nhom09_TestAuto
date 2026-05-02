package tests;

import common.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.LoginPage;

public class LoginTests {
    private static final Logger logger = LogManager.getLogger(LoginTests.class);

    @BeforeMethod
    public void beforeMethod() {
        logger.info("Mở trình duyệt Chrome và phóng to màn hình");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        logger.info("Đóng trình duyệt");
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit(); 
        }
    }

    @Test(description = "FDN_01 - Đăng nhập thành công với tài khoản hợp lệ")
    public void FDN_01() {
        logger.info("Thực thi FDN_01: Đăng nhập thành công với tài khoản hợp lệ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("login/"), "LỖI: Hệ thống chưa chuyển trang sau khi đăng nhập thành công!");
    }

    @Test(description = "FDN_02 - Đăng nhập thất bại do Email không tồn tại")
    public void FDN_02() {
        logger.info("Thực thi FDN_02: Đăng nhập thất bại do Email không tồn tại");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        loginPage.login("thuy@gmail.com", "123");
        
        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        Assert.assertEquals(actualErrorMsg.trim(), "Email hoặc mật khẩu không đúng", "Thông báo lỗi hiển thị không đúng như mong đợi!");
    }

    @Test(description = "FDN_03 - Đăng nhập thất bại do sai mật khẩu")
    public void FDN_03() {
        logger.info("Thực thi FDN_03: Đăng nhập thất bại do sai mật khẩu");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        loginPage.login(Constant.USERNAME, "123");
        
        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        Assert.assertEquals(actualErrorMsg.trim(), "Email hoặc mật khẩu không đúng", "Thông báo lỗi hiển thị không đúng như mong đợi!");
    }

    @Test(description = "FDN_04 - Đăng nhập với email để trống")
    public void FDN_04() {
        logger.info("Thực thi FDN_04: Đăng nhập với email để trống");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        loginPage.login("", Constant.PASSWORD);
        
        String validationMessage = loginPage.getTxtEmail().getAttribute("validationMessage");
        Assert.assertEquals(validationMessage, "Vui lòng điền vào trường này", "Thông báo validation của trình duyệt không đúng như mong đợi!");
    }

    @Test(description = "FDN_05 - Đăng nhập với mật khẩu để trống")
    public void FDN_05() {
        logger.info("Thực thi FDN_05: Đăng nhập với mật khẩu để trống");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        loginPage.login(Constant.USERNAME, "");
        
        String validationMessage = loginPage.getTxtPassword().getAttribute("validationMessage");
        Assert.assertEquals(validationMessage, "Vui lòng điền vào trường này", "Thông báo validation của trình duyệt không đúng như mong đợi!");
    }

    @Test(description = "FDN_06 - Kiểm tra khóa tài khoản khi sai 5 lần")
    public void FDN_06() {
        logger.info("Thực thi FDN_06: Kiểm tra khóa tài khoản khi sai 5 lần");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        for (int i = 1; i <= 5; i++) {
            loginPage.login(Constant.USERNAME, "123");
        }
        
        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        Assert.assertEquals(actualErrorMsg.trim(), "Tài khoản tạm khóa vì lý do bảo mật", "Hệ thống không báo khóa tài khoản như mong đợi!");
    }
}