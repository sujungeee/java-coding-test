package com.theory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TLocal {
    public static void main(String[] args) {
        // LocalDate
        LocalDate ldNow = LocalDate.now();
        LocalDate ldYesterday = LocalDate.of(2025, 6, 5);
        System.out.println(ldNow); // 2025-06-06
        System.out.println(ldYesterday); // 2025-06-05
        System.out.println(ldNow.plusDays(3)); // 2025-06-09
        System.out.println(ldNow.minusMonths(1)); // 2025-05-06
        System.out.println(ldNow.withMonth(1)); // 2025-01-06
        System.out.println(ldNow.isAfter(ldYesterday)); // true

        // LocalTime
        LocalTime ltNow = LocalTime.now();
        LocalTime ltYesterday = LocalTime.of(16, 4);
        System.out.println(ltNow); // 21:14:21.472962
        System.out.println(ltYesterday); // 16:04
        System.out.println(ltNow.plusHours(3)); // 00:14:21.472962
        System.out.println(ltNow.minusMinutes(3)); // 21:11:21.472962
        System.out.println(ltNow.withHour(3)); // 03:14:21.472962
        System.out.println(ltNow.isAfter(ltYesterday)); // true

        // LocalDateTime
        LocalDateTime ldtNow = LocalDateTime.now();
        LocalDateTime ldtYesterday = LocalDateTime.of(ldYesterday, ltYesterday);
        System.out.println(ldtNow); // 2025-06-06T21:24:42.587047
        System.out.println(ldtYesterday); // 2025-06-05T16:04
        System.out.println(ldtNow.plusHours(3)); // 2025-06-07T00:27:17.674102
        System.out.println(ldtNow.minusMinutes(3)); // 2025-06-06T21:24:17.674102
        System.out.println(ldtNow.withMonth(4)); // 2025-04-06T21:27:17.674102
        System.out.println(ldtNow.isAfter(ldtYesterday)); // true
        System.out.println(ldtNow.getYear()); // 2025
        System.out.println(ldtNow.getMonth()); // JUNE
        System.out.println(ldtNow.getDayOfWeek()); // FRIDAY
        System.out.println(ldtNow.getSecond()); // 40
    }
}