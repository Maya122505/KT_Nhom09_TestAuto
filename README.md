# 📝 Dự án Kiểm thử Tự động - Hệ thống Thi trắc nghiệm Online (Q-TEST) - Nhóm 09

Đây là bộ khung kiểm thử tự động (Automation Testing Framework) cho hệ thống **Thi trắc nghiệm Online (Q-TEST)**. Dự án được xây dựng với mục tiêu xác minh tính đúng đắn của các chức năng và giao diện người dùng, đảm bảo chất lượng phần mềm trước khi triển khai.

Dự án áp dụng các công nghệ hiện đại và tuân thủ mô hình **Page Object Model (POM)** để tối ưu hóa việc quản lý mã nguồn, tăng khả năng tái sử dụng và dễ dàng bảo trì.

## 🛠️ Công nghệ và Thư viện sử dụng
*   **Ngôn ngữ:** Java (JDK 25)
*   **Quản lý dự án & thư viện:** Maven
*   **Nền tảng kiểm thử:** TestNG (v7.10.2)
*   **Tự động hóa trình duyệt:** Selenium WebDriver (v4.22.0)
*   **Ghi log chi tiết:** Log4j2 (v2.23.1)
*   **Tạo báo cáo trực quan:** Extent Reports (v5.1.1)
*   **Tiện ích:** Apache Commons IO (hỗ trợ chụp và lưu ảnh màn hình)

## 🚀 Hướng dẫn Cài đặt và Chạy
### 1. Yêu cầu
*   Đảm bảo máy tính đã cài đặt **Java Development Kit (JDK) phiên bản 25**.
*   Đã cài đặt **Apache Maven**.
*   Trình duyệt **Google Chrome** phiên bản mới nhất.

### 2. Cấu hình
*   **Dữ liệu Test:** Có thể cập nhật tài khoản và mật khẩu mặc định trong file:
    `src/main/java/common/Constant.java`
*   **Cấu hình IDE (IntelliJ/Android Studio):** Để tránh lỗi biên dịch với JDK 25, vào `Settings > Build, Execution, Deployment > Build Tools > Maven > Runner` và tick chọn **Delegate IDE build/run actions to Maven**.

### 3. Chạy Test
Mở cửa sổ dòng lệnh (Terminal/CMD) tại thư mục gốc của dự án và chạy lệnh:
```bash
mvn clean test
```
Lệnh này sẽ tự động dọn dẹp, biên dịch, chạy toàn bộ 12 test case và sinh ra báo cáo.

## 📊 Báo cáo và Kết quả
*   **Báo cáo chi tiết (HTML):** Sau khi chạy xong, một file báo cáo trực quan sẽ được tạo tại `target/ExtentReport.html`. File này cung cấp thông tin chi tiết về trạng thái Pass/Fail của từng bước trong mỗi test case.
*   **Ảnh chụp màn hình (Screenshot):** Hệ thống sẽ tự động chụp lại màn hình trình duyệt tại thời điểm test case thất bại (hoặc thành công) và lưu vào thư mục `Evidence/` để hỗ trợ việc phân tích lỗi.

---
## 👥 Thành viên Nhóm 09
1.  Mai Thị Thu Thủy (Leader)
2.  Lê Nguyễn Bảo Trúc
3.  Đinh Thị Thức
4.  Nguyễn Thị Hoàng Luy
5.  Nguyễn Thị Lê Uyên

*Dự án được thực hiện bởi Nhóm 09 - Lớp 49K14.1 - Khoa Thống kê - Tin học - Trường Đại học Kinh tế, ĐH Đà Nẵng.*
