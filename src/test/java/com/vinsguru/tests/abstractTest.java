package com.vinsguru.tests;

import com.vinsguru.pages.vendorportal.dashboardPage;
import com.vinsguru.pages.vendorportal.loginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public abstract class abstractTest {
     //this class is intended to avoid repetition of @BeforeTest and @AfterTest
     // in various Test Classes: FlightReservationTest, vendorportalTest
    protected WebDriver driver;
 @BeforeTest
 public void setDriver() {

     WebDriverManager.chromedriver().setup();
     this.driver = new ChromeDriver();

     //using firefox:
     //WebDriverManager.firefoxdriver().setup();
     //this.driver = new FirefoxDriver();
 }

    @AfterTest
    public void quitDriver(){this.driver.quit();}



}
