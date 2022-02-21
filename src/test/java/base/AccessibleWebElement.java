package base;

import org.openqa.selenium.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class AccessibleWebElement {
    private final WebElement element;
    private final WebDriver driver;

    public AccessibleWebElement(WebElement element, WebDriver driver) {
        this.element = element;
        this.driver = driver;
    }

    public void click() {
        clickWithRetryForClickIntercepted();
    }

    private void clickWithRetryForClickIntercepted() {
        int attempts = 0;
        while (true) {
            try {
                element.click();
                break;
            } catch (ElementClickInterceptedException e) {
                attempts += 1;
                if (attempts == 5) throw e;
            }
        }
    }

    public void clear() {
        element.clear();
    }

    public void clearByBackspace() {
        while (element.getAttribute("value").length() > 0) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void setValueByJs(String value) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].value='" + value + "';", element);
    }

    public void clickByJs() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].click();", element);
    }

    public void sendKeys(CharSequence... keys) {
        element.sendKeys(keys);
    }

    public <T extends CoreBaseLoadablePage> T clickTo(Class<T> targetPage) {
        click();
        try {
            return targetPage.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(String.format("Error during creating new instance of %s", targetPage.toString()), e);
        }
    }

    public List<WebElement> findElements(By selector) {
        return element.findElements(selector);
    }

    public String getCssValue(String value) {
        return element.getCssValue(value);
    }

    public String getText() {
        return element.getText();
    }

    public String getAttribute(String attribute) {
        return element.getAttribute(attribute);
    }

    public AccessibleWebElement getInputElement() {
        return new AccessibleWebElement(element.findElement(By.cssSelector("input")), driver);
    }
}
