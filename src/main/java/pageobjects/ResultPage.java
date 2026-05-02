package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResultPage {
    WebDriver driver;
    WebDriverWait wait;

    private By txtEmail = By.name("email");
    private By txtPassword = By.name("password");
    private By btnLogin = By.xpath("//button[@type='submit']");

    private By menuTraCuuKetQua = By.xpath("//a[contains(.,'Tra cứu kết quả')]");

    private By resultRows = By.xpath("//table//tbody/tr");

    private By bodyPage = By.tagName("body");

    private By btnXemChiTiet = By.xpath("(//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'xem chi tiết')])[1]");

    private By lblChiTiet = By.xpath("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'chi tiết') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'điểm') or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'đáp án')]");

    public ResultPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void login(String email, String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtEmail)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtPassword)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();

        try{
            Thread.sleep(1500);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void clickMenuTraCuuKetQua(){
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(menuTraCuuKetQua));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", menu);

        try{
            Thread.sleep(800);
        }catch(Exception e){
            e.printStackTrace();
        }

        menu.click();

        try{
            Thread.sleep(1500);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean isDanhSachKetQuaDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(bodyPage));

            String text = driver.findElement(bodyPage).getText();
            int soDong = driver.findElements(resultRows).size();

            System.out.println("FUNC01 TEXT = " + text);
            System.out.println("FUNC01 ROWS = " + soDong);

            return soDong > 0
                    || text.contains("Tên đề thi")
                    || text.contains("Thời gian nộp")
                    || text.contains("Kết quả")
                    || text.contains("Hành động");

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean isThongBaoKhongCoKetQua(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(bodyPage));
            String text = driver.findElement(bodyPage).getText();

            System.out.println("FUNC02 TEXT = " + text);

            return text.contains("Bạn chưa hoàn thành bài thi nào.")
                    || text.contains("chưa hoàn thành bài thi nào");

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void clickXemChiTiet(){
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(btnXemChiTiet));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            btn.click();
        }catch(Exception e){
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        try{
            Thread.sleep(2000);
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("===== SAU KHI CLICK XEM CHI TIET =====");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.findElement(bodyPage).getText());
    }

    public boolean isChiTietKetQuaDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(bodyPage));

            String text = driver.findElement(bodyPage).getText().toLowerCase();
            String url = driver.getCurrentUrl().toLowerCase();

            System.out.println("FUNC03 URL = " + url);
            System.out.println("FUNC03 TEXT = " + text);

            return text.contains("chi tiết")
                    || text.contains("điểm")
                    || text.contains("đáp án")
                    || url.contains("detail")
                    || url.contains("chi-tiet");

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}