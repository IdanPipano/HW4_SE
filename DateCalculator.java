public class DateCalculator {

    private static final int FEBRUARY = 2;
    private static final int DAYS_IN_STANDARD_YEAR = 365;
    private static final int[] DAYS_IN_STANDARD_MONTH = new int[]{
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    }; // entry j is how many days are in month j. for February we'll check separately.

    public static Date addToDate(Date date, int num) {
        if (num == 0)
            return date;
        else if (num > 0)
            return addToDatePositive(date, num);
        else
            return addToDateNegative(date, -num);
        // TODO: Add your code here...
    }

    public static Date addToDatePositive(Date date, int num) {
        return date;
        // TODO: Add your code here...
    }

    public static Date addToDateNegative(Date date, int num) {
        return date;
        // TODO: Add your code here...
    }

    public static int daysUntilYearEnd(Date date){
        int sumDays = daysLeftInMonth(date);
        for (int i = date.getMonth() + 1; i <= 12; ++i){
            sumDays += howManyDaysInMonth(i, date.getYear());
        }
        return sumDays;
    }

    public static int daysUntilYearStart(Date date){
        return DAYS_IN_STANDARD_YEAR + (isLeapYear(date.getYear()) ? 1 : 0) - daysUntilYearEnd(date) - 1;
    }

    /**
     * Returns the number of days from the current date (excluding) to the last day in the current month (including).
     * @param date (Date) the date we want to find how many
     * @return
     */
    public static int daysLeftInMonth(Date date) {
        return howManyDaysInMonth(date.getMonth(), date.getYear()) - date.getDay();
    }

    public static int daysPasseMonth(Date date) {
        return date.getMonth() - 1;
    }

    /**
     * Returns the number of days the current month has in the current year.
     * @param month
     * @param year
     * @return
     */
    public static int howManyDaysInMonth(int month, int year){
        if (month == FEBRUARY && isLeapYear(year)) {
            return DAYS_IN_STANDARD_MONTH[month-1] + 1;
        }
        return DAYS_IN_STANDARD_MONTH[month-1];
    }

    /**
     * checks whether a year is a leap year (Shana Meuberet).
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year){
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
