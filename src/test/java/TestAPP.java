import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TestAPP {

    @Test
    protected void betweenMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 1, 13, 2, 2);
        Date dateOne = calendar.getTime();

        calendar.set(2023, 3, 22, 13, 2, 2);
        Date dateTwo = calendar.getTime();

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOne);
        int month1 = cal.get(Calendar.MONTH);
        int year1 = cal.get(Calendar.YEAR);
        int day1 = cal.get(Calendar.DATE);

        cal.setTime(dateTwo);
        int month2 = cal.get(Calendar.MONTH);
        int year2 = cal.get(Calendar.YEAR);
        int day2 = cal.get(Calendar.DATE);

        System.out.println("from :" + dateOne.toInstant());
        System.out.println("to   :" + dateTwo.toInstant());

        Integer m1 = month2 - month1;
        int i = Math.abs(m1);
        boolean day2after15 = day2 >= 16;
        boolean day2bef15 = day2 <= 15;
        boolean sameYear = year1 == year2;
        boolean diffMonthCur2Next = i >= 1 && i < 2;
        boolean diffMonth = i != 0;
        boolean sameMonth = i == 0;
        boolean f = month1 == month2 && year1 != year2;

        if (sameMonth && sameYear) System.out.println("can update1");
        if (diffMonthCur2Next && day2bef15 && sameYear) System.out.println("can update2");

        if ((!sameMonth && sameYear && day2after15)) System.out.println("can't update1");
        if ((diffMonthCur2Next && !day2bef15 && !sameYear)) System.out.println("can't update2");
        if (!sameYear) System.out.println("can't update3");
        if (!diffMonthCur2Next && !sameMonth) System.out.println("can't update4");


        if ((!sameYear) || (!sameMonth && sameYear && day2after15) || (diffMonthCur2Next && !day2bef15 && !sameYear))


            System.out.println("Month2 - Month1 = " + i);
        System.out.println("day 2 , >15  :" + day2after15);
        System.out.println("day 2 , <=15 :" + day2bef15);
        System.out.println("Different Month              :" + diffMonth);
        System.out.println("Different now and next Month :" + diffMonthCur2Next);
        System.out.println("Same month                   :" + sameMonth);
        System.out.println("Same year                    :" + sameYear);

        System.out.println(!(sameMonth && sameYear));
        System.out.println(!(diffMonthCur2Next && day2bef15 && sameYear));

    }

    // isWithinFirstDayOfCurrentMonthTo15thOfNextMonth
    private boolean isWithinFirstDayOfCurrentMonthTo15thOfNextMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        int currentMonth = cal1.get(Calendar.MONTH);
        int currentYear = cal1.get(Calendar.YEAR);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int targetMonth = cal2.get(Calendar.MONTH);
        int targetYear = cal2.get(Calendar.YEAR);

        if (targetYear == currentYear) {
            if (targetMonth == currentMonth && cal2.get(Calendar.DAY_OF_MONTH) <= 15) {
                return true;
            } else if (targetMonth == currentMonth + 1 && cal2.get(Calendar.DAY_OF_MONTH) <= 15) {
                return true;
            }
        } else if (targetYear == currentYear + 1 && targetMonth == Calendar.JANUARY && cal2.get(Calendar.DAY_OF_MONTH) <= 15) {
            return true;
        }

        return false;
    }

    public Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);  // Month in Calendar starts from 0 (January is 0)
        return calendar.getTime();
    }

    @Test
    public void test1(){

        Date date1 = getDate(2023, 5, 1);  // Example Date1
        Date date2 = getDate(2023, 5, 16);   // Example Date2

        System.out.println(date1);
        System.out.println(date2);


        if (isWithinFirstDayOfCurrentMonthTo15thOfNextMonth(date1, date2)) {
            System.out.println("Validation: The duration is within the 1st day of the current month to the 15th day of the next month.");
        } else {
            System.out.println("Validation: The duration is not within the specified range.");
        }
    }

    // check isWithinFirstDayOfCurrentMonthTo15thOfNextMonth
    private static boolean isWithinFirstDayOfCurrentMonthTo15thOfNextMonth(LocalDate date1, LocalDate date2) {
        LocalDate firstDayOfMonth = date1.withDayOfMonth(1);
        LocalDate fifteenthDayOfNextMonth = date1.plusMonths(1).withDayOfMonth(15);

        return !date2.isBefore(firstDayOfMonth) && !date2.isAfter(fifteenthDayOfNextMonth);
    }

    @Test
    public void test2(){

        Date date11 = getDate(2023, 04, 29);
        Date date22 = getDate(2023, 05, 16);

        LocalDate localDate11 = date11.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate22 = date22.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        if (isWithinFirstDayOfCurrentMonthTo15thOfNextMonth(localDate11, localDate22)) {
            System.out.println("Can update");
        } else {
            System.out.println("Can't update");
        }

        System.out.println(localDate11);
        System.out.println(date11.toInstant());
    }

    //convert timestamp
    @Test
    public void test3(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        LocalDateTime localDateTime1 = LocalDateTime.parse(formattedDateTime, formatter);
        ZonedDateTime zonedDateTime = localDateTime1.atZone(ZoneId.of("Asia/Bangkok"));
        Timestamp timestamp = Timestamp.from(zonedDateTime.toInstant());
        String a = String.valueOf(timestamp);

        System.out.println(a);

    }

    @Test
    public void test4(){
        List<String> list = new ArrayList<>();
//        list.add("A");
//        list.add("B");

        for (int i=0 ; i<= 2; i++){
            String a = String.valueOf(UUID.randomUUID())+"-"+String.valueOf(UUID.randomUUID());
            list.add(a);
        }

        System.out.println(list);
    }

}
