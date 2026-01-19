package ca.bcit.comp2522.bank;

/**
 * Represents a Date class that is used for BankAccount and BankClient
 * A Date class stores the year, month and day of the date.
 * Contains simple methods to calculate
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

    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;

    private static final int JAN_CODE = 1;
    private static final int FEB_CODE = 4;
    private static final int MAR_CODE = 4;
    private static final int APR_CODE = 0;
    private static final int MAY_CODE = 2;
    private static final int JUN_CODE = 5;
    private static final int JUL_CODE = 0;
    private static final int AUG_CODE = 3;
    private static final int SEP_CODE = 6;
    private static final int OCT_CODE = 1;
    private static final int NOV_CODE = 4;
    private static final int DEC_CODE = 6;
    /**
     *
     * @return month code for each month
     */
    private int getMonthCode() {
        return switch (month) {
            case JANUARY -> JAN_CODE;
            case FEBRUARY -> FEB_CODE;
            case MARCH -> MAR_CODE;
            case APRIL -> APR_CODE;
            case MAY -> MAY_CODE;
            case JUNE -> JUN_CODE;
            case JULY -> JUL_CODE;
            case AUGUST -> AUG_CODE;
            case SEPTEMBER -> SEP_CODE;
            case OCTOBER -> OCT_CODE;
            case NOVEMBER -> NOV_CODE;
            case DECEMBER -> DEC_CODE;
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

    private static void validateYear(final int year)
    {
        if(1800 > year || year > CURRENT_YEAR)
        {
            throw new IllegalArgumentException("Invalid year: " + year);
        }
    }

    private static void validateMonth(final int month)
    {
        if(1 > month || month > 12)
        {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
    }


    private static boolean isLeapYear(final int year)
    {

        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }


    private static void validateDay(final int year, final int month, final int day)
    {
        if(day < 1)
        {
            throw new IllegalArgumentException("Invalid day: " + day);
        }

        int maxDay;

        switch(month)
        {
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> maxDay = 30;
            case FEBRUARY -> maxDay = isLeapYear(year) ? 29 : 28;
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

    private static String pad2(final int n) {
        return (n < 10 ? "0" : "") + n;
    }

    /**
     * Returns the date in YYYYMMDD format.
     * @return date
     */
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

    /**
     * Returns the name of the month based on its numeric month value
     * @return the name of the month
     */
    public String getMonthName() {
        return switch (month){
            case JANUARY -> "January";
            case FEBRUARY -> "February";
            case MARCH -> "March";
            case APRIL -> "April";
            case MAY -> "May";
            case JUNE -> "June";
            case JULY -> "July";
            case AUGUST -> "August";
            case SEPTEMBER -> "September";
            case OCTOBER -> "October";
            case NOVEMBER -> "November";
            case DECEMBER -> "December";
            default -> throw new IllegalArgumentException("Invalid month: "+ month);
        };
    }

    @Override
    public String toString() {
        return getDayOfWeek() + ", "
                + getMonthName() + " "
                + day + ", "
                + year;
    }
}
