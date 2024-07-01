package com.convenient.tool.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarUtils {

    public static List<String> generateDaysForMonth(Calendar calendar) {
        List<String> days = new ArrayList<>();

        // 设置日历到该月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // 获取该月的总天数
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // 计算前面需要填充的空白天数（用于对齐周几）
        for (int i = 0; i < firstDayOfWeek; i++) {
            days.add("");
        }

        // 填充该月的日期
        for (int i = 1; i <= maxDay; i++) {
            days.add(String.valueOf(i));
        }

        return days;
    }
}
