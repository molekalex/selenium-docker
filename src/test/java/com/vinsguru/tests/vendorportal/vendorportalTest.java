package com.vinsguru.tests.vendorportal;

import com.fasterxml.jackson.databind.jsontype.impl.AsExistingPropertyTypeSerializer;
import com.vinsguru.pages.vendorportal.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class vendorportalTest {

    private WebDriver driver;
    private String user;
    private String password;
    private String searchItem;
    private loginPage loginpage;
    private dashboardPage dashboardpage;
    @BeforeTest
    @Parameters({"user", "password", "searchItem"})

    public void setDriver(String user, String password, String searchItem) {
        this.user = user;
        this.password = password;
        this.searchItem = searchItem;


        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        //WebDriverManager.firefoxdriver().setup();
        //this.driver = new FirefoxDriver();

        this.loginpage = new loginPage(driver);
        this.dashboardpage = new dashboardPage(driver);


    }

    @Test
    public void loginPageTest() {

        loginpage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        loginpage.login(user, password);
        Assert.assertTrue(loginpage.isAt());

    }

    @Test(dependsOnMethods = "loginPageTest")
    public void dashboardTest() {

        dashboardpage.search(searchItem);
        Assert.assertEquals(dashboardpage.getSearchResultsCount(), 92);
        Assert.assertTrue(dashboardpage.isAt());



        Assert.assertEquals(dashboardpage.getAnnualEarning(), "$215,000");
        Assert.assertEquals(dashboardpage.getMonthlyEarning(), "$40,000");
        Assert.assertEquals(dashboardpage.getProfit(), "50%");
        Assert.assertEquals(dashboardpage.getAvailableInventory(), "18");
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardpage.logout();
        Assert.assertTrue(loginpage.isAt());
    }

    @AfterTest
    public void quitDriver(){this.driver.quit();}


}
