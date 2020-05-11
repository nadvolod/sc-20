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
}
