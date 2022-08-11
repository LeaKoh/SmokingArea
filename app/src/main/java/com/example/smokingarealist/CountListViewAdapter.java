package com.example.smokingarealist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CountListViewAdapter extends BaseAdapter {
    Context context;
    public ArrayList<CountListViewItem> countListViewItemArrayList;


    public CountListViewAdapter(Context context, ArrayList<CountListViewItem> countListViewItemArrayList) {
        this.context = context;
        this.countListViewItemArrayList = countListViewItemArrayList;
    }


    @Override
    public int getCount() {return countListViewItemArrayList.size();}

    @Override
    public Object getItem(int position) {
        return countListViewItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    TextView date_textView;
    TextView count_textView;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.count_listview_item, parent, false);
            date_textView = (TextView) convertView.findViewById(R.id.date_textview);
            count_textView = (TextView) convertView.findViewById(R.id.count_textview);
        }


        long now = System.currentTimeMillis();

        Date date = new Date(now);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM월 dd일 hh:mm a");

        date_textView.setText(dateFormat.format(date).toString());
        count_textView.setText(Integer.toString(countListViewItemArrayList.size()));

        return convertView;
    }

}


