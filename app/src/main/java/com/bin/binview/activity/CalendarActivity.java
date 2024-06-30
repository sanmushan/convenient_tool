package com.bin.binview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bin.binview.R;
import com.convenient.tool.view.CalendarView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 日历
 */
public class CalendarActivity extends AppCompatActivity {
    private CalendarView calendarView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = (CalendarView) findViewById(R.id.calendarView);

        // 模拟接口返回的数据
        Map<String, String> markedDays = new HashMap<>();
        markedDays.put("2024-06-05", "#FF0000"); // 红色
        markedDays.put("2024-06-10", "#00FF00"); // 绿色
        markedDays.put("2024-06-15", "#0000FF"); // 蓝色
        // 设置标识日期
        calendarView.setMarkedDays(markedDays);
    }
}