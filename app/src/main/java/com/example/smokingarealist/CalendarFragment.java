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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarFragment extends Fragment {

    public CalendarView calendarView;
    ListView listView;
    public ImageButton add_btn;
    public CountListViewAdapter countListViewAdapter = null;
    ArrayList<CountListViewItem> countListViewItemArrayList;

    Context ct;

    DateFormat format = DateFormat.getDateInstance(DateFormat.FULL);
    String fileName;

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
                count = countListViewAdapter.getCount()+1;

                countListViewItemArrayList.add(new CountListViewItem(new Date(System.currentTimeMillis()),count));
/*
                String jsonArray = "[";
                for (int index = 0; index < countListViewItemArrayList.size(); index++) {
                    CountListViewItem countListViewItem = countListViewItemArrayList.get(index);
                    if (index > 0) {
                        jsonArray += "," + jsonArray;
                    }
                    jsonArray += jsonArray + countListViewItem.getString();
                }
                jsonArray += "]";
*/
                JsonArray jsonArray = new JsonArray();
                for (int index = 0; index < countListViewItemArrayList.size(); index++) {
                    CountListViewItem countListViewItem = countListViewItemArrayList.get(index);
                    JsonObject jsonObject = new JsonObject();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");

                    jsonObject.addProperty("date", formatter.format(countListViewItem.getWrite_date()));
                    jsonObject.addProperty("count", countListViewItem.getSmoke_count().toString());
                    jsonArray.add(jsonObject);
                }
                jsonArray.toString();

                JsonParser jsonParser = new JsonParser();
                JsonArray parseArray =  jsonParser.parse(jsonArray.toString()).getAsJsonArray();
                for (int index = 0; index < parseArray.size(); index++) {
                    JsonObject jsonObject = parseArray.get(index).getAsJsonObject();

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");

                    try {
                        countListViewItemArrayList.add(new CountListViewItem(formatter.parse(jsonObject.get("date").getAsString()),
                                Integer.parseInt(jsonObject.get("count").getAsString())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }


                countListViewAdapter.notifyDataSetChanged();

                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                    }
                });
            }
        });

        return v;
    }

}

