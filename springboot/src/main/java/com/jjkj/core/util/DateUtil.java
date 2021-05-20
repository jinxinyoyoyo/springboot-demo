package com.jjkj.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhoujiayuan on 2018/4/20.
 * 日期工具类
 */
public class DateUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());
    public static final String COMMON_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String COMMON_FORMAT_DAY = "yyyy-MM-dd";
    //查询日期形式(0.日 1.周 2.月)
    public static final int DATE_TYPE_DAY = 0;
    public static final int DATE_TYPE_WEEK = 1;
    public static final int DATE_TYPE_MONTH = 2;
    //查询开始时间或结束时间(0.开始时间 1.结束时间)
    public static final int START_TIME = 0;
    public static final int END_TIME = 1;

    /**
     * 字符串day 转换为 时间戳
     *
     * @param dateTime
     * @return
     */
    public static long getlongTime(String dateTime,String parseType) {
        SimpleDateFormat format = new SimpleDateFormat(parseType);
        //转化成日期格式
        try {
            return format.parse(dateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 时间戳 转换为 字符串
     *
     * @param longtime
     * @return yyyy-MM-dd
     */
    public static String getDay(long longtime,String parseType) {
        SimpleDateFormat format = new SimpleDateFormat(parseType);
        //转化成日期格式
        return format.format(longtime);
    }

    /**
     * 时间戳 转换为 指定日期
     *
     * @param longtime Long类型
     * @return fp 指定格式
     **/
    public static Date getDate(long longtime, String fp) {
        SimpleDateFormat format = new SimpleDateFormat(fp);
        //转化成日期格式
        String s = format.format(longtime);
        return getDate(s, fp);
    }

    //字符串转换日期
    public static Date getDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //日期转换字符串
    public static String getStrDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    //日期加减制定天数
    public static Date addDateDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        cal.getTime();
        return cal.getTime();
    }

    //日期加减制定分钟
    public static Date addDateMinute_Date(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        cal.getTime();
        return cal.getTime();
    }

    /**
     * 获取当前时间
     */
    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(COMMON_FORMAT_DATETIME);
        return sdf.format(new Date());
    }

    /**
     * 时间比较
     *
     * @param time1
     * @param time2
     * @param pattern 模式
     * @return
     */
    public static int compareTime(String time1, String time2, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            Date dt1 = df.parse(time1);
            Date dt2 = df.parse(time2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 判断两个日期是否是同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(String date1, String date2) {
        try {
            DateFormat df = new SimpleDateFormat(COMMON_FORMAT_DAY);
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (null == dt1 || null == dt2)
                return false;
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(dt1);
            cal1.set(Calendar.HOUR_OF_DAY, 0);
            cal1.set(Calendar.MINUTE, 0);
            cal1.set(Calendar.SECOND, 0);
            cal1.set(Calendar.MILLISECOND, 0);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(dt2);
            cal2.set(Calendar.HOUR_OF_DAY, 0);
            cal2.set(Calendar.MINUTE, 0);
            cal2.set(Calendar.SECOND, 0);
            cal2.set(Calendar.MILLISECOND, 0);
            return cal1.getTime().equals(cal2.getTime());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据传入日期返回当月/周第一天和最后一天
     *
     * @param timeType 开始时间或结束时间
     * @param dateType 日期形式(0.日 1.周 2.月)
     * @return
     */
    public static String getFirstOrLast(int year, int num, int timeType, int dateType) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(COMMON_FORMAT_DAY);

        if (dateType == DATE_TYPE_WEEK) {
            Calendar cal = getCalendarFormYear(year);
            cal.set(Calendar.WEEK_OF_YEAR, num);
            if (timeType == START_TIME) {
                return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                        cal.get(Calendar.DAY_OF_MONTH);
            } else {
                cal.add(Calendar.DAY_OF_WEEK, 6);
                return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                        cal.get(Calendar.DAY_OF_MONTH);
            }

        } else if (dateType == DATE_TYPE_MONTH) {
            //设置年份
            calendar.set(Calendar.YEAR, year);
            //设置月份
            calendar.set(Calendar.MONTH, num - 1);
            int day = 0;
            //获取某月最小天数
            if (timeType == START_TIME) {
                day = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
            } else {
                day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
            //设置日历中月份的最小天数
            calendar.set(Calendar.DAY_OF_MONTH, day);
            //格式化日期
            return format.format(calendar.getTime());
        }

        return null;
    }

    //日历转换
    public static Calendar getCalendarFormYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        return cal;
    }

    /**
     * 获取当天日期
     */
    public static String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat(COMMON_FORMAT_DAY);
        return format.format(new Date());
    }

    /**
     * 截取到日期
     */
    public static String getDay(String strDate, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        SimpleDateFormat format1 = new SimpleDateFormat(COMMON_FORMAT_DAY);
        String resultDate = "";
        try {
            //转化成date
            Date date = format.parse(strDate);
            //转化成日期格式
            resultDate = format1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return resultDate;
    }

    /**
     * @param strDate
     * @param pattern  传入模板
     * @param pattern1 返回模板
     * @return
     */
    public static Date getDay(String strDate, String pattern, String pattern1) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        SimpleDateFormat format1 = new SimpleDateFormat(pattern1);
        Date resultDate = null;
        try {
            //转化成date
            Date date = format.parse(strDate);
            //转化成日期格式
            String strDate1 = format1.format(date);
            resultDate = format1.parse(strDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return resultDate;
    }

    /**
     * 获取两个日期之间的所有日期（yyyy-MM-dd）
     *
     * @param begin
     * @param end
     * @return
     * @Description TODO
     * @author
     * @date
     */
    public static List<Date> getBetweenDates(Date begin, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(begin);
        while (begin.getTime() <= end.getTime()) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        }
        return result;
    }

    /**
     * 自动加1天
     *
     * @param c
     * @return
     * @author
     * @date
     */
    public static long autoAddDay(Calendar c) {
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
        return c.getTimeInMillis();
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) //闰年
                {
                    timeDistance += 366;
                } else //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    /**
     * 排班计算方法
     *
     * @param startL  开始时间戳
     * @param endL    结束时间戳
     * @param steps   主题顺序[12,12,24,36]
     * @param RuleId  主题 白12->晚12
     * @param ClassId 字典类别 白12 晚12
     * @return 排班结果集合
     **/
    public static List<Map<String, Object>> setRosterMeth(long startL, long endL, int[] steps, String RuleId, String[] ClassId) {
        //转换成小时
        int hours = Integer.parseInt(String.valueOf((endL - startL) / (1000 * 60 * 60)));
        System.out.println("---hours:" + hours);
        //一次排班耗时时间
        int one = 0;
        long[] l = new long[steps.length];//排班毫秒数组
        int s = 0;
        for (int step : steps) {
            one += step;
            l[s] = step * 60 * 60 * 1000;
            s++;
        }
        //排班循环次数
        long temp = 0;//开始时间戳
        List<Map<String, Object>> resultlist = new ArrayList<>();
        for (int i = 0; i < hours / one; i++) {
            long start = i == 0 ? startL : temp;
            Map<String, Object> rmap = creatClassList(start, l, RuleId, ClassId);
            temp = Long.parseLong(rmap.get("startL").toString());

            List<Map<String, Object>> listR = (List<Map<String, Object>>) rmap.get("listR");
            resultlist.addAll(listR);
        }
        //剩余时间 不以传入的截止时间为界线，存在剩余时间需要增加一轮排班，保证排班 完成
        if (hours / one > 1 && hours % one > 0) {
            int t = hours % one;
            int st = 0;
            for (int step : steps) {//主题顺序
                if (t > step) {
                    long wt = l[st];
                    Map<String, Object> outMap = new HashMap<>();
                    outMap.put("RuleId", RuleId);
                    outMap.put("ClassId", ClassId[st]);
                    outMap.put("CreatTime", new Date());
                    outMap.put("Id", UUIDUtil.getUUID());
                    outMap.put("CreatePersonId", "1");
                    outMap.put("WorkStart", getDate(temp, COMMON_FORMAT_DATETIME));
                    temp += wt;
                    outMap.put("WorkEnd", getDate(temp, COMMON_FORMAT_DATETIME));
                    resultlist.add(outMap);
                    t = t - step;
                    st++;
                }
            }
        }
        return resultlist;
    }

    /*****
     *
     * 排班数据重组*
     *
     *@return rmap
     * */
    public static Map<String, Object> creatClassList(long start, long[] l, String RuleId, String[] ClassId) {
        Map<String, Object> rMap = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        int i = 0;
        for (long wt : l) {
            Map<String, Object> outMap = new HashMap<>();
            outMap.put("RuleId", RuleId);
            outMap.put("ClassId", ClassId[i]);
            outMap.put("CreatTime", new Date());
            outMap.put("Id", UUIDUtil.getUUID());
            outMap.put("CreatePersonId", "1");
            outMap.put("WorkStart", getDate(start, COMMON_FORMAT_DATETIME));
            start += wt;
            outMap.put("WorkEnd", getDate(start, COMMON_FORMAT_DATETIME));
            list.add(outMap);
            i++;
        }
        rMap.put("startL", start);
        rMap.put("listR", list);
        return rMap;
    }

}
