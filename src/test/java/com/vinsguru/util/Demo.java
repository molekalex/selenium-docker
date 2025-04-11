package com.vinsguru.util;

import com.vinsguru.tests.vendorportal.model.vendorportalTestdata;

import java.util.Arrays;

public class Demo {

    public static void main(String[] args) {
        vendorportalTestdata testdata = JsonUtil.getTestData("test-data/vendor-portal/john.json",vendorportalTestdata.class);
        System.out.println(testdata.montlyEarning());
    }
    }

