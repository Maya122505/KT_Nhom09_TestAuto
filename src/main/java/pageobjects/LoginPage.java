package pageobjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

    // Locators đã được cập nhật chuẩn theo HTML của trang Q-TEST
    private final By _txtEmail = By.xpath("//input[@name='email']");
    private final By _txtPassword = By.xpath("//input[@name='password' or @id='pwd']");
    private final By _btnLogin = By.xpath("//button[@type='submit']");
    private final By _lblLoginErrorMsg = By.xpath("//*[contains(@class, 'error')]");
    
    // Locators bổ sung cho UI Test
    private final By _lnkForgotPassword = By.xpath("//a[@href='/quiz/khoi-phuc-mat-khau/']");
    private final By _lnkRegister = By.xpath("//a[@href='/quiz/dang-ky/']");

    public void open() {
        Constant.WEBDRIVER.get(Constant.QUIZ_URL);
    }

    public WebElement getTxtEmail() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(_txtEmail));
    }

    public WebElement getTxtPassword() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(_txtPassword));
    }

    public WebElement getBtnLogin() {
        return wait.until(ExpectedConditions.elementToBeClickable(_btnLogin));
    }

    public WebElement getLblLoginErrorMsg() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(_lblLoginErrorMsg));
    }

    public WebElement getLnkForgotPassword() {
        return wait.until(ExpectedConditions.elementToBeClickable(_lnkForgotPassword));
    }

    public WebElement getLnkRegister() {
        return wait.until(ExpectedConditions.elementToBeClickable(_lnkRegister));
    }

    public void login(String email, String password) {
        getTxtEmail().clear();
        getTxtEmail().sendKeys(email);
        getTxtPassword().clear();
        getTxtPassword().sendKeys(password);
        getBtnLogin().click();
    }
}
