package com.wondersgroup.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.alibaba.druid.util.StringUtils;


/**
 * 提供一些常用的日期计算方法
 * 
 * @author wanglei
 */
public class TimeOrDateUtils {
	/**
	 * flag before
	 */
	public static final transient int BEFORE = 1;
	/**
	 * flag after
	 */
	public static final transient int AFTER = 2;
	/**
	 * flag equal
	 */
	public static final transient int EQUAL = 3;
	public static final String FULL_FROMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String FULL_YMDHM = "yyyy-MM-dd HH:mm";
	public static final String FULL_YMD = "yyyy-MM-dd";
	public static final String YMD = "yyyyMMdd";

	/**
	 * 比较2个日期型相差的天数，会计算时分秒
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getIntervalDays(Date startDate, Date endDate) {
		if (null == startDate || null == endDate) {
			return -1;
		}
		long intervalMilli = endDate.getTime() - startDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}

	public static String responseTimeConvert(int responseTime) {
		String rt = "";
		if (responseTime < 24) {
			rt = responseTime + " hours";
		} else {
			int days = 0;
			if (responseTime % 24 == 0) {
				days = responseTime / 24;
				rt = days + " days";
			} else {
				days = responseTime / 24;
				int t = responseTime - days * 24;
				rt = days + " days " + t + " hours";
			}
		}
		return rt;
	}

	/*
	 * return current date of String
	 */
	public static String getCurrentDste(String pattern) {
		return (new SimpleDateFormat(pattern)).format(Calendar.getInstance().getTime());
	}

	/*
	 * return System time
	 */
	public static String getSystemTime(String pattern) {
		return formateDate(new Date(System.currentTimeMillis()), pattern);
	}

	/*
	 * return String date set date objec and pattern of format String
	 */
	public static String formateDate(Date date, String pattern) {
		if (StringUtils.isEmpty(pattern)) {
			pattern = TimeOrDateUtils.FULL_FROMAT;
		}
		SimpleDateFormat sformat = new SimpleDateFormat(pattern);
		if (date != null) {
			return sformat.format(date);
		}
		return "";
	}

	/*
	 * return String date set date objec and pattern of format String
	 */
	public static String formateDate(Date date) {
		if (date == null) {
			return "";
		}
		return formateDate(date, null);
	}

