package atda;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartComponent extends BasePage {
    public ShoppingCartComponent(WebDriver driver) {
        super(driver);
    }

    public String getItemsCount() {
        return driver.findElement(By.className("shopping_cart_badge")).getText();
    }
}
