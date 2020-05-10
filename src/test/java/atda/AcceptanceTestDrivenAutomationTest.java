package atda;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AcceptanceTestDrivenAutomationTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = getDriver();
        testWatcher.setDriver(driver);
    }

    @Rule
    public SauceTestWatcher testWatcher = new SauceTestWatcher();

    @Rule
    public TestName testName = new TestName() {
        public String getMethodName() {
            return String.format("%s", super.getMethodName());
        }
    };

    @Test
    public void shouldBeAbleToLogin() throws MalformedURLException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue("User logged in and should have seen the products page",
                new ProductsPage(driver).isLoaded());
    }

    @Test
    public void shouldAddOneItemToCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.open();
        productsPage.addItemToCart();
        assertEquals("We added 1 item to the cart and expect it to be there",
                "1", new ShoppingCartComponent(driver).getItemsCount());
    }

    private WebDriver getDriver() throws MalformedURLException {
        String sauceUsername = System.getenv("SAUCE_USERNAME");
        String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");

        MutableCapabilities sauceOpts = new MutableCapabilities();
        sauceOpts.setCapability("username", sauceUsername);
        sauceOpts.setCapability("accessKey", sauceAccessKey);
        sauceOpts.setCapability("name", testName.getMethodName());
        sauceOpts.setCapability("build", "ATDA");
        sauceOpts.setCapability("commandTimeout", "30");


        MutableCapabilities browserOptions = new MutableCapabilities();
        browserOptions.setCapability(CapabilityType.PLATFORM_NAME, "Windows 10");
        browserOptions.setCapability(CapabilityType.BROWSER_VERSION, "latest");
        browserOptions.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        browserOptions.setCapability("sauce:options", sauceOpts);

        String sauceUrl = "https://ondemand.saucelabs.com/wd/hub";
        URL url = new URL(sauceUrl);
        return new RemoteWebDriver(url, browserOptions);
    }
}
