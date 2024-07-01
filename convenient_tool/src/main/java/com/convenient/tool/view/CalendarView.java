package com.convenient.tool.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.convenient.tool.R;
import com.convenient.tool.adapter.CalendarAdapter;
import com.convenient.tool.utils.CalendarUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 日历View
 */
public class CalendarView extends LinearLayout {


    private TextView tvDate, tvCancel, tvConfirm;
    private ImageButton btnPrevious, btnNext, btnRefresh;
    private RecyclerView calendarRecyclerView;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private CalendarAdapter calendarAdapter;
    private Map<String, String> markedDays; // 使用Map<String, String>来存储标识日期和颜色



    private OnClickListener onClickListener;

    private OnCancelClickListener onCancelClickListener;
    private OnConfirmClickListener onConfirmClickListener;

    public CalendarView(Context context) {
        super(context);
        init(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_calendar, this, true);

        tvDate = findViewById(R.id.tvDate);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        btnRefresh = findViewById(R.id.btnRefresh);
        tvCancel = findViewById(R.id.tvCancel);
        tvConfirm = findViewById(R.id.tvConfirm);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        markedDays = new HashMap<>();

        updateDateDisplay();

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                updateDateDisplay();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                updateDateDisplay();
            }
        });

        btnRefresh.setOnClickListener(v -> refreshCalendar());
        tvCancel.setOnClickListener(v -> onCancelClicked());
        tvConfirm.setOnClickListener(v -> onConfirmClicked());

        calendarRecyclerView.setLayoutManager(new GridLayoutManager(context, 7));
        calendarAdapter = new CalendarAdapter(CalendarUtils.generateDaysForMonth(calendar), calendar, markedDays);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private void updateDateDisplay() {
        String date = dateFormat.format(calendar.getTime());
        tvDate.setText(date);
        calendarAdapter = new CalendarAdapter(CalendarUtils.generateDaysForMonth(calendar), calendar, markedDays);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private List<String> generateDaysForMonth() {
        List<String> days = new ArrayList<>();
        Calendar tempCalendar = (Calendar) calendar.clone();
        tempCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        int daysInMonth = tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < firstDayOfWeek; i++) {
            days.add("");
        }

        for (int i = 1; i <= daysInMonth; i++) {
            days.add(String.valueOf(i));
        }

        return days;
    }

    public void setMarkedDays(Map<String, String> markedDays) {
        this.markedDays = markedDays;
        updateDateDisplay();
    }

    private void refreshCalendar() {

    }

    private void onCancelClicked() {
        if (onCancelClickListener != null) {
            onCancelClickListener.onCancelClicked();
        }
        Log.w("tool", "取消按钮点击");
    }

    private void onConfirmClicked() {
        if (onConfirmClickListener != null) {
            onConfirmClickListener.onConfirmClicked();
        }
        Log.w("tool", "确认按钮点击");
    }
    public interface OnConfirmClickListener {
        void onConfirmClicked();
    }
    public interface OnCancelClickListener {
        void onCancelClicked();
    }

    public interface OnClickListener {
        void onCancelClicked();
        void onConfirmClicked();
    }

    public void setOnClickListener(OnClickListener clickListener) {
        this.onClickListener = clickListener;
    }
}
