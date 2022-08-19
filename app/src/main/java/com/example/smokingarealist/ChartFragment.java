package com.example.smokingarealist;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class ChartFragment extends Fragment {

    SmokingData s = new SmokingData();
    ArrayList<Integer> jsonList = new ArrayList<>();
    ArrayList<String> labelList = new ArrayList<>();
    BarChart barChart;

    int today_smoking;
    int yesterday_smoking;
    int gap_smoking;
    int avg_smoking;

    Date now = new Date();
    DateFormat format = DateFormat.getDateInstance(DateFormat.FULL);

    GregorianCalendar today = new GregorianCalendar();
    int month = today.get ( today.MONTH ) + 1;
    int day = today.get ( today.DAY_OF_MONTH );


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chart, container, false);

        TextView today_smoke = (TextView) v.findViewById(R.id. today_smoke);
        TextView yesterday_smoke = (TextView) v.findViewById(R.id. yesterday_smoke);
        TextView gap_smoke = (TextView) v.findViewById(R.id. gap);
        TextView lastest_smoke = (TextView) v.findViewById(R.id. latest_smoking);
        TextView average_smoke = (TextView) v.findViewById(R.id. smoking_quantity);

        ImageView gap_down = (ImageView) v.findViewById(R.id. downward);
        ImageView gap_up = (ImageView) v.findViewById(R.id. upwward);
        ImageView gap_keep = (ImageView) v.findViewById(R.id. keep);

        ImageButton left = (ImageButton) v.findViewById(R.id. left_chart);
        ImageButton right = (ImageButton) v.findViewById(R.id. right_chart);


        barChart = (BarChart) v.findViewById(R.id.fragment_bluetooth_chat_barchart);
        graphInitSetting();

        CalendarFragment calendarFragment = new CalendarFragment();

        today_smoking = calendarFragment.getCount();
        Date date = calendarFragment.getNow();
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일\naa HH시 mm분");

        //yesterday = today - 1;
        gap_smoking = today_smoking - yesterday_smoking;


        today_smoke.setText(""+today_smoking+" 개");
        yesterday_smoke.setText(""+yesterday_smoking+" 개");
        gap_smoke.setText(""+gap_smoking);
        average_smoke.setText(""+avg_smoking+"개");
        lastest_smoke.setText(format.format(date).toString());



        if (gap_smoking < 0) {
            gap_down.setVisibility(View. VISIBLE);
            gap_up.setVisibility(View. INVISIBLE);
            gap_keep.setVisibility(View. INVISIBLE);
            gap_smoke.setText(""+gap_smoking*-1);
            gap_smoke.setTextColor(Color.parseColor("#58ACFA"));
        }
        else if (gap_smoking > 0) {
            gap_down.setVisibility(View. INVISIBLE);
            gap_up.setVisibility(View. VISIBLE);
            gap_keep.setVisibility(View. INVISIBLE);
            gap_smoke.setTextColor(Color.parseColor("#FF6F6F"));
        }
        else {
            gap_down.setVisibility(View. INVISIBLE);
            gap_up.setVisibility(View. INVISIBLE);
            gap_keep.setVisibility(View. VISIBLE);
            gap_smoke.setTextColor(Color.parseColor("#000000"));
        }


        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        BarChartGraph(labelList, jsonList);
        barChart.setTouchEnabled(false);
        //barChart.getAxisRight().setAxisMaxValue(30);
        //barChart.getAxisLeft().setAxisMaxValue(30);
        barChart.setVisibleXRangeMaximum(7);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getAxisLeft().disableGridDashedLine();
        barChart.getAxisRight().disableGridDashedLine();
        //xAxis.isGridDashedLineEnabled(false);
        return v;
    }

    public void graphInitSetting() {

        for (int i=1; i<=12; i++)
            for (int l=1; l<31; l++)
                labelList.add("" + i + "/" + l);

        jsonList.add(10);
        jsonList.add(15);
        jsonList.add(21);
        jsonList.add(15);
        jsonList.add(18);

        BarChartGraph(labelList, jsonList);
        //barChart.setTouchEnabled(false);
        barChart.setMaxVisibleValueCount(50);
        barChart.setTop(0);
        barChart.setBottom(50);
        barChart.setAutoScaleMinMaxEnabled(true);
        barChart.setDrawGridBackground(false);


        //barChart.getAxisRight().setAxisMaxValue();
        //barChart.getAxisLeft().setAxisMaxValue(80);
    }

    private void BarChartGraph(ArrayList<String> labelList, ArrayList<Integer> valList) {


        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < valList.size(); i++) {
            entries.add(new BarEntry((Integer) valList.get(i), i));
        }

        BarDataSet smoke = new BarDataSet(entries, "");
        smoke.setAxisDependency(YAxis.AxisDependency.LEFT);
        smoke.setBarSpacePercent(60);

        barChart.setDescription(" ");

        ArrayList<String> labels = new ArrayList<String>();

        for (int i = 0; i < labelList.size(); i++) {
            labels.add((String) labelList.get(i));
        }

        BarData data = new BarData(labels, smoke);
        smoke.setColor(Color.parseColor("#ECCEF5"));

        barChart.setData(data);
        //barChart.animateXY(1000, 1000);
        barChart.invalidate();
    }

}

