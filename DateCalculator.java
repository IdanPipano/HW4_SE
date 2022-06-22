public class DateCalculator {

    private final int DAYS_IN_STANDARD_YEAR = 365;
    private final int[] DAYS_IN_MONTH = new int[]{
            31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    }; // entry j is how many days are in month j. for February we'll check separately.

    public static Date addToDate(Date date, int num) {
        return date;
        // TODO: Add your code here...
    }

    private int daysUntilYearEnd(Date date){
        int sumDays = daysLeftInMonth(date);
        for (int i = date.getMonth() + 1; i <= 12; ++i){
            sumDays += howManyDaysInMonth(i, date.getYear());
        }
        return sumDays;
    }

    private int daysUntilYearStart(Date date){
        int month = date.getMonth();
        int sumDays = date.getDay();
        for (int i = month - 1; i > 0; --i){
            sumDays += howManyDaysInMonth(i, date.getYear());
        }
        return sumDays;
    }

    /**
     * Returns the number of days from the current date (excluding) to the last day in the current month (including).
     * @param date (Date) the date we want to find how many
     * @return
     */
    private int daysLeftInMonth(Date date) {
        return howManyDaysInMonth(date.getMonth(), date.getYear()) - date.getDay();
    }

    /**
     * Returns the number of days the current month has in the current year.
     * @param month
     * @param year
     * @return
     */
    private int howManyDaysInMonth(int month, int year){
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }
        return this.DAYS_IN_MONTH[month];
    }

    /**
     * checks wether a year is a leap year (Shana Meuberet).
     * @param year
     * @return
     */
    private boolean isLeapYear(int year){
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
