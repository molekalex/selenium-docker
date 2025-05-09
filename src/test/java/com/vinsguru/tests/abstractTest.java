package com.vinsguru.tests;

import com.vinsguru.listener.TestListener;
import com.vinsguru.pages.vendorportal.dashboardPage;
import com.vinsguru.pages.vendorportal.loginPage;
import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListener.class})
public abstract class abstractTest {
    //this class is intended to avoid repetition of @BeforeTest and @AfterTest
    // in various Test Classes: FlightReservationTest, vendorportalTest
    protected WebDriver driver;
    private final static Logger log = LoggerFactory.getLogger(abstractTest.class);

    @BeforeSuite
    public void setupConfig(){
        Config.initialized();
    }

    @BeforeTest //agrego el parametro ctx
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        //here where are creating the drivers for each THREAD in the PARALLEL execution

        //using ternary operation instead of if(){} structure
 this.driver=Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
    ctx.setAttribute(Constants.DRIVER, this.driver);

    //ultima:
        //antigua manera de invocar los metodos remoto..., local...
        /*System.getProperty("selenium.grid.enabled");
        if(Boolean.getBoolean("selenium.grid.enabled"))
        {this.driver=getRemoteDriver();}else
        {this.driver=getLocalDriver();}*/
    }

    //metodo para trabajar con el driver local
    private WebDriver getLocalDriver() {
        /*WebDriverManager.chromedriver().setup();
        return new ChromeDriver();*/
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
        /*WebDriverManager.edgedriver().setup();
        return new EdgeDriver();*/
    }
    //metodo para trabajar con selenium grid:
    private WebDriver getRemoteDriver() throws MalformedURLException {

        Capabilities capabilities = new EdgeOptions();
        //matches the constants values in the default.properties and the POM file
        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
            capabilities = new FirefoxOptions();
        }else if(Constants.CHROME.equalsIgnoreCase(Config.get(Constants.BROWSER))){
            capabilities  = new ChromeOptions();}
          //ultima:
         //previous code replace following:
        //if(System.getProperty("browser").equalsIgnoreCase("chrome"))
        //{capabilities = new ChromeOptions();
        //}else{capabilities = new FirefoxOptions();}


        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);

        //usando el placeholder %s
        //format the the URL using http://%s:4444/wd/hub & localhost -> http://localhost:4444/wd/hub
        String hubUrl = String.format(urlFormat,hubHost);

        log.info("grid url:{}", hubUrl);

        return new RemoteWebDriver(new URL(hubUrl), capabilities);

        //old return:
        //return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }

    @AfterTest
    public void quitDriver(){this.driver.quit();}



}
