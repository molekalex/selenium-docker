package com.vinsguru.pages.flightreserve;

import com.vinsguru.pages.abstractPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Selectflightpage extends abstractPages {


    @FindBy(name = "departure-flight")
    private List<WebElement> departureOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalOptions;

    @FindBy(id = "confirm-flights")
    private WebElement departureButton;


    public Selectflightpage(WebDriver driver){
        super(driver);
         }

    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.departureButton));
        return this.departureButton.isDisplayed();
    }

 public void selectflights(){
        int random = ThreadLocalRandom.current().nextInt(0, departureOptions.size());
        this.departureOptions.get(random).click();
        this.arrivalOptions.get(random).click();
 }

 public void confirmflights(){
        this.departureButton.click();
 }

}