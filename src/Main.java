import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Collections;

class MyDate implements Comparable<MyDate> {
    private int dayOfMonth;
    private int monthOfYear;
    private int yearOfDate;

    public MyDate(int dayOfMonth, int monthOfYear, int yearOfDate) {
        this.dayOfMonth = dayOfMonth;
        this.monthOfYear = monthOfYear;
        this.yearOfDate = yearOfDate;
        if (!isDateValid()) {
            this.dayOfMonth = 1;
            this.monthOfYear = 1;
            this.yearOfDate = 1970;
        }
    }

    public boolean isDateValid() {
        if (yearOfDate < 1 || monthOfYear < 1 || monthOfYear > 12 || dayOfMonth < 1) {
            return false;
        }
        int maxDays = 31;
        if (monthOfYear == 4 || monthOfYear == 6 || monthOfYear == 9 || monthOfYear == 11) {
            maxDays = 30;
        } else if (monthOfYear == 2) {
            if ((yearOfDate % 4 == 0 && yearOfDate % 100 != 0) || (yearOfDate % 400 == 0)) {
                maxDays = 29;
            } else {
                maxDays = 28;
            }
        }
        return dayOfMonth <= maxDays;
    }

    public void updateDate(int newDay, int newMonth, int newYear) {
        this.dayOfMonth = newDay;
        this.monthOfYear = newMonth;
        this.yearOfDate = newYear;
        if (!isDateValid()) {
            System.out.println("Invalid date.");
        }
    }

    public String getDayOfWeek() {
        Calendar calendar = new GregorianCalendar(yearOfDate, monthOfYear - 1, dayOfMonth);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return daysOfWeek[dayOfWeek - 1];
    }

    public int calculateDaysDifference(MyDate otherDate) {
        Calendar currentDate = new GregorianCalendar(yearOfDate, monthOfYear - 1, dayOfMonth);
        Calendar comparisonDate = new GregorianCalendar(otherDate.yearOfDate, otherDate.monthOfYear - 1, otherDate.dayOfMonth);
        long diffInMillis = Math.abs(currentDate.getTimeInMillis() - comparisonDate.getTimeInMillis());
        return (int) (diffInMillis / (24 * 60 * 60 * 1000));
    }

    public void printFormattedDate() {
        String[] monthsOfYear = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        System.out.println(monthsOfYear[monthOfYear - 1] + " " + dayOfMonth + ", " + yearOfDate);
    }

    @Override
    public int compareTo(MyDate otherDate) {
        if (yearOfDate != otherDate.yearOfDate) {
            return yearOfDate - otherDate.yearOfDate;
        }
        if (monthOfYear != otherDate.monthOfYear) {
            return monthOfYear - otherDate.monthOfYear;
        }
        return dayOfMonth - otherDate.dayOfMonth;
    }
}

public class Main {
    public static void main(String[] args) {
        MyDate myDate1 = new MyDate(1, 1, 2023);
        MyDate myDate2 = new MyDate(29, 2, 2024);
        MyDate myDate3 = new MyDate(30, 2, 2023);

        myDate1.printFormattedDate();
        myDate2.printFormattedDate();
        myDate3.printFormattedDate();

        myDate1.updateDate(15, 3, 2023);
        myDate1.printFormattedDate();

        System.out.println("Day of the week: " + myDate2.getDayOfWeek());

        System.out.println("Difference in days: " + myDate1.calculateDaysDifference(myDate2));

        ArrayList<MyDate> dateList = new ArrayList<>();
        dateList.add(new MyDate(10, 5, 2022));
        dateList.add(new MyDate(1, 1, 2023));
        dateList.add(new MyDate(29, 2, 2024));
        dateList.add(new MyDate(15, 3, 2023));

        Collections.sort(dateList);
        System.out.println("\nSorted dates:");
        for (MyDate date : dateList) {
            date.printFormattedDate();
        }
    }
}
