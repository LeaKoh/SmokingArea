package com.example.smokingarealist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarFragment extends Fragment {

    int smoke_count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        ListView listView = (ListView) v.findViewById(R.id.smoking_data);
        CalendarView calendarView = (CalendarView) v.findViewById(R.id.calendar);
        ImageButton imageButton = (ImageButton) v.findViewById(R.id.add);

        SmokingList adapter = new SmokingList();
        listView.setAdapter(adapter);


        Calendar cal = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("mm/dd HH:mm");
        String date = format.format(Calendar.getInstance().getTime());


        /**
         calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                month += 1;
            }
        });
         **/


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smoke_count++;
                adapter.addData(smoke_count, date);
                adapter.notifyDataSetChanged();
                /**int checked;
                int ct = adapter.getCount();
                if (ct >= 0) {
                    checked = listView.getCheckedItemPosition();
                    if (checked > -1 && checked < ct) {
                        adapter.addData(smoke_count, date);
                        adapter.notifyDataSetChanged()
                    }
                }**/
            }
        });

        return v;
    }


    class SmokingList extends BaseAdapter {

        ArrayList<SmokingData> smokingDataList = new ArrayList<SmokingData>();

        @Override
        public int getCount() {
            return smokingDataList.size();
        }

        @Override
        public Object getItem(int i) {
            return smokingDataList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Context c = parent.getContext();

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.smoking_date_item, parent, false);
            }

            TextView textView0 = (TextView) convertView.findViewById(R.id.count);
            TextView textView1 = (TextView) convertView.findViewById(R.id.smoke_date);

            SmokingData s = smokingDataList.get(position);

            textView0.setText(s.getCount());
            textView1.setText(s.getDate());


            return convertView;
        }

        public void addData(int count, String date) {
            SmokingData s = new SmokingData();

            s.setCount(count);
            s.setDate(date);

            smokingDataList.add(s);
        }
    }
}



