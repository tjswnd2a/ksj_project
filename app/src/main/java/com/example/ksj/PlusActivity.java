package com.example.ksj;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlusActivity extends AppCompatActivity {
    private ArrayList<brandData> arraylist,arraylistcopy;

    private brandAdapter bAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private View dialog;
    private ImageView back;
    private TextView list_name,Yes,No;
    private EditText search;


    public  AppDatabase db;
    public static boolean count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plusactivity_main);

        back=(ImageView)findViewById(R.id.Back);
        search=(EditText)findViewById(R.id.search);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView2);
        count=false;
        db= AppDatabase.getInstance(this);
        linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        BrandList();
        arraylistcopy=new ArrayList<>();
        arraylistcopy.addAll(arraylist);
        bAdapter= new brandAdapter(arraylist);
        recyclerView.setAdapter(bAdapter);

        //어뎁터 클릭 기능
        bAdapter.setOnItemClickListener(new brandAdapter.OnClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                brandData item= bAdapter.getItem(pos);
//                  Toast.makeText(getApplicationContext(),item.get_brand(),Toast.LENGTH_SHORT).show();
                dialog=(View)v.inflate(getApplicationContext(),R.layout.selectdialog,null);
                //bulider에선 직접적으로 dismiss를 수행할수가 없음
                //bulider의 몸을 담아줄 AlerDialog객체를 생성해주고 create를해준다.
                AlertDialog.Builder builder= new AlertDialog.Builder(PlusActivity.this);
                AlertDialog dlg=builder.create();
                list_name=(TextView)dialog.findViewById(R.id.list_name);
                Yes=(TextView)dialog.findViewById(R.id.Yes);
                No=(TextView)dialog.findViewById(R.id.No);
                dlg.setView(dialog);
                list_name.setText("[ "+ item.get_brand() +" ]");
                Yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        count=true;
                        int number =0;
                        int image= item.get_Image();
                        String name = item.get_brand();
                        for(int i=0;i<db.EventDao().getAll().size();i++)
                        {
                            if(name.equals(db.EventDao().getAll().get(i).getEvent_name()))
                            { number=1; }
                        }
                        if(number==1)
                        { Toast.makeText(getApplicationContext(),"존재하는 목록입니다",Toast.LENGTH_SHORT).show();}
                        else{
                        db.EventDao().insert(new Event_room(image,name));
                        Toast.makeText(getApplicationContext(),"["+ name+"]"+" 즐겨찾기 추가", Toast.LENGTH_SHORT).show();}
                        dlg.dismiss();

                    }
                });
                No.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.dismiss();
                    }
                });
                dlg.show();
            }
        });



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            String text =search.getText().toString();
            searchView(text);
            }
        });
        //Activity 나가기
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void searchView(String text){
        arraylist.clear();

        if(text.length() == 0){
            arraylist.addAll(arraylistcopy);
        }
        //문자열을 입력할때
        else{
            for(int i=0;i<arraylistcopy.size();i++)
            {
                if(arraylistcopy.get(i).get_brand().contains(text)){
                    arraylist.add(arraylistcopy.get(i));
                }
            }
        }
        bAdapter.notifyDataSetChanged();
    }
    public void BrandList(){
        arraylist=new ArrayList<>();
        arraylist.add(new brandData(R.drawable.maplestory,"메이플스토리","Game, RPG"));
        arraylist.add(new brandData(R.drawable.blackdesert,"검은사막","Game, MMO"+"RPG"));
        arraylist.add(new brandData(R.drawable.fifa,"피파온라인4","Game, SPORT"));
        arraylist.add(new brandData(R.drawable.lostark,"로스트아크","Game, MMO"+"RPG"));
        arraylist.add(new brandData(R.drawable.dnf,"던전 앤 파이터","Game, RPG"));
        arraylist.add(new brandData(R.drawable.bg,"배틀그라운드","Game, FPS"));
        arraylist.add(new brandData(R.drawable.sudden,"서든어택","Game, FPS"));
        arraylist.add(new brandData(R.drawable.kart,"카트라이더","Game, Race"));
        arraylist.add(new brandData(R.drawable.getam,"겟앰프드","Game, Action"));
    }
}