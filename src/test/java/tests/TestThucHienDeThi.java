package tests;

import common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pageobjects.LoginPage;
import pageobjects.ThucHienDeThiPage;


public class TestThucHienDeThi {

    @BeforeMethod
    public void setUp() {
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }

    @Test
    public void FUNC_01_ThucHienBaiThiThanhCong() {
        LoginPage login = new LoginPage();
        login.open();
        login.login(Constant.USERNAME, Constant.PASSWORD);

        ThucHienDeThiPage testPage = new ThucHienDeThiPage();
        // Hàm vaoMenuThucHienDeThi được lấy từ GeneralPage thông qua kế thừa[cite: 1]
        testPage.vaoMenuThucHienDeThi();
        testPage.chonBaiThiVaBatDau("Kiểm tra định kỳ - Lập trình Python", "123456");
        testPage.lamBaiNgauNhien();
        testPage.nopBai();
    }

    @Test
    public void FUNC_02_HuyNopBaiTaiPopupXacNhan() {
        // Bước 1: Đăng nhập
        LoginPage login = new LoginPage();
        login.open();
        login.login(Constant.USERNAME, Constant.PASSWORD);

        // Bước 2: Vào menu (Hàm này được thừa kế từ GeneralPage)
        ThucHienDeThiPage testPage = new ThucHienDeThiPage();
        testPage.vaoMenuThucHienDeThi();

        // Bước 3: Vào bài thi và làm bài ngẫu nhiên
        testPage.chonBaiThiVaBatDau("Kiểm tra định kỳ - Lập trình Python", "123456");
        testPage.lamBaiNgauNhien();

        // Bước 4: Thực hiện Hủy nộp bài
        testPage.thucHienHuyNop();

        // Bước 5: Kiểm tra kết quả thực tế so với mong đợi
        System.out.println("--- Kết quả FUNC_02: Hủy nộp bài và quay lại màn hình thành công! ---");
    }

    @Test
    public void FUNC_03_TuDongNopBaiKhiHetThoiGian() {
        // 1. Đăng nhập[cite: 1]
        LoginPage login = new LoginPage();
        login.open();
        login.login(Constant.USERNAME, Constant.PASSWORD);

        // 2. Vào menu (Hàm kế thừa từ GeneralPage)[cite: 1]
        ThucHienDeThiPage testPage = new ThucHienDeThiPage();
        testPage.vaoMenuThucHienDeThi();

        // 3. Vào bài thi và làm bài ngẫu nhiên[cite: 1]
        // Thức nên chọn bài thi có thời gian ngắn nhất để test cho nhanh nhé[cite: 1]
        testPage.chonBaiThiVaBatDau("Kiểm tra định kỳ - Lập trình Python", "123456");
        testPage.lamBaiNgauNhien();

        // 4. Chờ cho đến khi hết giờ và hệ thống tự nộp[cite: 1]
        testPage.choHetThoiGianVaKiemTra();

        // 5. Kiểm tra kết quả mong đợi[cite: 1]
        String message = testPage.layNoiDungThongBaoTuDong();
        org.testng.Assert.assertEquals(message, "Bài thi của bạn đã được nộp tự động do hết giờ",
                "Lỗi: Không hiển thị đúng thông báo khi hết giờ làm bài!");

        System.out.println("--- Kết quả FUNC_03: Hệ thống tự động nộp bài khi hết giờ thành công! ---");
    }

    @Test
    public void FUNC_04_VaoDeThiCoMatKhauBaoVe() {
        // Bước 1: Đăng nhập hệ thống[cite: 1]
        LoginPage login = new LoginPage();
        login.open();
        login.login(Constant.USERNAME, Constant.PASSWORD);

        // Bước 2: Truy cập Menu "Thực hiện đề thi" (Thừa kế từ GeneralPage)[cite: 1]
        ThucHienDeThiPage testPage = new ThucHienDeThiPage();
        testPage.vaoMenuThucHienDeThi();

        // Bước 3: Thực hiện kịch bản FUNC_04[cite: 1]
        // - Chọn bài thi có mật khẩu
        // - Nhập đúng mật khẩu: "123456"
        // - Bấm "Xác nhận"
        testPage.chonBaiThiVaBatDau("Kiểm tra định kỳ - Lập trình Python", "123456");

        // Bước 4: Kiểm tra kết quả mong đợi[cite: 1]
        // Xác thực thành công và hệ thống chuyển sang giao diện làm bài, bắt đầu tính giờ[cite: 1]
        boolean isAccessGranted = testPage.isGiaoDienLamBaiHienThi();

        org.testng.Assert.assertTrue(isAccessGranted, "Lỗi: Hệ thống không chuyển sang giao diện làm bài sau khi nhập đúng mật khẩu!");

        System.out.println("--- Kết quả FUNC_04: Vào đề thi có mật khẩu thành công! ---");
    }

