package com.example.smokingarealist;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment implements OnMapReadyCallback{

    String strTitle[] = {"서울역 공항철도 15번 출구","용산역 1,2번출구","용산구청 앞","서울 용산 경찰서","용산경찰서 원효지구대","서울지방보훈청","용산세무서","국방부","순천향대학병원","하얏트호텔","용산 아이파크몰","동아사이언스 앞", "용산 아이피아"};
    String strDescription[]= {"서울특별시 용산구 청파동 297","서울특별시 용산구 한강대로 42","서울특별시 용산구 이태원동 녹사평대로 150","서울특별시 용산구 원효로89길 24","서울특별시 용산구 백범로 329","서울특별시 용산구 한강로동 131","서울특별시 용산구 서빙고로24길 15","서울특별시 용산구 용산동3가","서울특별시 용산구 한남동 657-76","서울특별시 용산구 한남동 747-7","서울특별시 용산구 한강로3가 한강대로23길 55 용산역 3","서울특별시 용산구 한강로3가 3-23","서울특별시 용산구 한강로2가 2-185"};
    double latitude[] = {37.553149,37.55376,37.532709,37.541169,37.538716,37.534923,37.523302, 37.533237, 37.534466,37.539372,37.529492,37.533068,37.533417};
    double longitude[] = {126.968881,126.969662,126.99,126.96765,126.96589,126.974192, 126.96868,126.97859,127.004585,126.997264,126.964318,126.963521,126.96736};
    int image = R.drawable.img_1;
    Context ct;

   MapsFragment mapsFragment = new MapsFragment();

    private Button btn;
    private Button backbtn;

    public HomeFragment(){}

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart(){
        super.onStart();
        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);

        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Context ct;
        ct = container.getContext();

        ListView listView = (ListView)v.findViewById(R.id.listview);
        MyAdapter adapter = new MyAdapter(ct, strTitle, strDescription, image);
        listView.setAdapter(adapter);

        listView.setVisibility(View.INVISIBLE);

        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);

//        supportMapFragment.getMapAsync(this);


        btn = (Button) v.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportMapFragment.getView().setVisibility(View.INVISIBLE);
                listView.setVisibility(View.VISIBLE);
                btn.setVisibility(View.INVISIBLE);
                backbtn.setVisibility(View.VISIBLE);
            }
        });

        backbtn = (Button) v.findViewById(R.id.backbtn);
        backbtn.setVisibility(View.INVISIBLE);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportMapFragment.getView().setVisibility(View.VISIBLE);
                listView.setVisibility(View.INVISIBLE);
                btn.setVisibility(View.VISIBLE);
                backbtn.setVisibility(View.INVISIBLE);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mapsFragment.latitude = latitude[position];
                mapsFragment.longitude = longitude[position];

                getFragmentManager().beginTransaction().replace(R.id.container, mapsFragment).commit();
            }
        });

       return v;

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng SEOUL = new LatLng(37.553149,126.968881 );
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));

    }
}

class MyAdapter extends ArrayAdapter<String> {
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
        ImageView imageButton = listview_item.findViewById(R.id.imagebutton);


        images.setImageResource(rImgs);
        myTitle.setText(rTitle[position]);
        myDescription.setText(rDescription[position]);

        return listview_item;
    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }



}

