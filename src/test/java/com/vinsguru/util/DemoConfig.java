package com.vinsguru.util;

public class DemoConfig {
    public static void main(String[] args) {
        //override the property browser:
        System.setProperty("browser","edge");
        //calling the static method
        Config.initialized();

        //
        System.out.println(Config.get("abstractPages.waitTime"));


    }
}