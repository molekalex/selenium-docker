package com.vinsguru.tests.vendorportal;


import com.google.common.util.concurrent.Uninterruptibles;
import com.vinsguru.pages.vendorportal.*;
import com.vinsguru.tests.abstractTest;
import com.vinsguru.tests.vendorportal.model.vendorportalTestdata;
import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;
import com.vinsguru.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class vendorportalTest extends abstractTest {

    private vendorportalTestdata testdata;
    private loginPage loginpage;
    private dashboardPage dashboardpage;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath) {

        //passing parameters to the vendorportalTestdata object:
        this.testdata = JsonUtil.getTestData(testDataPath, vendorportalTestdata.class);

        //creating and init the class objects:
        this.loginpage = new loginPage(driver);
        this.dashboardpage = new dashboardPage(driver);

    }
    @Test
    public void loginPageTest() {

        //Ultima:
        //loginpage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        loginpage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        loginpage.login(testdata.username(), testdata.password());
        Assert.assertTrue(loginpage.isAt());

    }

    @Test(dependsOnMethods = "loginPageTest")
    public void dashboardTest() {

        dashboardpage.search(testdata.searchItem());
        Assert.assertEquals(dashboardpage.getSearchResultsCount(), testdata.searchResultsCount());
        Assert.assertTrue(dashboardpage.isAt());



        Assert.assertEquals(dashboardpage.getAnnualEarning(), testdata.annualEarning());
        Assert.assertEquals(dashboardpage.getMonthlyEarning(), testdata.montlyEarning());
        Assert.assertEquals(dashboardpage.getProfit(), testdata.profitMarging());
        Assert.assertEquals(dashboardpage.getAvailableInventory(), testdata.availableInventory());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardpage.logout();
        Assert.assertTrue(loginpage.isAt());
    }

@AfterTest
    public void sleep(){
        //every method here will take 6 seconds:
    Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
}

}
