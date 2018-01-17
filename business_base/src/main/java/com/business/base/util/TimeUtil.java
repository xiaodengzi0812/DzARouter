package com.business.base.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {

    public static String getNowDate() {
        String date;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        date = formatter.format(curDate);

        return date;
    }

    public static String getNowDateCustom(String format) {
        String date;
        if (TextUtils.isEmpty(format)) return "";
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        date = formatter.format(curDate);

        return date;
    }

    /**
     * 获取当前时间yyyy-MM-dd HH:mm:ss SSS
     */
    public static String getNowTime() {  //年月
        String date;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.CHINA);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        date = formatter.format(curDate);

        return date;
    }

    /**
     * 毫秒转换"yyyy-MM-dd"
     *
     * @author lgy
     */
    public static String getTimeFromM(long millis) {  //年月
        if (millis == 0) {
            return "";
        }
        String date;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date curDate = new Date(millis);//获取当前时间
        date = formatter.format(curDate);

        return date;
    }

    /**
     * @author neusoft qinyuyang
     * @deprecated Use {@link #convertTimeFormat(String, long)}
     * 微秒转换"yyyy.MM.dd"
     */
    @Deprecated
    public static String getTime4FromM(long millis) {  //年月
        String date;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
        Date curDate = new Date(millis);//获取当前时间
        date = formatter.format(curDate);

        return date;
    }

    public static String convertTimeFormat(String format, long millis) {
        String date;

        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        Date curDate = new Date(millis);//获取当前时间
        date = formatter.format(curDate);

        return date;
    }

    /**
     * @author neusoft qinyuyang
     * @deprecated Use {@link #convertTimeFormat(String, long)}
     * 微秒转换"yyyy.MM.dd hh:mm:ss"
     */
    @Deprecated
    public static String getTime5FromM(long millis) {  //年月
        String date;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.CHINA);
        Date curDate = new Date(millis);//获取当前时间
        date = formatter.format(curDate);

        return date;
    }

    /**
     * @author neusoft shengfh
     * @deprecated Use {@link #convertTimeFormat(String, long)}
     * 微秒转换"yyyy.MM.dd hh:mm"
     */
    @Deprecated
    public static String getTime7FromM(long millis) {  //年月
        String date;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.CHINA);
        Date curDate = new Date(millis);
        date = formatter.format(curDate);

        return date;
    }

    /**
     * 微秒转换"yyyy年MM月dd HH:mm"
     *
     * @author lgy
     */
    public static String getTimeString2FromM(long millis) {  //年月
        String date;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        Date curDate = new Date(millis);//获取当前时间
        date = formatter.format(curDate);

        return date;
    }

    /**
     * 微秒转换"yyyy-MM-dd HH:mm:ss"
     *
     * @author lgy
     */
    public static String getTime2FromM2(long millis) {  //年月
        String date;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date curDate = new Date(millis);//获取当前时间
        date = formatter.format(curDate);

        return date;
    }

    /**
     * 微秒转换"yyyy-MM-dd HH:mm:ss"
     *
     * @author lgy
     */
    public static String getTimeFromM3(long millis) {  //年月
        String date;
        SimpleDateFormat todayFormatter = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss", Locale.CHINA);


        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();    //今天
        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));

        Date curDate = new Date(millis);//获取当前时间
        current.setTime(curDate);
        if (current.after(today)) {// 今天
            date = todayFormatter.format(curDate);
        } else {
            date = formatter.format(curDate);
        }
        return date;
    }

    /**
     * 日期转毫秒"yyyy年MM月dd"转long
     *
     * @author lgy
     */
    public static long date2Millis(String date) {
        if (TextUtils.isEmpty(date)) return 0;

        long millis = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd", Locale.CHINA);
        try {
            millis = formatter.parse(date).getTime();
        } catch (ParseException e) {
            //  Auto-generated catch block
            e.printStackTrace();
        }

        return millis;
    }

    /**
     * 日期转毫秒"yyyy年MM月dd"转long
     *
     * @author lgy
     */
    public static long date2MillisCustom(String date, String format) {
        if (TextUtils.isEmpty(date)) return 0;

        long millis = 0;
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        try {
            millis = formatter.parse(date).getTime();
        } catch (ParseException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }

        return millis;
    }

    /**
     * 时间转毫秒"HH:mm"转long
     *
     * @author lgy
     */
    public static long time2Millis(String date) {
        date = getNowDateCustom("yyyy年MM月dd日") + date;
        if (TextUtils.isEmpty(date)) return 0;

        long millis = 0;
        long mis = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm", Locale.CHINA);
        try {
            millis = formatter.parse(date).getTime();
        } catch (ParseException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }

        return millis;
    }

    /**
     * 当天显示HH:mm ，否则yyyy年MM月dd日
     *
     * @author lgy
     */
    public static String getReadableTime(long millis) {  //年月
        String date;
        String format;
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"), Locale.CHINA);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long t = calendar.getTimeInMillis();

        if (millis > t) {
            format = "HH:mm:ss";
        } else {
            format = "yyyy年MM月dd日 HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        Date curDate = new Date(millis);//获取当前时间
        date = formatter.format(curDate);

        return date;
    }

    /**
     * 获取当前时间和星期(yyyy-MM、dd、week)
     */
    public static String getDateAndWeek(long milliseconds) {

        String mYear;
        String mMonth;
        String mDay;
        String mHour;
        String mins;
        String mWeek;

        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(milliseconds);
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR)); // 年份  
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 月份  
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 日  
        mHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));// 小时
        mins = (c.get(Calendar.MINUTE) > 10) ? c.get(Calendar.MINUTE) + "" : "0" + c.get(Calendar.MINUTE) + "";// 分钟
        mWeek = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWeek)) {
            mWeek = "日";
        } else if ("2".equals(mWeek)) {
            mWeek = "一";
        } else if ("3".equals(mWeek)) {
            mWeek = "二";
        } else if ("4".equals(mWeek)) {
            mWeek = "三";
        } else if ("5".equals(mWeek)) {
            mWeek = "四";
        } else if ("6".equals(mWeek)) {
            mWeek = "五";
        } else if ("7".equals(mWeek)) {
            mWeek = "六";
        }

        String s = mYear + "年" + mMonth + "月" + ((Integer.parseInt(mDay) < 10) ? ("0" + mDay) : mDay) + "日"
                + " " + mHour + ":" + mins + "\n" + "星期" + mWeek;

        return s;
    }

    public static String seconds2String(long seconds) {
        long h = 0, m = 0, s = 0;

        if (seconds < 60) { //一分钟之内
            s = seconds;
        } else if (seconds < 1 * 60 * 60) { //一小时之内
            m = seconds / 60;
            s = seconds % 60;
        } else/*(seconds < 1 * 24 * 60 * 60)*/ { //一天之内
            h = seconds / (1 * 60 * 60);
            m = (seconds - h * 60 * 60) % 60;
            s = seconds % 60;
        }

        return (h > 0 ? (h + "小时") : "") + (m > 0 ? (m + "分钟") : "") + (s > 0 ? (s + "秒") : "");
    }

    /**
     * 是否打开网络同步时间
     */
    public static boolean isSNTP(Context context) {
        TimeZone tz = TimeZone.getDefault();
        String s = "TimeZone   " + tz.getDisplayName(false, 0, Locale.CHINA) + " Timezon id :: " + tz.getID();
        Log.i("TimeUtil", s);
        if (tz.getRawOffset() != 8 * 3600 * 1000) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 14) {
            if ((Settings.System.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME, 0) == 0)
                    || (Settings.System.getInt(context.getContentResolver(), "auto_time_zone", 0) == 0)) {
                return false;
            }
        } else {
            if (Settings.System.getInt(context.getContentResolver(), Settings.Global.AUTO_TIME, 0) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否弹即将收费提示
     */
    public static boolean isShowChargePrompt(int start, int end) {
        String nowTime = TimeUtil.getNowDateCustom("HH:mm");
        String time[] = nowTime.split(":");

        if (Integer.parseInt(time[0]) == end - 1 && Integer.parseInt(time[1]) >= 15) return true;
        else return false;
    }

    /**
     * 当前是否为免费时段
     */
    public static boolean isFreeTime(int start, int end) {
        int nowTime = Integer.parseInt(TimeUtil.getNowDateCustom("HH"));
        return (nowTime >= start && nowTime < end);
    }

    /**
     * 日期的加减
     */
    public static Date changeTime(int dex) {
        Format f = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date0);
        c.add(Calendar.DAY_OF_MONTH, dex);
        return c.getTime();
    }

}
