package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    @FindBy(xpath = "//*[text()='Your answer']/preceding-sibling::input")
    WebElement nameField;

    @FindBy(tagName = "textarea")
    WebElement reasonField;

    // @FindBy(xpath = "//div[@class='Qr7Oae'][3]")
    // WebElement divExperience;

    @FindBy(xpath = "//*[@data-value='3 - 5']")
    WebElement experienceValue;

    @FindBy(xpath =  "//input[@type='date']")
    WebElement dateField;

    @FindBy(xpath = "//input[@aria-label='Hour']")
    WebElement HourField;

    @FindBy(xpath = "//input[@aria-label='Minute']")
    WebElement MinuteField;

    @FindBy(xpath = "//div[@role='button']//span[text()='Submit']")
    WebElement SubmitButton;

    @FindBy(className = "vHW8K")
    WebElement thanksElem;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */

     /* hahahahah */
    @Test
    public void testCase01() throws InterruptedException{
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        Wrappers.sendKeyWrap(driver,nameField,"Crio Learner");
        String reason = "I want to be the best QA Engineer! " + System.currentTimeMillis();
        Wrappers.sendKeyWrap(driver, reasonField, reason);

        // Wrappers.scrollTo(driver, divExperience);
        Wrappers.click(driver, experienceValue);

        Wrappers.checkClick(driver, "Java");
        Wrappers.checkClick(driver, "Selenium");
        Wrappers.checkClick(driver, "TestNG");
        
        Wrappers.GenderWrapper(driver, "Ms");
        
        LocalDate date = LocalDate.now().minusDays(7);
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("ddMMYYYY");
        // Wrappers.sendKeyWrap(driver, dateField, date.format(dateformat));
        Wrappers.scrollTo(driver, dateField, "true");
        dateField.sendKeys(date.format(dateformat));
        // Wrappers.sendKeyWrap(driver, dateField, date.format(dateformat), "false");

        // LocalDateTime ldt = LocalDateTime.now();
        // DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("HH");
        // DateTimeFormatter minuteformat = DateTimeFormatter.ofPattern("mm");
        
        // Wrappers.sendKeyWrap(driver, HourField, ldt.format(hourFormat));
        // Wrappers.sendKeyWrap(driver, MinuteField, ldt.format(minuteformat));
        Wrappers.sendKeyWrap(driver, HourField, "07");
        Wrappers.sendKeyWrap(driver, MinuteField, "30");

        Wrappers.click(driver, SubmitButton);

        System.out.println(thanksElem.getText());
        Thread.sleep(5000);
    }

    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        PageFactory.initElements(driver,this);
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}