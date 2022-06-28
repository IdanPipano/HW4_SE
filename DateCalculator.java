

/**
 * This class is responsible for calculating what date we get when we add/subtract
 * a given number of days from a certain date.
 */
public class DateCalculator {

    private static final int FEBRUARY = 2;
    private static final int DECEMBER = 12;
    private static final int DAYS_IN_STANDARD_YEAR = 365; // STANDARD meaning not a leap year
    private static final int[] DAYS_IN_STANDARD_MONTH = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    }; // entry j is how many days there are in month j + 1 in a standard year


    /**
     * This function gets a Date and an integer num. It calculates and returns the date num days from
     * the given date. If num < 0, calculates the date |num| days before the given date.
     * @param date (Date) the given date
     * @param num (int) the number of days (can be positive, 0 or negative).
     * @return (Date) the date num days from the given date.
     */
    public static Date addToDate(Date date, int num) {
        if (num == 0)
            return date;
        else if (num > 0)
            return addToDatePositive(date, num);
        else
            return addToDateNegative(date, num);

    }

    /**
     * This function gets a number num >= 0 and calculates recursively the date
     * num days after the given date.
     * @param date (Date) the given date.
     * @param num (int) >= 0 the number of days.
     * @return (Date) the date num days after the given date.
     */
    public static Date addToDatePositive(Date date, int num) {
        if (num == 0) return date;
        int day = date.getDay(), month = date.getMonth(), year = date.getYear();
        int daysUntilYearEnd = daysUntilYearEnd(date);
        if (daysUntilYearEnd < num){
            return addToDatePositive(new Date(1,1, year + 1), num - 1 - daysUntilYearEnd);
        }
        // the following line is merely for readability
        if (daysUntilYearEnd == num) return new Date(31, DECEMBER, year);
        // if we reached here, num < daysUntilYearEnd
        int daysLeftInMonth = daysLeftInMonth(date);
        if (daysLeftInMonth < num){
            return addToDatePositive(new Date(1, month + 1, year), num - 1 - daysLeftInMonth);
        }
        return new Date(day + num, month, year);
    }

    /**
     * This function gets a number num <= 0 and calculates recursively the date
     * |num| days before the given date.
     * @param date (Date) the given date.
     * @param num (int) <= 0 the number of days.
     * @return (Date) the date |num| days before the given date.
     */
    public static Date addToDateNegative(Date date, int num) {
        if (num == 0) return date;
        int day = date.getDay(), month = date.getMonth(), year = date.getYear();
        int daysUntilYearStart = daysUntilYearStart(date);
        if ((-1)*num > daysUntilYearStart)
            return addToDateNegative(new Date(31, DECEMBER, year - 1),
                    num + 1 + daysUntilYearStart);

        // if we reached here, num in absolute value is <= daysUntilYearStart
        if ((-1) * num > day) //if we reach here, month is > 1 (it is not January)..
            return addToDateNegative(new Date(howManyDaysInMonth(month - 1, year),
                    month - 1, year), num + day);
        return new Date(day + num, month, year);
    }

    /**
     * Returns the days from the given date (excluding) to the first day of the last year (including).
     * @param date (Date) the date for which we want to calculate the number of days till the year ends.
     * @return (int) the days from the given date (excluding) to the first day of the last year (including)
     */
    public static int daysUntilYearEnd(Date date){
        int sumDays = daysLeftInMonth(date);
        for (int month = date.getMonth() + 1; month <= DECEMBER; ++month){
            sumDays += howManyDaysInMonth(month, date.getYear());
        }
        return sumDays;
    }

    /**
     * Returns the number of days from 1.1 in the current year (including) to the given date (excluding).
     * @param date (Date) the given date.
     * @return (int) a positive integer.
     */
    public static int daysUntilYearStart(Date date){
        return DAYS_IN_STANDARD_YEAR + (isLeapYear(date.getYear()) ? 1 : 0) - daysUntilYearEnd(date) - 1;
    }

    /**
     * Returns the number of days from the given date (excluding) to the last day in the current month (including).
     * @param date (Date) the given date.
     * @return (int) a positive integer
     */
    public static int daysLeftInMonth(Date date) {
        return howManyDaysInMonth(date.getMonth(), date.getYear()) - date.getDay();
    }

    /**
     * Returns the number of days the current month has in the current year.
     * @param month (int) the current month
     * @param year (int) the current year
     * @return (int) the number of days in the given month in the given year
     */
    public static int howManyDaysInMonth(int month, int year){
        if (month == FEBRUARY && isLeapYear(year)) {
            // in leap years, February has 29 days and in standard years it has 28 days.
            return DAYS_IN_STANDARD_MONTH[month - 1] + 1;
            //DAYS_IN_STANDARD_MONTH is an array of constants.
            // Entry j in DAYS_IN_STANDARD_MONTH is how many days there are in month j+1 in a standard year.
        }
        return DAYS_IN_STANDARD_MONTH[month - 1];
    }

    /**
     * checks whether a given year is a leap year (Shana Meuberet).
     * @param year (int) the given year
     * @return (boolean) true if the year is a leap year, false otherwise.
     */
    public static boolean isLeapYear(int year){
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
