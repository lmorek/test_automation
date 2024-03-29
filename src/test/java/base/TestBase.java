package base;

import core.DriverSetup;
import core.properties.TestProperties;
import org.openqa.selenium.WebDriver;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;

import static core.properties.TestProperties.readPropertiesFromFile;

public class TestBase extends TestListenerAdapter {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ThreadLocal<String> environment = new ThreadLocal<>();
    protected static ThreadLocal<String> browser = new ThreadLocal<>();


    public static TestProperties properties;


    public WebDriver getDriver() {
        return driver.get();
    }

    // String e stands for environment, String b stands for browser
    protected void setVariables(String e, String b) {
        if (e == null) {
            if (System.getenv("ENV") == null) {
                environment.set(properties.getEnvironment());
            } else {
                environment.set(System.getenv("ENV"));
            }
        } else {
            environment.set(e);
        }

        if (b == null) {
            if (System.getenv("BROWSER") == null) {
                browser.set(properties.getBrowser());
            } else {
                browser.set(System.getenv("BROWSER"));
            }
        } else {
            browser.set(b);
        }

    }

    @BeforeMethod(alwaysRun = true)
    @Parameters(value = {"browser", "environment"})
    public void beforeTest(@Optional String browser, @Optional String environment) {

        setVariables(environment, browser);
        driver.set(new DriverSetup(TestBase.environment.get(), TestBase.browser.get()).setWebDriver());
        getDriver().manage().window().maximize();
        getDriver().get(properties.getTestUrl());
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters(value = {"properties"})
    public synchronized void beforeSuite(@Optional("local.properties") String propertiesFile) {
        properties = new TestProperties(readPropertiesFromFile(propertiesFile));
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {

        if (getDriver() != null) {
            getDriver().quit();
        }
    }
}
