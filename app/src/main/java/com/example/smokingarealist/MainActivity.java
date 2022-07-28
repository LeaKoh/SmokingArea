package com.example.smokingarealist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    private ListViewAdapter adapter;
    String strTitle[] = {"서울역 공항철도 15번 출구","용산역 1,2번출구","용산구청 앞","서울 용산 경찰서","용산경찰서 원효지구대","서울지방보훈청","용산세무서","국방부","순천향대학병원","하얏트호텔","용산 아이파크몰","동아사이언스 앞", "용산 아이피아"};
    String strDescription[]= {"서울특별시 용산구 청파동 297","서울특별시 용산구 한강대로 42","서울특별시 용산구 이태원동 녹사평대로 150","서울특별시 용산구 원효로89길 24","서울특별시 용산구 백범로 329","서울특별시 용산구 한강로동 131","서울특별시 용산구 서빙고로24길 15","서울특별시 용산구 용산동3가","서울특별시 용산구 한남동 657-76","서울특별시 용산구 한남동 747-7","서울특별시 용산구 한강로3가 한강대로23길 55 용산역 3","서울특별시 용산구 한강로3가 3-23","서울특별시 용산구 한강로2가 2-185"};
    int image = R.drawable.img_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listview = (ListView) findViewById(R.id.listview);
        MyAdapter adapter = new MyAdapter(this, strTitle, strDescription, image);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();

            }
        });

}

class MyAdapter extends ArrayAdapter<String>{
    Context context;
    String rTitle[];
    String rDescription[];
    int rImgs;

    MyAdapter(Context con, String sTitle[], String sDescription[], int imgs){
        super(con, R.layout.listview_item, R.id.name, sTitle);
        this.context = con;
        this.rTitle = sTitle;
        this.rDescription = sDescription;
        this.rImgs = imgs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listview_item = layoutInflater.inflate(R.layout.listview_item, parent, false);
        ImageView images = listview_item.findViewById(R.id.imageview);
        TextView myTitle = listview_item.findViewById(R.id.name);
        TextView myDescription = listview_item.findViewById(R.id.address);
        ImageButton imageButton = listview_item.findViewById(R.id.imagebutton);

        images.setImageResource(rImgs);
        myTitle.setText(rTitle[position]);
        myDescription.setText(rDescription[position]);

        return listview_item;
    }

    }
}