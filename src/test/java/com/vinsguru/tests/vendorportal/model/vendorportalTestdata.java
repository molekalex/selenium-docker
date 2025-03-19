package com.vinsguru.tests.vendorportal.model;

public record vendorportalTestdata(String username,
                                   String password,
                                   String montlyEarning,
                                   String annualEarning,
                                   String profitMarging,
                                   String availableInventory,
                                   String searchItem,
                                   int searchResultsCount) {
}

/*
public class vendorportalTestdata {
    String username;
    String password;
    String montlyEarning;
    String annualEarning;
    String profitMarging;
    String availableInventory;
    String searchItem;
    int searchResultsCount;

    public vendorportalTestdata() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMontlyEarning() {
        return montlyEarning;
    }

    public void setMontlyEarning(String montlyEarning) {
        this.montlyEarning = montlyEarning;
    }

    public String getAnnualEarning() {
        return annualEarning;
    }

    public void setAnnualEarning(String annualEarning) {
        this.annualEarning = annualEarning;
    }

    public String getProfitMarging() {
        return profitMarging;
    }

    public void setProfitMarging(String profitMarging) {
        this.profitMarging = profitMarging;
    }

    public String getAvailableInventory() {
        return availableInventory;
    }

    public void setAvailableInventory(String availableInventory) {
        this.availableInventory = availableInventory;
    }

    public String getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(String searchItem) {
        this.searchItem = searchItem;
    }

    public int getSearchResultsCount() {
        return searchResultsCount;
    }

    public void setSearchResultsCount(int searchResultsCount) {
        this.searchResultsCount = searchResultsCount;
    }
}*/
