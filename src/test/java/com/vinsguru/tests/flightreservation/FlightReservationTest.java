package com.vinsguru.tests.flightreservation;

import com.vinsguru.pages.flightreserve.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;


public class FlightReservationTest {

    private WebDriver driver;
    private String expectedPrice;
    private String noOfPassengers;

    //following parameters come from the file "flight-reservation.xml"

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})

    public void setDriver(String noOfPassengers,String expectedPrice) {
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;

        //WebDriverManager.chromiumdriver().setup();
        //this.driver = new ChromiumDriver();

        WebDriverManager.firefoxdriver().setup();
        //System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        this.driver = new FirefoxDriver();

    }
    @Test
    public void RegistrationPageTest(){
        Registrationpage registrationPage = new Registrationpage(driver);
        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserdetails("lex","22");
        registrationPage.enteruserCredentials("lex22@evo.com","pass@123");
        registrationPage.enterAddress("colo","mede","132455");
        registrationPage.register();

    }
    @Test(dependsOnMethods= "RegistrationPageTest")
    public void ConfirmationReservationpageTest(){
        ConfirmationReservationpage confirmationReservationpage = new ConfirmationReservationpage(driver);
        Assert.assertTrue(confirmationReservationpage.isAt());
        confirmationReservationpage.gotoFlight();
    }

    @Test(dependsOnMethods ="ConfirmationReservationpageTest")
    public void FlightSearchPageTest(){
        FlightsSearchPage flightSearchPage = new FlightsSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(noOfPassengers);
        flightSearchPage.searchFlights();

    }

    @Test(dependsOnMethods = "FlightSearchPageTest" )
    public void SelectflightpageTest(){
        Selectflightpage selectflightpage=new Selectflightpage(driver);
        Assert.assertTrue(selectflightpage.isAt());
        selectflightpage.selectflights();
        selectflightpage.confirmflights();
    }

    @Test ( dependsOnMethods = "SelectflightpageTest" )
    public void FlightConfirmationPageTest() {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage (driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(),expectedPrice);

    }

    @AfterTest
    public void quitDriver(){
         this.driver.quit();
    }


}