package com.vinsguru.tests.flightreservation.model;

public record flightreservationTestdata(String noOfPassengers,
                                        String expectedPrice,
                                        String firstname,
                                        String lastname,
                                        String email,
                                        String password,
                                        String street,
                                        String city,
                                        String zip) {
}