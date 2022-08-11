package com.example.smokingarealist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarFragment extends Fragment {

    public CalendarView calendarView;
    ListView listView;
    public ImageButton add_btn;
    private static CountListViewAdapter countListViewAdapter;
    ArrayList<CountListViewItem> countListViewItemArrayList;

    Context ct;

    Date now = new Date();

    DateFormat format = DateFormat.getDateInstance(DateFormat.FULL);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);
        ct = container.getContext();

        calendarView = v.findViewById(R.id.calendar);

        ListView listView = (ListView)v.findViewById(R.id.smoking_data);
        countListViewItemArrayList = new ArrayList<CountListViewItem>();
        countListViewAdapter = new CountListViewAdapter(getContext(),countListViewItemArrayList);
        listView.setAdapter(countListViewAdapter);

        add_btn = (ImageButton) v.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count;
                count = countListViewAdapter.getCount();

                countListViewItemArrayList.add(new CountListViewItem(new Date(System.currentTimeMillis()),count));

                countListViewAdapter.notifyDataSetChanged();
            }
        });


        return v;
    }

}

