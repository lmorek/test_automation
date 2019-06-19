package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

//TODO add capabilities and support for all browsers
public class DriverSetup {
    private DesiredCapabilities capabilities;
    private LoadCapabilities loadCapabilities;
    private String environment;
    private String browser;

    public DriverSetup(String environment, String browser) {
        this.environment = environment;
        this.browser = browser;
        loadCapabilities = new LoadCapabilities(environment,browser);
        capabilities = loadCapabilities.prepareCapabilities();
    }

    public WebDriver setWebDriver() {

        WebDriver driver;

        if (environment.contains("local")) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeDriverService service = new ChromeDriverService.Builder()
                            .usingAnyFreePort()
                            .build();
                    ChromeOptions options = new ChromeOptions();
                    options.merge(capabilities);
                    driver = new ChromeDriver(service,options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(new FirefoxOptions().setProfile(new FirefoxProfile()));
                    break;
                default:
                    throw new RuntimeException("Unrecognised browser");
            }
        } else {
            throw new RuntimeException("Unrecognised environment");
        }
        return driver;
    }
}
