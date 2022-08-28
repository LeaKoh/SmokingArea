package com.example.smokingarealist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeFragment extends Fragment implements OnMapReadyCallback{



    String strTitle[] = {"서울역 공항철도 15번 출구.국립극단","서울역 공항철도 15번 출구","용산역 광장앞(용산아이파크몰)","용산구청 앞","서울 용산 경찰서","용산경찰서 원효지구대","서울지방보훈청","용산세무서","국방부","순천향대학병원",
            "하얏트호텔","용산 아이파크몰","동아사이언스 앞", "용산 아이피아","용산 전자랜드","아모레퍼시픽","제일기획","제이엘레지던스","충영빌딩","e테크밸리",
            "삼원빌딩","대교빌딩","현대자동차 원효로서비스","진성빌딩","미성상사","조양빌딩","하나실업빌딩","오리온제과","우경빌딩","대한지방행정공제회",
            "넥서스벨리","지에스 한강에클라트","한강그랜드오피스","용산전자오피스텔","NH농협은행 용산금융센터","대우아이빌","유베이스","대원빌딩","기독교대한감리회여선교회관","일각빌딩",
            "한남빌딩","용산역 비비안", "서울역 풍산아이원플러스","용산 세광음악사","한화빌딩","보은개발","애전빌딩","갈월동 오피스","KCC IT빌딩","금강토탈패션할인매장",
            "용산빌딩","청룡빌딩","롯데지알에스","서경산업 금강빌딩","수빌딩","우리빌딩","기업은행 용산지점","진흥기업 진흥빌딩","용암빌딩 한치과","하나은행 LS용산타워지점",
            "한국폴리텍대학 서울정수캠퍼스 운동장", "한국폴리텍1대학 서울정수캠퍼스","한국폴리텍대학 서울정수캠퍼스 제2공학관","한국폴리텍대학 서울정수캠퍼스 학생회관"};
    String strDescription[]= {"서울특별시 용산구 청파동 297","서울특별시 용산구 동자동 43-227","서울특별시 용산구 한강대로 42","서울특별시 용산구 이태원동 녹사평대로 150","서울특별시 용산구 원효로89길 24","서울특별시 용산구 백범로 329","서울특별시 용산구 한강로1가 107","서울특별시 용산구 서빙고로24길 15","서울특별시 용산구 용산동3가","서울특별시 용산구 한남동 657-76",
            "서울특별시 용산구 한남동 747-7","서울특별시 용산구 한강로3가 한강대로23길 55 용산역 3","서울특별시 용산구 한강로3가 3-23","서울특별시 용산구 한강로2가 2-185","서울특별시 용산구 한강로3가 16-9","서울특별시 용산구 한강로2가 424","서울특별시 용산구 한남동 738-29", "서울특별시 용산구 용문동 10-4","서울특별시 용산구 용문동 28-5","서울특별시 용산구 원효로3가 51-37",
            "서울특별시 용산구 원효로3가 43-39","서울특별시 용산구 원효로3가 124-1","서울특별시 용산구 원효로4가 113-71","서울특별시 용산구 신계동 7-3","서울특별시 용산구 원효로1가 57-11","서울특별시 용산구 원효로2가 37-3", "서울특별시 용산구 원효로2가 92-5","서울특별시 용산구 문배동 30-10","서울특별시 용산구 신계동 40-1","서울특별시 용산구 한강로2가 101",
            "서울특별시 용산구 한강로2가 14-18","서울특별시 용산구 한강로3가 16-88","서울특별시 용산구 한강로3가 16-91","서울특별시 용산구 한강로3가 16-94","서울특별시 용산구 한강로2가 15-19", "서울특별시 용산구 한강로2가 2-392","서울특별시 용산구 원효로1가 133-3","서울특별시 용산구 원효로2가 59-8","서울특별시 용산구 한남동 36-1","서울특별시 용산구 이태원동 254-5",
            "서울특별시 용산구 한남동 737-37","서울특별시 용산구 한강로3가 40-999","서울특별시 용산구 서계동 219-17","서울특별시 용산구 서계동 232-32", "서울특별시 용산구 서계동 53-24","서울특별시 용산구 서계동 130-2","서울특별시 용산구 청파동3가 80-7","서울특별시 용산구 갈월동 103-17","서울특별시 용산구 갈월동 7-50","서울특별시 용산구 갈월동 88-18",
            "서울특별시 용산구 갈월동 92","서울특별시 용산구 갈월동 98-38","서울특별시 용산구 갈월동 98-6","서울특별시 용산구 남영동 10-1", "서울특별시 용산구 한강로2가 2-177","서울특별시 용산구 남영동 129-4","서울특별시 용산구 남영동 87","서울특별시 용산구 후암동 105-64","서울특별시 용산구 후암동 175-9","서울특별시 용산구 한강로2가 191-3",
            "서울특별시 용산구 보광동 238-2","서울특별시 용산구 보광동 238-2","서울특별시 용산구 보광동 238-2","서울특별시 용산구 보광동 238-2"};
    double latitude[] = {37.553149,37.55376,37.528404,37.532709,37.541169,37.538716,37.534923,37.523302,37.533237,37.534466,
            37.539372,37.529492,37.533068,37.533417,37.532818,37.52911,37.535103,37.536678,37.538329,37.533234,
            37.534626,37.533918,37.531999,37.53461,37.538839,37.536223,37.534709,37.535767,37.536202,37.534231,
            37.531685,37.534144,37.531399,37.531222,37.533197,37.531695,37.538001,37.535201,37.535109,37.540322,
            37.535022,37.528894,37.55492,37.553965,37.552032,37.551185,37.54175,37.540529,37.548507,37.543561,
            37.542611,37.541424,37.54154,37.544739,37.532824,37.54096,37.541995,37.5485,37.54589, 37.528002,
            37.529136,37.52871,37.529323,37.529799};
    double longitude[] = {126.968881,126.969662,126.965569,126.99,126.96765,126.96589,126.974192, 126.96868,126.97859,127.004585,
            126.997264,126.964318,126.963521,126.96736,126.958895,126.968532,126.998616,126.961147,126.959979,126.957656,
            126.959002,126.95763,126.953751,126.966177,126.968402,126.963549,126.960734, 126.969268,126.961404,126.970651,
            126.964859,126.955431,126.955055,126.956792,126.964645,126.968805,126.967147,126.963618,127.011499,126.989802,
            126.997224,126.964038,126.968415,126.965964,126.968943,126.96922,126.970388,126.972721,126.972205,126.971505,
            126.972137,126.972679,126.971791, 126.973179,126.970666,126.973516,126.973179,126.977574,126.976959,126.967632,
            126.996483,126.996226,126.995976,126.996498};

    int image = R.drawable.location;
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

