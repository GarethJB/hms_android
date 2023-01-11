package com.example.androidhms.customer;

public class CustomerCommon {

    public static String extractDate(String date) {
        String match = "[^0-9]";
        date = date.replaceAll(match, "");
        return date;
    }
}
