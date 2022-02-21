package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CoreBasePage {
    public static final int WAIT_FOR_CLICKABLE_TIMEOUT = 10;
    protected final WebDriver driver;
    public UsefullWaits waits;
    protected Actions actions;

    public CoreBasePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        waits = new UsefullWaits(driver);
    }

    public AccessibleWebElement waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_FOR_CLICKABLE_TIMEOUT);
        JavascriptExecutor js = ((JavascriptExecutor) driver);

        int attempts = 0;
        while (true) {
            try {
                waitForElementExistance(element);

                js.executeScript("arguments[0].scrollIntoView(true);", element);

                return new AccessibleWebElement(waits.explicitWait(WAIT_FOR_CLICKABLE_TIMEOUT).until(ExpectedConditions.elementToBeClickable(element)), driver);
            } catch (StaleElementReferenceException e) {
                attempts += 1;
                if (attempts == 10) throw e;
            }
        }
    }

    public AccessibleWebElement waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_FOR_CLICKABLE_TIMEOUT);
        JavascriptExecutor js = ((JavascriptExecutor) driver);

        int attempts = 0;
        while (true) {
            try {
                waitForElementExistance(element);

                js.executeScript("arguments[0].scrollIntoView(true);", element);

                return new AccessibleWebElement(waits.explicitWait(WAIT_FOR_CLICKABLE_TIMEOUT).until(ExpectedConditions.visibilityOf(element)), driver);
            } catch (StaleElementReferenceException e) {
                attempts += 1;
                if (attempts == 10) throw e;
            }
        }
    }

    public void hoverOnElement(WebElement element) {
        waitForElementToBeClickable(element);
        actions.moveToElement(element).perform();
        //TODO FIX IN SOME BETTER WAY - heatmap test is failing sometimes without sleep.
        // moveToElement() moves mouse above heatmap. If mouse is moving from A3 to A1, then mouse is moved above
        // A2, and when test is trying to read data from tooltip form well A1 it can be taken from A2 instead...
        // Now test is waiting to have well label in hover text before reading data, but for AQ if we have hyperwell
        // data in hover text is exactly same for both wells.
        // First idea how to fix it is to add some well position indicator to hover text.
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getBrowserName() {
        return ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
    }

    @Step("Scroling to the element")
    public void scrollToWebElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Step("Press enter key")
    public void pressEnterKey() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(200);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void clearElementByBackspaceKey(WebElement inputElement, int textLength) {
        waitForElementToBeClickable(inputElement).click();
        for (int i = 0; i < textLength; i++) {
            waitForElementToBeClickable(inputElement).sendKeys(Keys.BACK_SPACE);
        }
    }

    private void waitForElementExistance(WebElement element) {
        waits.explicitWait(WAIT_FOR_CLICKABLE_TIMEOUT).until(webdriver -> {
            try {
                element.getSize();
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        });
    }
}
