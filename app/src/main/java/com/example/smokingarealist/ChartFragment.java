package com.example.smokingarealist;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;

public class ChartFragment extends Fragment {

    SmokingData s = new SmokingData();
    ArrayList<Integer> jsonList = new ArrayList<>();
    ArrayList<String> labelList = new ArrayList<>();
    BarChart barChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chart, container, false);

        barChart = (BarChart) v.findViewById(R.id.fragment_bluetooth_chat_barchart);
        graphInitSetting();

        BarChartGraph(labelList, jsonList);
        barChart.setTouchEnabled(false);
        barChart.getAxisRight().setAxisMaxValue(30);
        barChart.getAxisLeft().setAxisMaxValue(30);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.isGridDashedLineEnabled(false);
        return v;
    }

    public void graphInitSetting() {

        int i, l;

        for (l=1; l<30; l++) {
            labelList.add(l+"일");
        }

        jsonList.add(1);
        jsonList.add(12);
        jsonList.add(51);


        BarChartGraph(labelList, jsonList);
        barChart.setTouchEnabled(false);
        barChart.setMaxVisibleValueCount(50);
        barChart.setTop(0);
        barChart.setBottom(50);
        barChart.setAutoScaleMinMaxEnabled(true);
        //barChart.getAxisRight().setAxisMaxValue();
        //barChart.getAxisLeft().setAxisMaxValue(80);
    }

    private void BarChartGraph(ArrayList<String> labelList, ArrayList<Integer> valList) {


        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < valList.size(); i++) {
            entries.add(new BarEntry((Integer) valList.get(i), i));
        }

        BarDataSet depenses = new BarDataSet(entries, "흡연량");
        depenses.setAxisDependency(YAxis.AxisDependency.LEFT);
        barChart.setDescription(" ");

        ArrayList<String> labels = new ArrayList<String>();

        for (int i = 0; i < labelList.size(); i++) {
            labels.add((String) labelList.get(i));
        }

        BarData data = new BarData(labels, depenses);
        depenses.setColor(Color.parseColor("#CCCCFF"));

        barChart.setData(data);
        barChart.setVisibleXRangeMaximum(10); //스크롤생ㅇ
        //barChart.animateXY(1000, 1000);
        barChart.invalidate();
    }

}

