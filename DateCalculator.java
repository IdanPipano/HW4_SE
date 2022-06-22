/**
 *
 */
public class DateCalculator {
    private final int[] daysInMonth = new int[]{
            31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    }; // entry j is how many days are in month j. for February we'll check separately.

    public static Date addToDate(Date date, int num) {

    }

    /**
     * Returns the number of days from the current date (excluding) to the last
     * @param date
     * @return
     */
    private int daysLeftInMonth(Date date){
        return howManyDaysInMonth(date.getMonth(), date.getYear()) - date.getDay();
    }

    /**
     * Returns the number of days the current month has in the current year.
     * @param month
     * @param year
     * @return
     */
    private int howManyDaysInMonth(int month, int year){
        if (month == 2){
            return isLeapYear(year) ? 29 : 28;
        }
        return this.daysInMonth[month];
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


