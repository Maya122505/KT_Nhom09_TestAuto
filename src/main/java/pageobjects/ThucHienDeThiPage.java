package pageobjects;

import common.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// Kế thừa từ GeneralPage[cite: 1]
public class ThucHienDeThiPage extends GeneralPage {

    // --- Locators riêng của trang này ---[cite: 1]
    private final By _btnXacNhanVaoThi = By.xpath("//button[contains(text(), 'BẮT ĐẦU LÀM BÀI')]");
    private final By _txtMatKhauPopUp = By.xpath("//input[@placeholder='Mật khẩu đề thi']");
    private final By _btnXacNhanPopUp = By.xpath("//button[contains(text(), 'XÁC NHẬN')]");
    private final By _listDapAnRadio = By.xpath("//input[@type='radio']");
    private final By _btnNopBai = By.xpath("//button[contains(text(), 'Nộp bài')]");
    private final By _btnHoanThanhCuoiCung = By.xpath("//button[text()='HOÀN THÀNH']");
    private final By _btnHuyNopBai = By.xpath("//button[contains(text(), 'Hủy') or contains(text(), 'HUỶ')]");
    private final By _lblThongBaoNopTuDong = By.xpath("//div[contains(text(), 'Bài thi của bạn đã được nộp tự động do hết giờ')]");
    private final By _lblTimer = By.id("timer");
    private final By _lblLoiTrongMatKhau = By.xpath("//div[contains(text(), 'Vui lòng nhập mật khẩu')]");
    private final By _lblThongBaoKhongKhaDung = By.xpath("//*[contains(text(), 'Đề thi hiện không khả dụng')]");

    // --- Actions riêng ---[cite: 1]
    public void chonBaiThiVaBatDau(String tenBaiThi, String matKhau) {
        By dynamicBtn = By.xpath("//div[@class='exam-card'][.//h2[contains(text(), '" + tenBaiThi + "')]]//a[contains(@class, 'btn-action')]");
        wait.until(ExpectedConditions.elementToBeClickable(dynamicBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(_btnXacNhanVaoThi)).click();
        if (matKhau != null && !matKhau.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(_txtMatKhauPopUp)).sendKeys(matKhau);
            wait.until(ExpectedConditions.elementToBeClickable(_btnXacNhanPopUp)).click();
        }
    }

    public void lamBaiNgauNhien() {
        List<WebElement> ds = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(_listDapAnRadio));
        for (int i = 0; i < ds.size(); i += 4) {
            try {
                WebElement opt = ds.get(i);
                ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", opt);
                opt.click();
            } catch (Exception e) {}
        }
    }

    public void nopBai() {
        wait.until(ExpectedConditions.elementToBeClickable(_btnNopBai)).click();
        wait.until(ExpectedConditions.elementToBeClickable(_btnHoanThanhCuoiCung)).click();
    }

    public void thucHienHuyNop() {
        // Bấm nút Nộp bài để hiện Pop-up
        wait.until(ExpectedConditions.elementToBeClickable(_btnNopBai)).click();

        // Bấm nút Hủy trên Pop-up vừa hiện ra
        wait.until(ExpectedConditions.elementToBeClickable(_btnHuyNopBai)).click();
    }

    public void choHetThoiGianVaKiemTra() {
        // Thức cần khởi tạo một WebDriverWait mới với thời gian chờ lâu hơn (ví dụ 5 phút)
        // để tránh bị lỗi Timeout khi đồng hồ bài thi chưa chạy về 0
        WebDriverWait longWait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofMinutes(5));

        // Đợi cho đến khi thông báo xuất hiện trên màn hình
        longWait.until(ExpectedConditions.visibilityOfElementLocated(_lblThongBaoNopTuDong));
    }

    // 3. Lấy nội dung thông báo để Assert
    public String layNoiDungThongBaoTuDong() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(_lblThongBaoNopTuDong)).getText();
    }

    public boolean isGiaoDienLamBaiHienThi() {
        try {
            // Kiểm tra xem đồng hồ đếm ngược có hiển thị không
            return wait.until(ExpectedConditions.visibilityOfElementLocated(_lblTimer)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void vaoDeThiDeTrongMatKhau(String tenBaiThi) {
        By dynamicBtn = By.xpath("//div[@class='exam-card'][.//h2[contains(text(), '" + tenBaiThi + "')]]//a[contains(@class, 'btn-action')]");
        wait.until(ExpectedConditions.elementToBeClickable(dynamicBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(_btnXacNhanVaoThi)).click();

        // Bước quan trọng: Để trống trường mật khẩu và bấm xác nhận ngay
        wait.until(ExpectedConditions.elementToBeClickable(_btnXacNhanPopUp)).click();
    }

    public String layThongBaoLoiMatKhau() {
        try {
            // 1. Ưu tiên chờ và "chộp" lấy cái Alert của trình duyệt trước
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String text = alert.getText(); // Lấy chữ "Mật khẩu không chính xác!..."
            alert.accept(); // Bấm OK để đóng Alert, giải phóng trình duyệt
            return text;
        } catch (TimeoutException e) {
            // 2. Nếu đợi mà không thấy Alert nào bật lên, thì mới đi tìm text trên giao diện HTML
            return wait.until(ExpectedConditions.visibilityOfElementLocated(_lblLoiTrongMatKhau)).getText();
        }
    }

    public boolean isPopUpMatKhauHienThi() {
        try {
            // Kiểm tra xem nút "XÁC NHẬN" trên Pop-up có còn hiển thị không
            return wait.until(ExpectedConditions.visibilityOfElementLocated(_btnXacNhanPopUp)).isDisplayed();
        } catch (Exception e) {
            // Nếu không tìm thấy (tức là Pop-up đã biến mất/bị đóng), trả về false
            return false;
        }
    }

    public boolean isDeThiBiKhoa(String tenBaiThi, String expectedText) {
        try {
            By dynamicBtn = By.xpath("//div[@class='exam-card'][.//h2[contains(text(), '" + tenBaiThi + "')]]//div[contains(@class, 'btn-action')]");
            org.openqa.selenium.WebElement btnElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicBtn));

            String classAttribute = btnElement.getAttribute("class");
            String text = btnElement.getText();

            // Kiểm tra xem nút có class disabled VÀ chứa đúng chữ mình cần không
            return classAttribute.contains("btn-disabled") && text.trim().equals(expectedText);
        } catch (Exception e) {
            return false;
        }
    }
}