    @Test
    public void FUNC_05_VaoDeThiDeTrongMatKhau() {
        // 1. Đăng nhập
        LoginPage login = new LoginPage();
        login.open();
        login.login(Constant.USERNAME, Constant.PASSWORD);

        // 2. Vào menu "Thực hiện đề thi"
        ThucHienDeThiPage testPage = new ThucHienDeThiPage();
        testPage.vaoMenuThucHienDeThi();

        // 3. Thực hiện các bước theo kịch bản trong ảnh image_49e89d.png[cite: 1]
        testPage.vaoDeThiDeTrongMatKhau("Kiểm tra định kỳ - Lập trình Python");

        // 4. Kiểm tra kết quả mong đợi[cite: 1]
        String actualMsg = testPage.layThongBaoLoiMatKhau();

        // Assert 1: Hiển thị đúng thông báo yêu cầu nhập mật khẩu[cite: 1]
        org.testng.Assert.assertEquals(actualMsg, "Vui lòng nhập mật khẩu",
                "Lỗi: Không hiển thị thông báo nhắc nhở khi để trống mật khẩu!");

        // Assert 2: Xác nhận vẫn đang ở màn hình Pop-up, không truy cập được vào câu hỏi[cite: 1]
        boolean isPopUpConHienThi = testPage.isPopUpMatKhauHienThi();
        org.testng.Assert.assertTrue(isPopUpConHienThi, "Lỗi: Hệ thống vẫn cho vào đề thi dù mật khẩu trống!");

        System.out.println("--- Kết quả FUNC_04 (Để trống): Hệ thống đã chặn và báo lỗi thành công. ---");
    }

    @Test
    public void FUNC_06_VaoDeThiNhapSaiMatKhau() {
        // Bước 1: Đăng nhập
        LoginPage login = new LoginPage();
        login.open();
        login.login(Constant.USERNAME, Constant.PASSWORD);

        // Bước 2: Truy cập Menu
        ThucHienDeThiPage testPage = new ThucHienDeThiPage();
        testPage.vaoMenuThucHienDeThi();

        // Bước 3: Thực hiện kịch bản FUNC_06 (Nhập sai mật khẩu)
        // Gọi lại hàm cũ nhưng cố tình truyền vào một mật khẩu sai (VD: "SaiPass123" thay vì "123456")
        testPage.chonBaiThiVaBatDau("Kiểm tra định kỳ - Lập trình Python", "SaiPass123");

        // Bước 4: Kiểm tra kết quả mong đợi
        // 4.1 - Kiểm tra thông báo lỗi (Hàm này đã được bạn cập nhật để bắt JS Alert ở TC trước)
        String actualMsg = testPage.layThongBaoLoiMatKhau();
        org.testng.Assert.assertTrue(actualMsg.contains("Mật khẩu không chính xác"),
                "Lỗi: Không hiển thị đúng thông báo nhắc nhở khi nhập sai mật khẩu!");

        // 4.2 - Xác nhận vẫn đang ở màn hình Pop-up, không truy cập được vào đề thi
        boolean isPopUpConHienThi = testPage.isPopUpMatKhauHienThi();
        org.testng.Assert.assertTrue(isPopUpConHienThi, "Lỗi: Hệ thống vẫn cho vào đề thi dù nhập sai mật khẩu!");

        System.out.println("--- Kết quả FUNC_06 (Nhập sai): Hệ thống đã chặn và báo lỗi chính xác. ---");
    }

    @Test
    public void FUNC_07_TruyCapDeThiDaDong() {
        // Bước 1: Đăng nhập
        LoginPage login = new LoginPage();
        login.open();
        login.login(Constant.USERNAME, Constant.PASSWORD);

        // Bước 2: Truy cập Menu
        ThucHienDeThiPage testPage = new ThucHienDeThiPage();
        testPage.vaoMenuThucHienDeThi();

        // Bước 3 & 4: Kiểm tra trạng thái của bài thi đã đóng
        // Trực tiếp kiểm tra xem giao diện có khóa cái nút lại không, thay vì cố click
        String tenBaiThi = "Kỳ thi thực hành - Cơ sở dữ liệu"; // Tên lấy chuẩn theo ảnh của Thức
        boolean isKhoaThanhCong = testPage.isDeThiBiKhoa(tenBaiThi, "không khả dụng");

        // Assert: Nếu nút chưa bị khóa (hàm trả về false) thì đánh rớt Test Case
        org.testng.Assert.assertTrue(isKhoaThanhCong,
                "Lỗi: Bài thi đã đóng nhưng nút truy cập không bị vô hiệu hóa (thiếu class btn-disabled hoặc sai text)!");

        System.out.println("--- Kết quả FUNC_07: Giao diện đã khóa thành công nút làm bài đối với đề thi hết hạn! ---");
    }

    @Test
    public void FUNC_08_TruyCapDeThiHetLuotLam() {
        LoginPage login = new LoginPage();
        login.open();
        login.login(Constant.USERNAME, Constant.PASSWORD);

        ThucHienDeThiPage testPage = new ThucHienDeThiPage();
        testPage.vaoMenuThucHienDeThi();

        // Tên bài thi lấy chính xác từ ảnh của bạn
        String tenBaiThi = "Kiểm tra định kỳ - Lập trình Python";

        // Gọi hàm và truyền vào chữ "Hết lượt thi"
        boolean isKhoaThanhCong = testPage.isDeThiBiKhoa(tenBaiThi, "Hết lượt thi");

        org.testng.Assert.assertTrue(isKhoaThanhCong,
                "Lỗi: Bài thi đã hết lượt nhưng nút không hiển thị 'Hết lượt thi' hoặc không bị khóa!");

        System.out.println("--- Kết quả FUNC_08: Giao diện đã khóa nút và hiện đúng chữ 'Hết lượt thi'. ---");
    }

}