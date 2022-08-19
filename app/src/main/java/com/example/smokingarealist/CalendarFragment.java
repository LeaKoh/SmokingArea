package com.example.smokingarealist;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarFragment extends Fragment {

    ListView listView;
    public ImageButton add_btn;
    private static CountListViewAdapter countListViewAdapter;
    ArrayList<CountListViewItem> countListViewItemArrayList;
    public String fname = null;
    public String str = null;
    String selectMeetingDate = "";
    int count;





    Context ct;
    Date now = new Date();
    DateFormat format = DateFormat.getDateInstance(DateFormat.FULL);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        CalendarView calendarView = v.findViewById(R.id.calendar);
        ListView listView = (ListView)v.findViewById(R.id.smoking_data);

        countListViewItemArrayList = new ArrayList<CountListViewItem>();
        countListViewAdapter = new CountListViewAdapter(getContext(),countListViewItemArrayList);
        listView.setAdapter(countListViewAdapter);

        ct = container.getContext();
        add_btn = (ImageButton) v.findViewById(R.id.add_btn);


        /**for (i=1; i<13; i++) {
            if (i % 2 == 1) {
                for (j=1; j<32; j++) {
                   countListViewItemArrayList = new ArrayList<CountListViewItem>();
                }
            }


            else {
                if (i == 2)
                    for (j=1; j<28; j++) {
                        ArrayList<CountListViewItem> countListViewItemArrayList = new ArrayList<CountListViewItem>();
                    }

                else {
                    for (j=1; j<31; j++) {
                        ArrayList<CountListViewItem> countListViewItemArrayList = new ArrayList<CountListViewItem>();
                    }
                }
            }
        }**/



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {

                checkDay(year, month, dayOfMonth);

                Toast.makeText(getActivity(), year + "년 " + (month + 1) + "월 " + dayOfMonth + "일", Toast.LENGTH_SHORT).show();
                selectMeetingDate = year + "-" + (month + 1) + "-" + dayOfMonth;

                GregorianCalendar today = new GregorianCalendar();
                int now_year = today.get ( today.YEAR );
                int now_month = today.get ( today.MONTH ) + 1;
                int now_day = today.get ( today.DAY_OF_MONTH );

                if (year == now_year && (month+1) == now_month && dayOfMonth == now_day) {
                    add_btn.setVisibility(View. VISIBLE);
                }
                else {
                    add_btn.setVisibility(View. INVISIBLE);
                }

                add_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (year == now_year && (month+1) == now_month && dayOfMonth == now_day) {
                            saveSmoking(fname);

                            count = countListViewAdapter.getCount();
                            countListViewItemArrayList.add(new CountListViewItem(new Date(System.currentTimeMillis()), count));
                            countListViewAdapter.notifyDataSetChanged();
                        }
                    }
                });

                /**for (i=1; i<=12; i++) {
                    for (j=0; j<=31; j++) {
                        if ( i == month && j == dayOfMonth) {
                            FileInputStream fis = null;
                            try {
                                fis = ct.openFileInput(fname);

                                byte[] fileData = new byte[fis.available()];
                                fis.read(fileData);
                                fis.close();

                                str = new String();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }**/

            }
        });

        return v;
    }


    public void checkDay(int cYear, int cMonth, int cDay) {
        fname = ""+cYear+"-"+(cMonth+1)+""+"-"+cDay;
        FileInputStream fis = null;

        try {
            fis = getActivity().openFileInput(fname);

            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            str = new String(fileData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public void removeSmoking(String readDay) {
        FileOutputStream fos = null;

        try {
            fos= getActivity().openFileOutput(readDay, Context.MODE_NO_LOCALIZED_COLLATORS);
            String content = "";
            fos.write((content).getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public void saveSmoking (String readDay) {
        FileOutputStream fos = null;

        try {
            fos = getActivity().openFileOutput(readDay, Context.MODE_NO_LOCALIZED_COLLATORS);
            String content = "Hello";
            fos.write((content).getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        return count;
    }

    public Date getNow() {
        return now;
    }
}

