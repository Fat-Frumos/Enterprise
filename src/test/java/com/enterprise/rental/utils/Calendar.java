package com.enterprise.rental.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Calendar {

    public static void main(String[] args) throws ParseException {

        for (int i = 2023; i < 2028; i++) {
            String birth = String.format("25/02/%d", i);
            String dayBirth = getDayStringNew(birth);
            System.out.println(dayBirth);
        }
    }

    public static String getDayStringNew(String birth) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date myDate = dateFormat.parse(birth);
        dateFormat.applyPattern("EEE, d MMM yyyy");
        return dateFormat.format(myDate);
    }
}
