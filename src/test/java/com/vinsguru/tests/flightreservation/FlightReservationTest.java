package com.vinsguru.tests.flightreservation;

import com.vinsguru.pages.flightreserve.*;
import com.vinsguru.tests.abstractTest;
import com.vinsguru.tests.flightreservation.model.flightreservationTestdata;
import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;
import com.vinsguru.util.JsonUtil;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;


public class FlightReservationTest extends abstractTest {

    private flightreservationTestdata testdata;

    private Registrationpage registrationPage;
    //following parameters come from the file "flight-reservation.xml"
    private ConfirmationReservationpage confirmationReservationpage;
    private FlightsSearchPage flightSearchPage;
    private Selectflightpage selectflightpage;
    private FlightConfirmationPage flightConfirmationPage;
    @BeforeTest
    @Parameters("testdataPath")

    public void setParameters(String testdataPath) {

        this.testdata = JsonUtil.getTestData(testdataPath, flightreservationTestdata.class);


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
         //ultima
        //registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserdetails(testdata.firstname(), testdata.lastname());
        registrationPage.enteruserCredentials(testdata.email(), testdata.password());
        registrationPage.enterAddress(testdata.street(),testdata.city(), testdata.zip());
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
        flightSearchPage.selectPassengers(testdata.noOfPassengers());
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
        Assert.assertEquals(flightConfirmationPage.getPrice(),testdata.expectedPrice());

    }




}