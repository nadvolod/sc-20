package atda;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage{

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement usernameField =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

        usernameField.sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.className("btn_action")).click();
    }


}
