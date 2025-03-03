package com.vinsguru.pages.flightreserve;

import com.vinsguru.pages.abstractPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FlightConfirmationPage extends abstractPages {

    private static final Logger LOG = LoggerFactory.getLogger(FlightConfirmationPage.class);
     //document.JQuerySelectorALL
    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightConfirmationElement;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement totalPriceElement;


    public FlightConfirmationPage(WebDriver driver){
    super(driver);
        //pageFactory.initElements(driver,this)
    }

    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationElement));
        return this.flightConfirmationElement.isDisplayed();
    }

    public String getPrice(){
        String confirmation = this.flightConfirmationElement.getText();
        String price = this.totalPriceElement.getText();
        //LOG.info("Flight confirmation# : {}", confirmation);
        //LOG.info("Total price : {}", price);
        return price;
    }

}
