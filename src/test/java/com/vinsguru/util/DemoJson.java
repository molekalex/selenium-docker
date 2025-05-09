package com.vinsguru.util;

import com.vinsguru.tests.flightreservation.model.flightreservationTestdata;
import com.vinsguru.tests.vendorportal.model.vendorportalTestdata;

public class DemoJson {

    public static void main(String[] args) {
        //bring testdate
        vendorportalTestdata testdata = JsonUtil.getTestData("test-data/vendor-portal/john.json",vendorportalTestdata.class);
        System.out.println(testdata.montlyEarning());
        //creates a record object and pass the data from the json file according to the record compiled class parameter:
        flightreservationTestdata testdata2 = JsonUtil.getTestData("test-data.flight-reservation/dan.json",flightreservationTestdata.class);
        //print a member of the record using the implicit method lastname() for this member:
        System.out.println(testdata2.lastname());
        //print all the record:
        System.out.println(testdata2);
    }
    }

