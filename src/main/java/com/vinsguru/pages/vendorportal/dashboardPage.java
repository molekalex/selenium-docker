package com.vinsguru.pages.vendorportal;

import com.vinsguru.pages.abstractPages;
import com.vinsguru.pages.flightreserve.FlightConfirmationPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class dashboardPage extends abstractPages {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    public dashboardPage(WebDriver driver) {
        super(driver);
    }
    JavascriptExecutor js = (JavascriptExecutor) super.driver;
    @FindBy(css = "#dataTable_filter .form-control")
    private WebElement searchBox;
    @FindBy(id = "monthly-earning")
    private WebElement monthlyLabel;
    @FindBy(id = "annual-earning")
    private WebElement annualLabel;
    @FindBy(id = "profit-margin")
    private WebElement profitLabel;
    @FindBy(id = "available-inventory")
    private WebElement availableInventory;

    @FindBy(css = "#userDropdown img") // or @FindBy(css=".img-profile")
    private WebElement profilePicture;
    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css = "#logoutModal a")
    private WebElement logoutAlert;

    @FindBy(id = "dataTable_info")
    private WebElement tableInfo;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf((this.annualLabel)));
        return this.annualLabel.isDisplayed();
    }


    public void search(String searchItem) {
        this.wait.until(ExpectedConditions.visibilityOf(this.searchBox));
        this.searchBox.sendKeys(searchItem);
    }

    public int getSearchResultsCount() {
        String resultsText = this.tableInfo.getText();
        String[] arr = resultsText.split(" ");
        //5th item not present launch and exception:
        int count = Integer.parseInt(arr[5]);
        log.info("count found : {}", count);
        //log.info("Total price : {}", price);
        return count;
    }


    public String getMonthlyEarning() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyLabel));
        return this.monthlyLabel.getText();
    }

    public String getAnnualEarning() {
        //return this.annualLabel.getText();
        String text = (String) js.executeScript("return arguments[0].textContent;", annualLabel);
        return text;
    }

    public String getProfit() {
        return this.profitLabel.getText();
    }

    public String getAvailableInventory() {
        return this.availableInventory.getText();
    }


    public void logout() {
        this.wait.until(ExpectedConditions.visibilityOf(this.profilePicture));
        this.profilePicture.click();
        //add wait if this is necessary
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutAlert));
        this.logoutAlert.click();

    }

}


