package atda;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        WebElement inventoryContainer = waitUntilVisible(By.id("inventory_container"));
        return inventoryContainer.isDisplayed();
    }

    public void open() {
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
    }

    public void addItemToCart() {
        waitUntilVisible(By.className("btn_primary")).click();
    }
}
