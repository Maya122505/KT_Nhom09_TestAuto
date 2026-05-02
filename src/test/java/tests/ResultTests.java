package tests;

import common.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.ResultPage;

public class ResultTests {

    ResultPage resultPage;

    @BeforeMethod
    public void setUp(){
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
        Constant.WEBDRIVER.get("http://localhost:8000/quiz/login/");

        resultPage = new ResultPage(Constant.WEBDRIVER);
    }

    @Test
    public void testFUNC_01_HienThiDanhSachBaiDaLam(){
        resultPage.login("luynh@gmail.com","123");
        resultPage.clickMenuTraCuuKetQua();

        Assert.assertTrue(resultPage.isDanhSachKetQuaDisplayed());
    }

    @Test
    public void testFUNC_02_KhongCoDuLieuBaiDaLam(){
        resultPage.login("alo@gmail.com","123");
        resultPage.clickMenuTraCuuKetQua();

        Assert.assertTrue(resultPage.isThongBaoKhongCoKetQua());
    }

    @Test
    public void testFUNC_03_XemChiTietBaiDaLam(){
        resultPage.login("luynh@gmail.com","123");
        resultPage.clickMenuTraCuuKetQua();
        resultPage.clickXemChiTiet();

        Assert.assertTrue(resultPage.isChiTietKetQuaDisplayed());
    }

    @AfterMethod
    public void tearDown(){
        if(Constant.WEBDRIVER != null){
            Constant.WEBDRIVER.quit();
        }
    }
}