package com.vinsguru.pages.flightreserve;


import com.vinsguru.pages.abstractPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Registrationpage extends abstractPages{

     //private WebDriver driver; //no needed because of super()

    @FindBy(id = "firstName")
    private WebElement firstnameinput;

    @FindBy(id = "lastName")
    private WebElement lastnameinput;

    @FindBy(id = "email")
    private WebElement emailinput;

    @FindBy(id = "password")
    private WebElement passwordinput;

    @FindBy(id = "street")
    private WebElement streetinput;

    @FindBy(id = "city")
    private WebElement cityinput;

    @FindBy(id = "zip")
    private WebElement zipinput;

    @FindBy(id = "register-btn")
    private WebElement registerbutton;

    public Registrationpage(WebDriver driver){
        //this.driver = driver;
        super(driver);
        //pageFactory.initElements(driver,this)

    }

    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.firstnameinput));
        return this.firstnameinput.isDisplayed();
        }

    public void goTo(String url){
        this.driver.get(url);
    }

    public void enterUserdetails (String firstname, String lastname){
        this.firstnameinput.sendKeys(firstname);
        this.lastnameinput.sendKeys(lastname);
        }

        public void enteruserCredentials (String email, String password){
        this.emailinput.sendKeys(email);
        this.passwordinput.sendKeys(password);
    }

    public void enterAddress (String street, String city, String zip){
        this.streetinput.sendKeys(street);
        this.cityinput.sendKeys(city);
        this.zipinput.sendKeys(zip);
    }

    public void register() {

    this.registerbutton.click();

    }
}