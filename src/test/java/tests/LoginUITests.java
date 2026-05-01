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

public class LoginUITests {
    private static final Logger logger = LogManager.getLogger(LoginUITests.class);

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

    @Test(description = "FDN_UI001 - [Email] Textbox - Status = enable")
    public void FDN_UI001() {
        logger.info("Thực thi FDN_UI001: Kiểm tra ô Email có được bật (enable) không");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        boolean isEmailEnabled = loginPage.getTxtEmail().isEnabled();
        Assert.assertTrue(isEmailEnabled, "Lỗi: Ô nhập Email đang bị tắt (disabled)!");
    }

    @Test(description = "FDN_UI002 - [Password] Textbox - Status = enable")
    public void FDN_UI002() {
        logger.info("Thực thi FDN_UI002: Kiểm tra ô Password có được bật (enable) không");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        boolean isPasswordEnabled = loginPage.getTxtPassword().isEnabled();
        Assert.assertTrue(isPasswordEnabled, "Lỗi: Ô nhập Password đang bị tắt (disabled)!");
    }

    @Test(description = "FDN_UI003 - [Quên mật khẩu?] button - Status = enable")
    public void FDN_UI003() {
        logger.info("Thực thi FDN_UI003: Kiểm tra link Quên mật khẩu có được bật (enable) không");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        boolean isForgotPwdEnabled = loginPage.getLnkForgotPassword().isEnabled();
        Assert.assertTrue(isForgotPwdEnabled, "Lỗi: Link Quên mật khẩu đang bị tắt (disabled)!");
    }



    @Test(description = "FDN_UI004 - [Đăng nhập] button - Status = enable")
    public void FDN_UI004() {
        logger.info("Thực thi FDN_UI004: Kiểm tra nút Đăng nhập có được bật (enable) không");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        boolean isLoginBtnEnabled = loginPage.getBtnLogin().isEnabled();
        Assert.assertTrue(isLoginBtnEnabled, "Lỗi: Nút Đăng nhập đang bị tắt (disabled)!");
    }

    @Test(description = "FDN_UI005 - [Tạo một tài khoản mới?] button - Status = enable")
    public void FDN_UI005() {
        logger.info("Thực thi FDN_UI005: Kiểm tra link Tạo tài khoản mới có được bật (enable) không");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        boolean isRegisterLnkEnabled = loginPage.getLnkRegister().isEnabled();
        Assert.assertTrue(isRegisterLnkEnabled, "Lỗi: Link Tạo tài khoản mới đang bị tắt (disabled)!");
    }
}
