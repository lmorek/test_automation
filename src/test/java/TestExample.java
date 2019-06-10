import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class TestExample {

    @Test
    public void testGoogle(){

        WebDriverManager.chromedriver().setup();
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .build();
        ChromeOptions options = new ChromeOptions();

        WebDriver driver = new ChromeDriver(service,options);
        driver.get("http://www.google.pl");

        driver.quit();
    }
}
