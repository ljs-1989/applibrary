package com.liujs.library.utils;

import android.content.Context;

import com.liujs.library.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by liujs on 2016/10/14.
 * 邮箱：725459481@qq.com
 */

public class DataUtil {

    private static SimpleDateFormat format = null;

    private static long nd = 1000 * 24 * 60 * 60;

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime() {
        format = new SimpleDateFormat("YYYY-MM-dd HH.mm");
        return format.format(new Date());
    }

    /**
     * 根据时间格式将毫秒单位时间转成字符串格式
     *
     * @param pattern timeformat
     * @param longTime 时间（毫秒）
     * @return
     */
    public static String getFormatedTime(String pattern, long longTime) {
        format = new SimpleDateFormat(pattern);
        return format.format(new Date(longTime));
    }



    /**
     * �ַ�ת��������
     *
     * @param date �����ַ�
     * @param format ���ڸ�ʽ
     * @return
     */
    public static Date convert(String date, String format) {
        Date retValue = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            retValue = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * @author LuoB.
     * @param oldTime ��С��ʱ��
     * @param newTime �ϴ��ʱ�� (���Ϊ�� Ĭ�ϵ�ǰʱ�� ,��ʾ�͵�ǰʱ�����)
     * @return -1 ��ͬһ��. 0������ . 1 ��������ǰ��.
     * @throws ParseException ת���쳣
     */
    public static int isYeaterday(Date oldTime, Date newTime) {
        if (newTime == null) {
            newTime = new Date();
        }
        // ������� ���� yyyy-MM-dd 00��00��00 �������
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = format.format(newTime);
        Date today;
        try {
            today = format.parse(todayStr);
            // ���� 86400000=24*60*60*1000 һ��
            if ((today.getTime() - oldTime.getTime()) > 0
                    && (today.getTime() - oldTime.getTime()) <= 86400000) {
                return 0;
            } else if ((today.getTime() - oldTime.getTime()) <= 0) { // �����ǽ���
                return -1;
            } else { // ������ǰ��
                return 1;
            }
        } catch (ParseException e) {

        }
        return -1;
    }

    /**
     * 计算两个日期型的时间相差多少时间
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    public static String twoDateDistance(Context context, Date startDate, Date endDate) {

        if (startDate == null || endDate == null) {
            return null;
        }
        return twoDateDistance(context, startDate.getTime(), endDate.getTime());
    }


    public static String twoDateDistance(Context context, long startTime, long endTime) {
        if (isYeaterday(new Date(startTime), new Date(endTime)) == 0) {
            return context.getString(R.string.time_yesterday_ago);
        } else if (isYeaterday(new Date(startTime), new Date(endTime)) == 1) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String todayStr = format.format(startTime);
            try {
                Date today = format.parse(todayStr);
                startTime = today.getTime();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        long timeLong = endTime - startTime;
        timeLong = Math.max(timeLong, 0);
        if (timeLong < (60 * 1000)) {
            timeLong = timeLong/1000;
            return timeLong+context.getString(R.string.time_seconds_ago);
        }else if (timeLong < (60 * 60 * 1000)) {
            timeLong = timeLong / 1000 / 60;
            return timeLong + context.getString(R.string.time_minutes_ago);
        } else if (timeLong < (60 * 60 * 24 * 1000)) {
            timeLong = timeLong / 60 / 60 / 1000;
            return timeLong + context.getString(R.string.time_hours_ago);
        } else if (timeLong < (60 * 60 * 48 * 1000)) {
            return context.getString(R.string.time_yesterday_ago);
        } else {
            long date = timeLong / nd;
        if (date <= 30) {
                return date + context.getString(R.string.time_before_day);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
                return sdf.format(startTime);
            }
        }
    }

}
