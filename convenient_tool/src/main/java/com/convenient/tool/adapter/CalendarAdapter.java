package com.convenient.tool.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.convenient.tool.R;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    private List<String> days;
    private int selectedPosition = -1;
    private Calendar calendar;
    private Map<String, String> markedDays;

    public CalendarAdapter(List<String> days, Calendar calendar, Map<String, String> markedDays) {
        this.days = days;
        this.calendar = calendar;
        this.markedDays = markedDays;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_day_item, parent, false);
        return new CalendarViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull final CalendarViewHolder holder, final int position) {
        String day = days.get(position);
        holder.dayText.setText(day);

        if (!day.isEmpty()) {
            holder.dayText.setTextColor(Color.BLACK);
            holder.dayText.setBackground(null);

            Calendar today = Calendar.getInstance();
            if (today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                    today.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                    Integer.parseInt(day) == today.get(Calendar.DAY_OF_MONTH)) {
                holder.dayText.setTextColor(Color.RED);
            }

            if (selectedPosition == position) {
                holder.dayText.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.selected_day_background));
                holder.dayText.setTextColor(Color.WHITE);
            }

            String month = String.format("%02d", calendar.get(Calendar.MONTH) + 1); // 月份格式化为两位数
            String dayWithLeadingZero = String.format("%02d", Integer.parseInt(day)); // 日期格式化为两位数

            String dateKey = calendar.get(Calendar.YEAR) + "-" + month + "-" + dayWithLeadingZero;

            if (markedDays.containsKey(dateKey)) {
                holder.redDot.setVisibility(View.VISIBLE);
                GradientDrawable drawable = (GradientDrawable) holder.redDot.getBackground();
                drawable.setColor(Color.parseColor(markedDays.get(dateKey))); // 设置自定义颜色
            } else {
                holder.redDot.setVisibility(View.GONE);
            }

            holder.dayText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPosition != position) {
                        notifyItemChanged(selectedPosition);
                        selectedPosition = position;
                        notifyItemChanged(selectedPosition);
                    }
                }
            });
        } else {
            holder.dayText.setTextColor(Color.TRANSPARENT);
            holder.redDot.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public static class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView dayText;
        View redDot;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            dayText = itemView.findViewById(R.id.dayText);
            redDot = itemView.findViewById(R.id.redDot);
        }
    }
}
