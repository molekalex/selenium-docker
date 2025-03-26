package com.vinsguru.tests;

import com.vinsguru.pages.vendorportal.dashboardPage;
import com.vinsguru.pages.vendorportal.loginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class abstractTest {
    //this class is intended to avoid repetition of @BeforeTest and @AfterTest
    // in various Test Classes: FlightReservationTest, vendorportalTest
    protected WebDriver driver;

    @BeforeTest
    public void setDriver() throws MalformedURLException {
        System.getProperty("selenium.grid.enabled");
        if(Boolean.getBoolean("selenium.grid.enabled"))
        {this.driver=getRemoteDriver();}else
        {this.driver=getLocalDriver();}
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();

        //using firefox:
        //WebDriverManager.firefoxdriver().setup();
        //this.driver = new FirefoxDriver();
    }

    //metodo para trabajar con el archivo POM
    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
    //
    private WebDriver getRemoteDriver() throws MalformedURLException {

        Capabilities capabilities;
        if(System.getProperty("browser").equalsIgnoreCase("chrome"))
        {
            capabilities = new ChromeOptions();
        }else
        {capabilities = new FirefoxOptions();}
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }

    @AfterTest
    public void quitDriver(){this.driver.quit();}



}
