package com.enterprise.rental.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

class TestLocaleData {

    @Test
    void testCalendar() throws ParseException {
        Calendar start = Calendar.getInstance();
        Calendar finish = Calendar.getInstance();
        start.set(1939, Calendar.SEPTEMBER, 1);
        finish.set(1945, Calendar.SEPTEMBER, 3);

        int difference = dateDifference(start, finish);
        System.out.println(difference);

        SimpleDateFormat pattern = new SimpleDateFormat("dd MM yyyy");
        String begin = "01 09 1939";
        String end = "03 09 1945";
        getDays(pattern, begin, end);


        for (int i = 2022; i < 2027; i++) {
            String birth = String.format("22/02/%d", i);
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

    private static void getDays(SimpleDateFormat pattern, String begin, String end) {
        try {
            Date oldDate = pattern.parse(begin);
            Date newDate = pattern.parse(end);
            int differenceInDays = (int) ((newDate.getTime() - oldDate.getTime())
                    / (1000 * 60 * 60 * 24));
            System.out.println(differenceInDays);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int dateDifference(Calendar a, Calendar b) {

        int resultDays = 0;

        Calendar temp = a;
        a = b;
        b = temp;

        int dayOneOriginalYearDays = a.get(Calendar.DAY_OF_YEAR);

        while (a.get(Calendar.YEAR) > b.get(Calendar.YEAR)) {
            a.add(Calendar.YEAR, -1);
            resultDays += a.getActualMaximum(Calendar.DAY_OF_YEAR);
        }
        return resultDays - b.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays;
    }
}
