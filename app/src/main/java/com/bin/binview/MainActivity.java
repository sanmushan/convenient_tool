package com.bin.binview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bin.binview.activity.CalendarActivity;
import com.convenient.tool.adapter.recyclerview.CommonAdapter;
import com.convenient.tool.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private CommonAdapter<String> mAdapter;
    List<String> mList = new ArrayList<>();
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mList.clear();
        mList.add("日历");
        showRv();


    }

    private void showRv() {
        LinearLayoutManager tabManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(tabManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new CommonAdapter<String>(this, R.layout.item_home, mList) {
            @Override
            protected void convert(ViewHolder holder, String bean, int position) {

                holder.setText(R.id.tv_name, "点击进入======》"+bean);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == 0) {
                            startActivity(new Intent(mContext, CalendarActivity.class));
                        }

                    }
                });

            }
        };
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}