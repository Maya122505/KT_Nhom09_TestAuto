package pageobjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GeneralPage {
    protected WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(15));

    private final By _menuThucHienDeThi = By.xpath("//a[@href='/quiz/thuc-hien-de-thi/']");

    public void vaoMenuThucHienDeThi() {
        wait.until(ExpectedConditions.elementToBeClickable(_menuThucHienDeThi)).click();
    }
}
