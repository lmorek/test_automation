package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UsefullWaits {
    public static final int MAX_ANIMATION_LOAD_TIME = 5;

    private WebDriver driver;


    public UsefullWaits(WebDriver driver) {
        this.driver = driver;
    }

    public void implicitWait(int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public WebDriverWait explicitWait(int timeoutInSeconds) {
        return new WebDriverWait(driver, timeoutInSeconds);
    }

    public FluentWait<WebDriver> fluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
    }


    public void waitForPageURL(String correctURL, int timeOut, int pageLoadTimeOut) throws InterruptedException {
        Allure.step(String.format("Wait until the address of the page will contain %s", correctURL));
        try {
            while (!(driver.getCurrentUrl().contains(correctURL))) {
                if (timeOut > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Allure.step("Catch InterruptedException " + e.toString());
                    }
                    timeOut--;
                } else {
                    throw new TimeoutException();
                }
            }
        } catch (NoSuchElementException e) {
            Allure.step(
                    String.format(
                            "Timeout Exception when waiting for the address of the page will contain %s", correctURL));
        }
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeOut, TimeUnit.SECONDS);
    }

    public void waitForPageToBeLoaded() {
        ExpectedCondition<Boolean> pageLoadCondition =
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public WebElement waitForVisible(WebElement element, int seconds) {
        Allure.step("Waiting until element under " + Helper.extractSelector(element) + " selector is visible");
        try {
            return new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            Allure.step("Unable to find element - waiting too long for: " + element);
            throw e;
        }
    }

    public void waitForVisible(By by, int seconds) {
        Allure.step("Waiting until element under " + by.toString() + " selector is visible");
        try {
            new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            Allure.step("Unable to find element - waiting too long for: " + by.toString());
        }
    }

    public void waitForClickable(WebElement element, int seconds) {
        Allure.step("Waiting until element under " + Helper.extractSelector(element) + " selector is clickable");
        try {
            new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Allure.step("Unable to click on element - not visible or waiting too long for:" + element);
        }
    }

    public void waitForClickable(By by, int seconds) {
        Allure.step("Waiting until element under " + Helper.extractSelector(driver.findElement(by)) + " selector is clickable");
        try {
            new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
        } catch (Exception e) {
            Allure.step("Unable to click on element - not visible or waiting too long for:" + by);
        }
    }

    public void waitForPresence(By by, int seconds) {
        Allure.step("Waiting until presence of element under " + by + " selector.");
        try {
            new WebDriverWait(driver, seconds).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            Allure.step("Unable find element - waiting too long for:" + by);
            throw e;
        }
    }

    public void waitUntilListChanges(List<WebElement> list, int seconds, int size) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);

        wait.until((ExpectedCondition<Boolean>) driver -> {
            int elementCount = list.size();
            return elementCount != size;
        });
    }

    public void waitForInvisibilityOfElement(By by, int seconds) {
        Allure.step("Waiting until invisibility of element under " + by + " selector.");
        try {
            new WebDriverWait(driver, seconds).until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            Allure.step("Waiting too long for invisibility of:" + by);
            throw e;
        }
    }
}
