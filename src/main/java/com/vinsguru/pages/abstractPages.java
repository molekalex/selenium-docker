package com.vinsguru.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class abstractPages {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public abstractPages(WebDriver driver){
        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver,this);
        }

        public abstract boolean isAt();

    }