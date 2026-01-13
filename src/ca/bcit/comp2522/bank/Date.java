package ca.bcit.comp2522.bank;

/**
 * Date:
 *
 * @author Giant Mak
 * @version 1.0
 */
class Date {
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
    private static final int[] MONTH_CODES =
    {
        1, // January
        4, // February
        4, // March
        0, // April
        2, // May
        5, // June
        0, // July
        3, // August
        6, // September
        1, // October
        4, // November
        6  // December
    };

    /**
     * Instantiate a Date class with validate helper methods.
     *
     * @param year year of the date
     * @param month month of the date
     * @param day day of the date
     */
    Date(final int year, final int month, final int day)
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
    int getDay()
    {
        return day;
    }

    /**
     * Returns the month.
     * @return month
     */
    int getMonth()
    {
        return month;
    }

    /**
     * Returns the year.
     * @return year
     */
    int getYear()
    {
        return year;
    }

    /**
     * Returns the date in YYYYMMDD format.
     * @return date
     */
    String getYYYYMMDD()
    {
        return year + "-" + month + "-" + day;
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
    String getDayOfWeek()
    {
        int total = 0;

        // Step 0: Year adjustment
        if(year >= 2000)
        {
            total += SIX;
        }
        else if(year >= 1800 && year < 1900)
        {
            total += TWO;
        }
        else if(isLeapYear(year) && (month == 1 || month == 2))
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
        total += MONTH_CODES[month - 1];

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
