package ca.bcit.comp2522.bank;

/**
 * Represents a Date class that is used for BankAccount and BankClient.
 * A Date class stores the year, month and day of the date.
 *
 * @author Giant Mak, Brian Lau
 * @version 1.0
 */
public class Date {

    /* Date fields */
    private final int year;
    private final int month;
    private final int day;

    /* Year limits */
    private static final int MIN_YEAR = 1800;
    private static final int CURRENT_YEAR = 2026;

    /* Time constants */
    private static final int MONTHS_IN_YEAR = 12;
    private static final int DAYS_IN_WEEK = 7;
    private static final int FOUR = 4;
    private static final int SIX = 6;
    private static final int TWO = 2;
    private static final int HUNDRED = 100;
    private static final int FOUR_HUNDRED = 400;

    /* Month numbers */
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

    /* Month codes */
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

    /* Day limits */
    private static final int MIN_DAY = 1;
    private static final int FEB_MAX_DAY = 29;
    private static final int FEB_MIN_DAY = 28;
    private static final int AJSN_MAX_DAY = 30;
    private static final int REST_MAX_DAY = 31;

    /* Century thresholds */
    private static final int YEAR_1800 = 1800;
    private static final int YEAR_1900 = 1900;
    private static final int YEAR_2000 = 2000;

    /* changed single digits to double digits */
    private static final int CHNG_TO_DD = 10;

    /**
     * Constructs a Date with validation.
     * @param year of the date
     * @param month of the date
     * @param day of the date
     */
    public Date(final int year, final int month, final int day) {
        validateYear(year);
        validateMonth(month);
        validateDay(year, month, day);

        this.year = year;
        this.month = month;
        this.day = day;
    }

    /* ---------------- Validation ---------------- */

    private static void validateYear(final int year) {
        if (year < MIN_YEAR || year > CURRENT_YEAR) {
            throw new IllegalArgumentException("Invalid year: " + year);
        }
    }

    private static void validateMonth(final int month) {
        if (month < JANUARY || month > DECEMBER) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    private static boolean isLeapYear(final int year) {
        return (year % FOUR == 0 && year % HUNDRED != 0)
                || (year % FOUR_HUNDRED == 0);
    }

    private static void validateDay(final int year, final int month, final int day) {
        if (day < MIN_DAY) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }

        int maxDay;

        switch (month) {
            case APRIL, JUNE, SEPTEMBER, NOVEMBER -> maxDay = AJSN_MAX_DAY;
            case FEBRUARY -> maxDay = isLeapYear(year) ? FEB_MAX_DAY : FEB_MIN_DAY;
            default -> maxDay = REST_MAX_DAY;
        }

        if (day > maxDay) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
    }

    /* ---------------- Getters ---------------- */

    public int getDay() { return day; }

    public int getMonth() { return month; }

    public int getYear() { return year; }

    /* ---------------- Formatting ---------------- */

    private static String placeHolder(final int n) {
        return (n < CHNG_TO_DD ? "0" : "") + n;
    }

    public String getYYYYMMDD() {
        return year + "-" + placeHolder(month) + "-" + placeHolder(day);
    }

    /* ---------------- Day of Week ---------------- */

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
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };
    }

    public String getDayOfWeek() {
        int total = 0;

        if (year >= YEAR_2000) {
            total += SIX;
        }
        if (year >= YEAR_1800 && year < YEAR_1900) {
            total += TWO;
        }
        if (isLeapYear(year) && (month == JANUARY || month == FEBRUARY)) {
            total += SIX;
        }

        int yearPart = year % HUNDRED;
        int twelves = yearPart / MONTHS_IN_YEAR;
        int remainder = yearPart - (twelves * MONTHS_IN_YEAR);
        int fours = remainder / FOUR;

        total += twelves + remainder + fours + day + getMonthCode();
        total %= DAYS_IN_WEEK;

        return switch (total) {
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

    public String getMonthName() {
        return switch (month) {
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
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };
    }

    @Override
    public String toString() {
        return getDayOfWeek() + ", " + getMonthName() + " " + day + ", " + year;
    }
}