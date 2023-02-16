package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
//import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
    public static WebDriver driver;
    public static Properties prop;
    public static WebDriverWait wait;
    public static WebEventListener eventListener;
    public static EventFiringWebDriver e_driver;
    public static Logger log = Logger.getLogger(TestBase.class);
    public static String filePath = System.getProperty("user.dir");
    
    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(filePath + "\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {
        String browserName = prop.getProperty("browser");
        if (browserName.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\SeleniumTesting\\SeleniumJars\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equals("FireFox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\SeleniumTesting\\SeleniumJars\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equals("Internet Explorer")) {
            System.setProperty("webdriver.ie.driver", "C:\\SeleniumTesting\\SeleniumJars\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        } else {
			System.out.println("browser not found.");
		}
        
// Now create object of EventListerHandler to register it with EventFiringWebDriver
        e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
        
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
        wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtil.EXPLICIT_WAIT));

        driver.get(prop.getProperty("url"));
        log.info("*****************************************Entering url**************************************************");
    }
}
