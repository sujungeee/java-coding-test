package com.theory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TDate {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        System.out.println(date); // Wed Jun 04 12:50:45 KST 2025
        System.out.println(date.getTime()); // 1749009045474

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date)); // 2025-06-04 12:50:45
        Date date1 = sdf.parse("2025-01-01 00:00:00");

        System.out.println(date1); // Wed Jan 01 00:00:00 KST 2025
        System.out.println(date.after(date1)); // true
        System.out.println(date.before(date1)); // false
    }
}