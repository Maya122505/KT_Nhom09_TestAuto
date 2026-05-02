package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.HomePage;

public class HomeTests {

    WebDriver driver;
    HomePage homePage;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8000/quiz/login/");

        homePage = new HomePage(driver);
        homePage.login("luynh@gmail.com", "123");
    }

    @Test
    public void testFUNC_01_HienThiTrangChu(){
        homePage.clickMenuTrangChu();
        Assert.assertTrue(homePage.isTrangChuReloaded());
    }

    @Test
    public void testFUNC_02_HienThiThucHienDeThi(){
        homePage.clickMenuThucHienDeThi();
        Assert.assertTrue(homePage.isThucHienDeThiDisplayed(),
                "FAIL: Không hiển thị giao diện Thực hiện đề thi.");
    }

    @Test
    public void testFUNC_03_HienThiTraCuuKetQua(){
        homePage.clickMenuTraCuuKetQua();
        Assert.assertTrue(homePage.isTraCuuKetQuaDisplayed());
    }

    @Test
    public void testFUNC_04_DangXuatTaiKhoan(){
        homePage.clickDangXuat();
        Assert.assertTrue(homePage.isRedirectLoginPage());
    }

    @Test
    public void testFUNC_05_VaoThiDeDangMo(){
        homePage.clickMenuThucHienDeThi();
        homePage.clickVaoThiPython();

        Assert.assertTrue(homePage.isMoGiaoDienLamBaiThi(),
                "FAIL: Không chuyển sang giao diện làm bài thi Python.");
    }
    @Test
    public void testFUNC_06_VaoThiDeDong(){
        homePage.clickMenuThucHienDeThi();

        boolean isBlocked = homePage.isBlockedWhenExamClosed();

        // Log rõ kết quả để báo cáo bug
        if (!isBlocked) {
            System.out.println("[BUG - FUNC06] Hệ thống KHÔNG chặn truy cập " +
                    "khi đề thi đã đóng/hết hạn. " +
                    "Expected: Hiển thị thông báo 'Đề thi hiện không khả dụng'. " +
                    "Actual: Cho phép vào phòng thi hoặc không hiển thị thông báo.");
        }

        Assert.assertTrue(isBlocked,
                "FUNC06 FAIL: Hệ thống không chặn khi đề đóng - Bug cần fix phía backend/frontend"
        );
    }

    @Test
    public void testFUNC_07_KhongCoDeThi(){
        driver.get("http://localhost:8000/quiz/login/");
        homePage = new HomePage(driver);
        homePage.login("alo@gmail.com", "123");

        Assert.assertTrue(homePage.isThongBaoKhongCoBaiThi());
    }

    @Test
    public void testFUNC_08_LogicTongBaiThi(){
        Assert.assertTrue(homePage.isTongBaiThiDungLogic());
    }

    @Test
    public void testFUNC_09_LogicDiemTrungBinh(){
        Assert.assertTrue(homePage.isDiemTrungBinhDungLogic());
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}