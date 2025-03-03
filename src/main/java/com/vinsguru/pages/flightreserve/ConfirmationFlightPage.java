package com.vinsguru.pages.flightreserve;

import com.vinsguru.pages.abstractPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ConfirmationFlightPage extends abstractPages {

    private WebDriver driver;

    @FindBy(id = "go-to-flights-search")
    private WebElement gotobutton;

    //constructor methodd
    public ConfirmationFlightPage(WebDriver driver)
    {
        super(driver);
    }

    //pasa la URL?
    //public void goTo(String url){this.driver.get(url);} //why dont?
    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.gotobutton));
        return this.gotobutton.isDisplayed();
    }

    public void gotoFlight(){
        this.gotobutton.click();
    }

    public void register(){
        this.gotobutton.click();
    }

}