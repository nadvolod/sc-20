package atda;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        WebElement usernameField = waitUntilVisible(By.id("user-name"));

        usernameField.sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.className("btn_action")).click();
    }


}
