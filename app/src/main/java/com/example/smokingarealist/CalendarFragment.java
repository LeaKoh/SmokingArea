package com.example.smokingarealist;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smokingarealist.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.prefs.BackingStoreException;

public class CalendarFragment extends Fragment {


    int smoke_count = 0;
    int i = 0;
    int Month;
    int Day;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        int Month_List[] = {};
        int Day_List[] = {};
        int Count_List[] = {};
        String Time_List[] = {};

        SimpleDateFormat sdf = new SimpleDateFormat("HH : mm a");
        String Time = sdf.format(System.currentTimeMillis());

        ListView listView = (ListView) v.findViewById(R.id.smoking_data);
        SmokingAdapter adapter = new SmokingAdapter();


        for (int l = 0; l < i; l++) {
            adapter.addItem(new SmokingData(Month_List[l], Day_List[l], Time_List[l], Count_List[l]));
        }

        listView.setAdapter(adapter);


        CalendarView calendarView = (CalendarView) v.findViewById(R.id.calendar);
        ImageButton imageButton = (ImageButton) v.findViewById(R.id.add);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smoke_count++;
                Month_List[i] = Month;
                Day_List[i] = Day;
                Count_List[i] = smoke_count;
                Time_List[i] = Time;
                i++;
            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Month = month;
                Day = dayOfMonth;
            }
        });

        return v;
    }
}

class SmokingAdapter extends BaseAdapter {

    ArrayList<SmokingData> items = new ArrayList<SmokingData>();

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(int position) {
        return items.get(position);
    }



}

