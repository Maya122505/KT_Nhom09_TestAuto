# 🚂 Railway Automation Testing Project - Nhóm 09 

Dự án này là bộ công cụ kiểm thử tự động (Automation Testing) cho hệ thống **QA Railway**, được xây dựng dựa trên ngôn ngữ **Java** và thư viện **Selenium WebDriver**. Dự án áp dụng mô hình **Page Object Model (POM)** nhằm tối ưu hóa việc quản lý mã nguồn và tái sử dụng các thành phần giao diện.

## 🛠 Công nghệ và Thư viện sử dụng
*   **Ngôn ngữ:** Java (JDK 11)
*   **Quản lý dự án:** Maven 3.x
*   **Testing Framework:** TestNG (v7.10.2)
*   **Trình duyệt tự động:** Selenium WebDriver (v4.22.0)
*   **Ghi log:** Log4j2 (v2.23.1)
*   **Báo cáo:** Extent Reports (v5.1.1) & TestNG Reports
*   **Tiện ích:** Apache Commons IO (hỗ trợ lưu ảnh chụp màn hình)
## 📋 Phạm vi kiểm thử (Test Scenarios)
Dự án bao phủ 16 Test Cases (TC01 - TC16) với các chức năng trọng tâm:
1.  LoginTests (TC01-TC05)
2.  AccountTests (TC06-TC9)
3.  ScheduleTests (TC10, TC11)
4.  ManageTests (TC12, TC13, TC16)
5.  TicketTests (TC14-TC15)

## 🚀 Hướng dẫn
### 1. Chuẩn bị
*   Đảm bảo máy đã cài đặt **Java 11** và **Maven**.
*   Trình duyệt **Google Chrome** cần được cài đặt (ChromeDriver sẽ được Selenium tự động quản lý).

### 2. Cấu hình Dữ liệu
Cập nhật các thông tin tài khoản (Email, Mật khẩu) trong file:
`src/main/java/common/Constant.java`

### 3. Chạy Test bằng Maven
Mở cửa sổ dòng lệnh (Terminal/CMD) tại thư mục gốc của dự án và chạy:
```bash
mvn clean test
```

## 📊 Kết quả kiểm thử
*   **Báo cáo Extent Reports:** Được sinh ra tự động để theo dõi trạng thái Pass/Fail của từng step.
*   **Screenshot:** Hệ thống sẽ tự động chụp ảnh màn hình trình duyệt và lưu vào thư mục `Evidence/` để hỗ trợ việc phân tích lỗi.


# Thành viên nhóm: 
1. Mai Thị Thu Thủy (Leader)
2. Lê Nguyễn Bảo Trúc
3. Đinh Thị Thức
4. Nguyễn Thị Hoàng Luy
5. Nguyễn Thị Lê Uyên

# Link github: https://github.com/Maya122505/KT_2026_Apr_Nhom09

*Dự án được thực hiện bởi Nhóm 09 - 49K14.1 - DUE*
