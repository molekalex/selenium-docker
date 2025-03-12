package com.vinsguru.pages.vendorportal;

import com.vinsguru.pages.abstractPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class loginPage extends abstractPages {

    //url for the test site.
    //https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html
    public loginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "#username")
    private WebElement usernameInput;
    @FindBy(id= "password")
    private WebElement passwordInput;


    @FindBy(id = "login")
    private WebElement loginButton;


    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf((this.loginButton)));
        return this.loginButton.isDisplayed();
    }


    public void goTo (String url){
        this.driver.get(url);
    }
    public void login(String username, String password){
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.loginButton.click();
    }


}