	/**
	 * covnert String to java.util.Date
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String dateStr, String pattern) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}

	/**
	 * covnert String to java.util.Date, default format yyyy-MM-dd
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(FULL_YMD);
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}

	public static Date getLater(Date first, Date second) {
		int flag = compareTwoDate(first, second);
		if (flag == AFTER) {
			return first;
		} else {
			return second;
		}
	}

	public static int compareTwoDate(Date first, Date second) {
		if ((first == null) && (second == null)) {
			return EQUAL;
		} else if (first == null) {
			return BEFORE;
		} else if (second == null) {
			return AFTER;
		} else if (first.before(second)) {
			return BEFORE;
		} else if (first.after(second)) {
			return AFTER;
		} else {
			return EQUAL;
		}
	}

	public static int compareTwoDate(String first, String second, String pattern) {
		if ((first == null) && (second == null)) {
			return EQUAL;
		} else if (first == null) {
			return BEFORE;
		} else if (second == null) {
			return AFTER;
		}
		Date firstDate = parseDate(first, pattern);
		Date secondDate = parseDate(second, pattern);
		if (firstDate.before(secondDate)) {
			return BEFORE;
		} else if (firstDate.after(secondDate)) {
			return AFTER;
		} else {
			return EQUAL;
		}
	}

	public static Calendar getStartLastDate(int year, int month, int date) {
		Calendar cl = Calendar.getInstance();
		cl.set(year, month, date);
		return cl;
	}

	public static boolean isDateBetween(Date date, Date begin, Date end) {
		int c1 = compareTwoDate(begin, date);
		int c2 = compareTwoDate(date, end);
		return (((c1 == BEFORE) && (c2 == BEFORE)) || (c1 == EQUAL) || (c2 == EQUAL));
	}

	public static int getCurYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	public static int getCurMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH);
	}

	public static int getCurDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getCurHour() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int getCurMinute() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MINUTE);
	}

	public static int getCurSecond() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.SECOND);
	}

	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	public static Calendar getCalendarByDate() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static Calendar getCalendar(int year, int month, int date, int hourOfDay, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date, hourOfDay, minute, second);
		return calendar;
	}

	public static Calendar getCalendarDate(String dateString) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] sArr = dateString.split("-");
		int year = Integer.parseInt(sArr[0]);
		int month = Integer.parseInt(sArr[1]);
		int day = Integer.parseInt(sArr[2]);
		return new GregorianCalendar(year - 1, month - 1, day);
	}

	public static String[] getMonthRegionStrings() {
		String[] months = new String[12];
		GregorianCalendar g = new GregorianCalendar();
		int m = g.get(Calendar.MONTH) + 1;
		int y = g.get(Calendar.YEAR);
		for (int i = 0; i < 12; i++) {
			if (m < 1) {
				y -= 1;
				m = 12;
			}
			String dateStr = y + "-";
			if (m < 10) {
				dateStr += "0" + m;
			} else {
				dateStr += m;
			}
			String startDate = dateStr + "-01";
			int maxDayNum = TimeOrDateUtils.getCalendarDate(startDate).getActualMaximum(Calendar.DAY_OF_MONTH);
			String endDate = dateStr + "-" + maxDayNum;
			// System.out.println("[startDate=" + startDate +
			// ",endDate="+endDate+"]");
			months[i] = startDate + "," + endDate;
			m--;
		}
		return months;
	}

	public static String[] getRegionStringsForCurrentMonth() {
		String[] strs = new String[2];
		GregorianCalendar g = new GregorianCalendar();
		int days = g.getActualMaximum(Calendar.DAY_OF_MONTH);
		// fetch string of current month, e.g "2011-05"
		strs[0] = TimeOrDateUtils.getCurrentDste("yyyy-MM") + "-01";
		strs[1] = TimeOrDateUtils.getCurrentDste("yyyy-MM") + "-" + days;
		return strs;
	}

	public static int getDayForMonth(String format, String sdate) {
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		try {
			Date date = sformat.parse(sdate);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			return 0;
		}
	}

	public static String getCurrentMonth(String format, String sdate, int flag) {
		int days = getDayForMonth(format, sdate);
		if (flag == 1) {
			// return getCurYear() + "-" +(getCurMonth()+1) +"-" +"1" + " " +
			// getCurHour() + ":" + getCurMinute() + ":" + getCurSecond();
			return getCurYear() + "-" + (getCurMonth() + 1) + "-" + "1" + " " + "00:00:00";
		} else {
			return getCurYear() + "-" + (getCurMonth() + 1) + "-" + days + " " + "23:59:59";
		}
	}

	public static String getPreMonth(String format, String sdate, int flag) {
		int days = getDayForMonth(format, sdate);
		if (flag == 1) {
			return getCurYear() + "-" + (getCurMonth()) + "-" + "1" + " " + getCurHour() + ":" + getCurMinute() + ":" + getCurSecond();
		} else {
			return getCurYear() + "-" + (getCurMonth()) + "-" + days + " " + getCurHour() + ":" + getCurMinute() + ":" + getCurSecond();
		}
	}

	public static String getMonthByInterval(String format, int Interval) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, Interval);
		return sdf.format(calendar.getTime());
	}

	public static Date getMonthDateByInterval(String format, int Interval) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, Interval);
		return calendar.getTime();
	}

	public static String getDayByInterval(Date format, int Interval) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, Interval);
		SimpleDateFormat sfmt = new SimpleDateFormat(FULL_FROMAT);
		return sfmt.format(calendar.getTime());
	}

	public static long getDayTimeByInterval(Date format, int Interval) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, Interval);
		return calendar.getTimeInMillis();
	}

	public static void main(String[] args) {
		// System.out.println(getDayByInterval(new
		// Date(System.currentTimeMillis()),1));
		// Date[] dates = TimeOrDateUtils.getMonthRange(new Date());
		// System.out.println( TimeOrDateUtils.parseDate(dates[0], pattern );
		// System.out.println(dates[1]);
		// 当前时间
		// Date []ddStrings= getDayRange(new Date());
		// System.out.println(ddStrings[0]+":"+ddStrings[1]);
		// System.out.println(getMonthLast("yyyy-MM-dd", 1));
		// System.out.println(getOneMonthBeforeCurrentTime(FULL_YMD));
		String dd = getSpecifiedDayAfter("2015-11-30", FULL_YMD);
		System.out.print(dd);

	}

	public static String getMonthFirst(String format, int Interval) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.MONTH, Interval);
		return sdf.format(calendar.getTime());
	}

	public static String getMonthLast(String format, int Interval) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, Interval);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return sdf.format(calendar.getTime());
	}

	public static String getEnglishForMonth(int Interval) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM", Locale.ENGLISH);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, Interval);
		return sdf.format(calendar.getTime());
	}

	public static String[] isCurrentMonthForDashboardSearch(String startDate, String endDate) {
		String[] dates = new String[2];
		if (!StringUtils.isEmpty(startDate)) {
			dates[0] = startDate;
		} else {
			dates[0] = TimeOrDateUtils.getRegionStringsForCurrentMonth()[0];
		}
		if (!StringUtils.isEmpty(endDate)) {
			dates[1] = endDate;
		} else {
			dates[1] = TimeOrDateUtils.getRegionStringsForCurrentMonth()[1];
		}
		return dates;
	}

	public static Date getCalendarToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FULL_FROMAT);
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/*
	 * 根据传入日期，返回日期所在月份的第一天和最后一天
	 */
	public static Date[] getMonthRange(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date start = c.getTime();
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.SECOND, -1);
		Date end = c.getTime();
		return new Date[] { start, end };
	}

	/**
	 * 获得某一个日期的开始时间点和结束时间点
	 * 
	 * @param date
	 * @return
	 */
	public static Date[] getDayRange(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date start = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		calendar.add(Calendar.SECOND, -1);
		Date end = calendar.getTime();
		return new Date[] { start, end };
	}

	// 获得当前时间一个月前的时间
	public static Date getOneMonthBeforeCurrentTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);

		return cal.getTime();
	}
	// 获得当前时间一个月后的时间
	public static Date getOneMonthAfterCurrentTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);

		return cal.getTime();
	}

	/**
	 * 获得某一个日期的开始时间点
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayBegin(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date start = calendar.getTime();
		return start;
	}

	/**
	 * 获得某一个日期的结束时间点
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayEnd(Date date) {
		if(null==date){
			return null;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		calendar.add(Calendar.SECOND, -1);
		Date end = calendar.getTime();
		return end;
	}

	public static Date getDateByOffsetDays(Date target, int offsetDays) {
		Calendar c = new GregorianCalendar();
		c.setTime(target);
		c.add(Calendar.DAY_OF_YEAR, offsetDays);
		Date newDate = c.getTime();
		return newDate;
	}

	public static Date getDateByOffsetHours(Date target, int offsetHours) {
		Calendar c = new GregorianCalendar();
		c.setTime(target);
		c.add(Calendar.HOUR_OF_DAY, offsetHours);
		Date newDate = c.getTime();
		return newDate;
	}

	public static String getMysqlDateFormatFieldString(String fieldString, String pattern) {
		String resultTemplate = " str_to_date(DATE_FORMAT(_fieldString_, _pattern_),_pattern_) ";
		if (StringUtils.isEmpty(pattern)) {
			pattern = "'%Y-%m-%d'";
		}
		String resultString = resultTemplate.replace("_fieldString_", fieldString).replaceAll("_pattern_", pattern);
		return resultString;
	}


	public static long getTotalDays(long time) {
		return TimeUnit.MILLISECONDS.toDays(time);
	}

	public static long getTotalHours(long time) {
		return TimeUnit.MILLISECONDS.toHours(time);
	}

	public static long getTotalMinutes(long time) {
		return TimeUnit.MILLISECONDS.toMinutes(time);
	}

	public static long getTotalSeconds(long time) {
		return TimeUnit.MILLISECONDS.toSeconds(time);
	}

	public static long getTotalDays(Date date) {
		return getTotalDays(date.getTime());
	}

	public static long getTotalHours(Date date) {
		return getTotalHours(date.getTime());
	}

	public static long getTotalMinute(Date date) {
		return getTotalMinutes(date.getTime());
	}

	public static long getTotalSecond(Date date) {
		return getTotalSeconds(date.getTime());
	}

	/**
	 * 判断提供的时间点(年月日时分秒)是否在提供的闭区间时间区域(只有时分秒)内
	 * 
	 * @param time
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean checkDateBetweenTime(Date time, Date beginTime, Date endTime) {
		if (time == null && (beginTime != null || endTime != null)) {
			return false;
		} else if (time != null && (beginTime == null && endTime == null)) {
			return false;
		} else if (time == null && (beginTime == null && endTime == null)) {
			return false;
		}
		Calendar t = Calendar.getInstance();
		t.setTime(time);
		Date b = beginTime;
		Date e = endTime;
		if (beginTime != null && endTime != null && endTime.before(beginTime)) {
			b = endTime;
			e = beginTime;
		}
		if (b != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(b);
			calendar.set(Calendar.YEAR, t.get(Calendar.YEAR));
			calendar.set(Calendar.MONTH, t.get(Calendar.MONTH));
			calendar.set(Calendar.DAY_OF_YEAR, t.get(Calendar.DAY_OF_YEAR));
			int value = calendar.getTime().compareTo(time);
			if (value == 0) {
				return true;
			} else if (value > 0) {
				return false;
			}
		}
		if (e != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(e);
			calendar.set(Calendar.YEAR, t.get(Calendar.YEAR));
			calendar.set(Calendar.MONTH, t.get(Calendar.MONTH));
			calendar.set(Calendar.DAY_OF_YEAR, t.get(Calendar.DAY_OF_YEAR));
			int value = calendar.getTime().compareTo(time);
			if (value == 0) {
				return true;
			} else if (value < 0) {
				return false;
			}
		}
		return true;
	}

	public static Boolean isExpired(Date date, Date expireDate) {
		if (date == null || expireDate == null) {
			return null;
		}
		return expireDate.compareTo(date) < 0;
	}

	/**
	 * 获取两个指定时间之间差了多少个小时
	 * 
	 * @param sysNow
	 * @param time
	 * @return
	 */
	public long getDurationByHours(long sysNow, long time) {
		long duration = Math.abs(time - sysNow);
		long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
		return diffInHours;
	}

	/**
	 * 获得指定日期的前一天
	 * 
	 * @param specifiedDay
	 * @return
	 * @throws Exception
	 */
	public static String getSpecifiedDayBefore(String specifiedDay, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = sdf.format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = sdf.format(c.getTime());
		return dayAfter;
	}
}
