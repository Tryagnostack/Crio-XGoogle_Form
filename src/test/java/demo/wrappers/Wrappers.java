package demo.wrappers;

import java.util.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public static void sendKeyWrap(WebDriver driver, WebElement element, String text){
        scrollTo(driver, element, "true");
        element.click();
        element.sendKeys(text);
    }
    public static void sendKeyWrap(WebDriver driver, WebElement element, String text, String flag){
        scrollTo(driver, element, flag);
        element.click();
        element.sendKeys(text);
    }

    public static void click(WebDriver driver, WebElement element){
        scrollTo(driver, element, "true");
        element.click();
    }

    public static void checkClick(WebDriver driver, String text){
        WebElement element = driver.findElement(By.xpath("//*[text()='" + text + "']"));
        scrollTo(driver, element, "true");
        element.click();
    }

    public static void GenderWrapper(WebDriver driver, String choice) throws InterruptedException{
        WebElement selectionBox = driver.findElement(By.xpath("//*[text()='Choose']"));
        scrollTo(driver, selectionBox, "true");
        selectionBox.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']//span[@class='vRMGwf oJeWuf'][normalize-space()='"+choice+"']")));
        
        WebElement item = driver.findElement(By.xpath("//div[@role='option']//span[@class='vRMGwf oJeWuf'][normalize-space()='"+choice+"']"));
        item.click();
        Thread.sleep(500);
     
    }

    public static void scrollTo(WebDriver driver, WebElement element,String flag){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView("+flag+")",element);
    }
}
