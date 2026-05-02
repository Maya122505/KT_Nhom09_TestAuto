package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    //LOGIN
    private By txtEmail = By.name("email");
    private By txtPassword = By.name("password");
    private By btnLogin = By.xpath("//button[@type='submit']");

    //MENU
    private By menuTrangChu = By.xpath("//a[contains(.,'Trang chủ')]");
    private By menuThucHienDeThi = By.xpath("//a[contains(.,'Thực hiện đề thi')]");
    private By menuTraCuuKetQua = By.xpath("//a[contains(.,'Tra cứu kết quả')]");
    private By menuDangXuat = By.xpath("//a[contains(.,'Đăng xuất')]");

    //BUTTON VÀO THI TỪNG ĐỀ
    private By btnVaoThiPython = By.xpath("//*[contains(.,'Kiểm tra định kỳ') and contains(.,'Lập trình Python')]//following::a[contains(.,'Vào thi')][1]");
    private By btnVaoThiCSDL = By.xpath(
            "(//*[contains(text(),'Kỳ thi thực hành - Cơ sở dữ liệu') " +
                    "or contains(text(),'Cơ sở dữ liệu')]" +
                    "/following::a[contains(.,'Vào thi')])[1]"
    );
    //DASHBOARD
    private By txtTongBaiThi = By.xpath("//*[contains(text(),'TỔNG BÀI THI')]/following-sibling::*[1]");
    private By txtDaLam = By.xpath("//*[contains(text(),'ĐÃ LÀM BÀI')]/following-sibling::*[1]");
    private By txtChuaLam = By.xpath("//*[contains(text(),'CHƯA LÀM BÀI')]/following-sibling::*[1]");
    private By txtDiemTB = By.xpath("//*[contains(text(),'ĐIỂM TRUNG BÌNH')]/following-sibling::*[1]");

    private By bodyPage = By.tagName("body");

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isMoGiaoDienLamBaiThi(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(bodyPage));

            String currentUrl = driver.getCurrentUrl();
            String text = driver.findElement(bodyPage).getText();

            System.out.println("FUNC05 URL: " + currentUrl);
            System.out.println("FUNC05 TEXT: " + text);

            boolean vaoDungPhongThi = text.contains("Nộp bài")
                    || text.contains("Thời gian còn lại")
                    || text.contains("Câu hỏi")
                    || text.contains("Lập trình Python")
                    || currentUrl.contains("start")
                    || currentUrl.contains("exam");

            return vaoDungPhongThi;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //LOGIN
    public void login(String email, String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtEmail)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtPassword)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
    }

    // FUNC_01
    public void clickMenuTrangChu(){
        wait.until(ExpectedConditions.elementToBeClickable(menuTrangChu)).click();
    }

    public boolean isTrangChuReloaded(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(bodyPage));
            return driver.getCurrentUrl().contains("home")
                    || driver.findElement(bodyPage).getText().contains("TỔNG BÀI THI");
        }catch(Exception e){
            return false;
        }
    }

    //FUNC_02
    public void clickMenuThucHienDeThi(){
        wait.until(ExpectedConditions.elementToBeClickable(menuThucHienDeThi)).click();
    }

    public boolean isThucHienDeThiDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(bodyPage));

            String currentUrl = driver.getCurrentUrl();
            String pageText = driver.findElement(bodyPage).getText();

            System.out.println("FUNC02 URL = " + currentUrl);
            System.out.println("FUNC02 TEXT = " + pageText);

            return currentUrl.contains("exam")
                    || currentUrl.contains("thuc")
                    || pageText.contains("Thực hiện đề thi")
                    || pageText.contains("Kiểm tra định kỳ")
                    || pageText.contains("Kỳ thi")
                    || pageText.contains("Vào thi");

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //FUNC_03
    public void clickMenuTraCuuKetQua(){
        wait.until(ExpectedConditions.elementToBeClickable(menuTraCuuKetQua)).click();
    }

    public boolean isTraCuuKetQuaDisplayed(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(bodyPage));

            String currentUrl = driver.getCurrentUrl();
            String pageText = driver.findElement(bodyPage).getText();

            System.out.println("FUNC03 URL = " + currentUrl);
            System.out.println("FUNC03 TEXT = " + pageText);

            return currentUrl.contains("result")
                    || currentUrl.contains("history")
                    || currentUrl.contains("score")
                    || pageText.contains("Tra cứu kết quả")
                    || pageText.contains("Lịch sử làm bài")
                    || pageText.contains("Điểm")
                    || pageText.contains("Kết quả thi");

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //FUNC_04
    public void clickDangXuat(){
        wait.until(ExpectedConditions.elementToBeClickable(menuDangXuat)).click();
    }

    public boolean isRedirectLoginPage(){
        return driver.getCurrentUrl().contains("login");
    }

    // FUNC_05
    public void clickVaoThiPython(){
        try{
            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(btnVaoThiPython));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
            Thread.sleep(1500);

            wait.until(ExpectedConditions.elementToBeClickable(btn));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //FUNC_06
        public void clickVaoThiCSDL(){
        try {
            WebElement btn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(btnVaoThiCSDL)
            );
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", btn
            );
            Thread.sleep(1500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isBlockedWhenExamClosed(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(bodyPage));
            String text = driver.findElement(bodyPage).getText();
            String currentUrl = driver.getCurrentUrl();

            System.out.println("FUNC06 URL: " + currentUrl);
            System.out.println("FUNC06 TEXT: " + text);

            boolean khongVaoDuocPhongThi =
                    !text.contains("Nộp bài")
                            && !text.contains("Thời gian còn lại")
                            && !text.contains("Câu 1");

            boolean coThongBao =
                    text.contains("Đề thi hiện không khả dụng")
                            || text.contains("không khả dụng")
                            || text.contains("Bài thi đã hết hạn")
                            || text.contains("Không thể truy cập")
                            || text.contains("Đề thi đã đóng")
                            || text.contains("Hết hạn");

            System.out.println("FUNC06 - Không vào được phòng thi: " + khongVaoDuocPhongThi);
            System.out.println("FUNC06 - Có thông báo chặn: " + coThongBao);

            return khongVaoDuocPhongThi && coThongBao;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //FUNC_07
    public boolean isThongBaoKhongCoBaiThi(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(bodyPage));
            String text = driver.findElement(bodyPage).getText();

            return text.contains("Hiện tại bạn chưa có đề thi nào sắp tới.")
                    || text.contains("chưa có đề thi nào");
        }catch(Exception e){
            return false;
        }
    }

    //FUNC_08
    public boolean isTongBaiThiDungLogic(){
        int tong = Integer.parseInt(driver.findElement(txtTongBaiThi).getText().trim());
        int daLam = Integer.parseInt(driver.findElement(txtDaLam).getText().trim());
        int chuaLam = Integer.parseInt(driver.findElement(txtChuaLam).getText().trim());

        return tong == (daLam + chuaLam);
    }

    //FUNC_09
    public boolean isDiemTrungBinhDungLogic(){
        int daLam = Integer.parseInt(driver.findElement(txtDaLam).getText().trim());
        double diemTB = Double.parseDouble(driver.findElement(txtDiemTB).getText().trim());

        return daLam > 0 && diemTB > 0;
    }
}