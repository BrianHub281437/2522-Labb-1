package ca.bcit.comp2522.bank;

/**
 * Date:
 *
 * @author Giant Mak, Brian Lau
 * @version 1.0
 */
public class Date {
    private final int year;
    private final int month;
    private final int day;

    private static final int CURRENT_YEAR = 2026;

    /* Constant for day of week calculations */
    private static final int MONTHS_IN_YEAR = 12;
    private static final int DAYS_IN_WEEK   = 7;
    private static final int FOUR           = 4;
    private static final int SIX            = 6;
    private static final int TWO            = 2;

    /* Month code for each month */
    public int getMonthCode() {
        return switch (month) {
            case 1 -> 1;
            case 2 -> 4;
            case 3 -> 4;
            case 4 -> 0;
            case 5 -> 2;
            case 6 -> 5;
            case 7 -> 0;
            case 8 -> 3;
            case 9 -> 6;
            case 10 -> 1;
            case 11 -> 4;
            case 12 -> 6;
            default -> throw new IllegalArgumentException("Invalid month:"+ month);
        };
    }

    /**
     * Instantiate a Date class with validate helper methods.
     *
     * @param year year of the date
     * @param month month of the date
     * @param day day of the date
     */
   public Date(final int year, final int month, final int day)
    {
        validateYear(year);
        validateMonth(month);
        validateDay(year, month, day);

        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Validates the year of the date.
     *
     * @param year year of the date
     * @throws IllegalArgumentException if the year is not between 1800 and current year
     */
    private static void validateYear(int year)
    {
        if(1800 > year || year > CURRENT_YEAR)
        {
            throw new IllegalArgumentException("Invalid year: " + year);
        }
    }

    /**
     * Validates the month of the date.
     *
     * @param month month of the date
     * @throws IllegalArgumentException if the month is not between 1-12
     */
    private static void validateMonth(int month)
    {
        if(1 > month || month > 12)
        {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    /**
     * Helper method to determine if the year is a leap year
     * @param year year of the date
     * @return true if the year is leap year, false otherwise
     */
    private static boolean isLeapYear(int year)
    {

        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public String getMonthName() {
        return switch (month){
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> throw new IllegalArgumentException("Invalid month: "+ month);
        };
    }

    /**
     * Validates the day of the date.
     *
     * @param year year of the date
     * @param month month of the date
     * @param day day of the date
     * @throws IllegalArgumentException if the date exceeds the max day limit of the month
     */
    private static void validateDay(int year, int month, int day)
    {
        if(day < 1)
        {
            throw new IllegalArgumentException("Invalid day: " + day);
        }

        int maxDay;

        switch(month)
        {
            case 4, 6, 9, 11 -> maxDay = 30;
            case 2 -> maxDay = isLeapYear(year) ? 29 : 28;
            default -> maxDay = 31;
        }

        if(day > maxDay)
        {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
    }

    /**
     * Returns the day.
     * @return day
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Returns the month.
     * @return month
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * Returns the year.
     * @return year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Returns the date in YYYYMMDD format.
     * @return date
     */
    private static String pad2(final int n) {
        return (n < 10 ? "0" : "") + n;
    }
    public String getYYYYMMDD()
    {
        return year + "-" + pad2(month) + "-" + pad2(day);
    }

    /**
     * Calculates the day of the week using the COMP2522 algorithm.
     * <p>
     * Steps:
     * 0. Apply Year adjustment (1800s, 1900s, 2000s) and leap year adjustment.
     * 1. Calculate number of twelves in the year.
     * 2. Calculate the remainder from step 2.
     * 3. Calculate number of fours in the remainder.
     * 4. Add the day of the month.
     * 5. Add the month code.
     * 6. Take modulo 7 to determine the day of week.
     * 7. Convert the number into day of week.
     * </p>
     * Day mapping:
     * 0 = Saturday, 1 = Sunday, 2 = Monday, ..., 6 = Friday
     *
     * @return the day of the week
     */
    public String getDayOfWeek()
    {
        int total = 0;

        // Step 0: Year adjustment
        if(year >= 2000)
        {
            total += SIX;
        }
        if(year >= 1800 && year < 1900)
        {
            total += TWO;
        }
        if(isLeapYear(year) && (month == 1 || month == 2))
        {
            total += SIX;
        }

        // Use last two digits of year
        int yearPart = year % 100;

        // Step 1: number of twelves
        int twelves = yearPart / MONTHS_IN_YEAR;
        total += twelves;

        // Step 2: remainder
        int remainder = yearPart - (twelves * MONTHS_IN_YEAR);
        total += remainder;

        // Step 3: number of fours in remainder
        int fours = remainder / FOUR;
        total += fours;

        // Step 4: add day of month
        total += day;

        // Step 5: add month code (month - 1 for index)
        total += getMonthCode();

        // Step 6: modulo 7 gives the number of day of week
        total %= DAYS_IN_WEEK;

        // Step 7: map the number to the day of week
        return switch(total)
        {
            case 0 -> "saturday";
            case 1 -> "sunday";
            case 2 -> "monday";
            case 3 -> "tuesday";
            case 4 -> "wednesday";
            case 5 -> "thursday";
            case 6 -> "friday";
            default -> throw new IllegalArgumentException("Invalid mapping number: " + total);
        };
    }

}
