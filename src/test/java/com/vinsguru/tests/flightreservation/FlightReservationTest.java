package com.vinsguru.tests.flightreservation;

import com.vinsguru.pages.flightreserve.*;
import com.vinsguru.tests.abstractTest;
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


public class FlightReservationTest extends abstractTest {

    private String expectedPrice;
    private String noOfPassengers;
    private Registrationpage registrationPage;
    //following parameters come from the file "flight-reservation.xml"
    private ConfirmationReservationpage confirmationReservationpage;
    private FlightsSearchPage flightSearchPage;
    private Selectflightpage selectflightpage;
    private FlightConfirmationPage flightConfirmationPage;
    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})

    public void setParameters(String noOfPassengers,String expectedPrice) {
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;

        // init the class objects:
        //this avoids repetition in @Test anotations bellow:
        this.registrationPage = new Registrationpage(driver);
        this.confirmationReservationpage = new ConfirmationReservationpage(driver);
        this.flightSearchPage = new FlightsSearchPage(driver);
        this.selectflightpage=new Selectflightpage(driver);
        this.flightConfirmationPage = new FlightConfirmationPage (driver);
    }
    @Test
    public void RegistrationPageTest(){

        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserdetails("lex","22");
        registrationPage.enteruserCredentials("lex22@evo.com","pass@123");
        registrationPage.enterAddress("colo","mede","132455");
        registrationPage.register();

    }
    @Test(dependsOnMethods= "RegistrationPageTest")
    public void ConfirmationReservationpageTest(){

        Assert.assertTrue(confirmationReservationpage.isAt());
        confirmationReservationpage.gotoFlight();
    }

    @Test(dependsOnMethods ="ConfirmationReservationpageTest")
    public void FlightSearchPageTest(){

        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(noOfPassengers);
        flightSearchPage.searchFlights();

    }

    @Test(dependsOnMethods = "FlightSearchPageTest" )
    public void SelectflightpageTest(){

        Assert.assertTrue(selectflightpage.isAt());
        selectflightpage.selectflights();
        selectflightpage.confirmflights();
    }

    @Test ( dependsOnMethods = "SelectflightpageTest" )
    public void FlightConfirmationPageTest() {

        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(),expectedPrice);

    }




}