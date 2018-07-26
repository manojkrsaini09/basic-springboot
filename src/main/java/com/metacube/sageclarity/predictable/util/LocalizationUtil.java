package com.metacube.sageclarity.predictable.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LocalizationUtil {
    private static final String DATE_WITH_TIME = "yyyy-mm-dd'T'hh:mm";
    private static final String DATE = "yyyy-MM-dd";
    private static final String YEAR_FORMAT = "yy";
    private static final String MONTH_YEAR_FORMAT = "MMM-yy";

    public static final Date getTodaysDate(){
        return Calendar.getInstance().getTime();
    }
    public static final String getFormattedDate(Date date) {
        if (date == null || date.toString().isEmpty()) return null;

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE);
        return dateFormat.format(date);
    }

    public static final String getFormattedDateWithTime(Date date) {
        if (date == null || date.toString().isEmpty()) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_WITH_TIME);
        return dateFormat.format(date);
    }

    public static final Date stringToDate(String string) throws ParseException {
        if (string == null || string.isEmpty()) return null;

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE);
        return dateFormat.parse(string);
    }

    public static final Date stringtoDateWithTime(String string) throws ParseException {
        if (string == null || string.isEmpty())
            return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_WITH_TIME);
        return dateFormat.parse(string);
    }

    public static final Date setEodTime(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        return calendar.getTime();
    }

    public static final Date setEodTime(Calendar calendar){
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        return calendar.getTime();
    }

    public static final Date getDateWithStartingTime(Date date) throws ParseException{
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }
    public static final Date getWeekStartingDate(Date date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek= calendar.get(Calendar.DAY_OF_WEEK);
        Integer amount=dayOfWeek-Calendar.SUNDAY;
        calendar.add(Calendar.DATE,-amount+1);
        return dateFormat.parse(dateFormat.format( calendar.getTime()));
    }

    public static final Date getMonthStartingDate(Date date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int dayOfMonth= calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE,-dayOfMonth+1);
        return dateFormat.parse(dateFormat.format(calendar.getTime()));
    }

    public static final Date getDateFromCalendarInstance(Date date) throws ParseException{
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }
    //format= MMM-yy
    public static final String getMonthYearFormattedString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MONTH_YEAR_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static final Date getMonthEndDate(Date date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        int dayOfMonth=  calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE,dayOfMonth);
        return dateFormat.parse(dateFormat.format(calendar.getTime()));
    }

    public static final Date getDateBeforSomeDaysFromToday(Integer days) throws ParseException {
        Date todayDate =LocalizationUtil.getTodaysDate();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(todayDate);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.add(Calendar.DATE,-days);
        return calendar.getTime();
    }

    public static final Date getDateAfterSomeDaysFromToday(Integer days) throws ParseException {
        Date todayDate =LocalizationUtil.getTodaysDate();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(todayDate);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.add(Calendar.DATE,days);
        return calendar.getTime();
    }
}
