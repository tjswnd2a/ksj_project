package com.example.ksj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageView plus,delete,delete_cancel;
    private TextView yes,no,delete_name;
    private brandAdapter2 bAdapter2;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<brandData2> arrayList;
    private AppDatabase db;
    private View dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plus=(ImageView)findViewById(R.id.plus);
        delete=(ImageView)findViewById(R.id.delete);
        delete_cancel=(ImageView)findViewById(R.id.delete_cancel);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        delete_cancel.setVisibility(View.INVISIBLE);
        db= AppDatabase.getInstance(this);
        arrayList=new ArrayList<>();
        result();

        gridLayoutManager=new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(gridLayoutManager);
//        ArrayList<brandData2> arrayList= new ArrayList<brandData2>();


        bAdapter2 = new brandAdapter2(arrayList);
        recyclerView.setAdapter(bAdapter2);
        result();
        bAdapter2.setOnItemClickListener(new brandAdapter2.OnClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                    brandData2 item = bAdapter2.getItem(pos);
                if(delete.getVisibility()==View.VISIBLE) {
                    String event = item.get_Brand();
                    Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                    intent.putExtra("이벤트", event);
                    startActivity(intent);
                }
                else if(delete_cancel.getVisibility()==View.VISIBLE)
                {
                    dialog=(View)v.inflate(getApplicationContext(),R.layout.deletedialog,null);
                    AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
                    AlertDialog dlg=builder.create();
                    delete_name=(TextView)dialog.findViewById(R.id.delete_name);
                    yes=(TextView)dialog.findViewById(R.id.dYes);
                    no=(TextView)dialog.findViewById(R.id.dNo);
                    dlg.setView(dialog);
                    delete_name.setText("[ "+ item.get_Brand() +" ]");
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //int image = item.get_Image();
                            String name= item.get_Brand();
                            for(int i=0; i<db.EventDao().getAll().size();i++)
                            {
                                if(name.equals(db.EventDao().getAll().get(i).getEvent_name()))
                                {
                                    db.EventDao().delete(db.EventDao().getAll().get(i));
                                }
                            }
                            result();
                            bAdapter2.notifyDataSetChanged();
                            dlg.dismiss();
                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dlg.dismiss();
                        }
                    });
                    dlg.show();
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),PlusActivity.class);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete.setVisibility(View.INVISIBLE);
                delete_cancel.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"삭제하기",Toast.LENGTH_SHORT).show();
            }
        });
        delete_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete.setVisibility(View.VISIBLE);
                delete_cancel.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"삭제취소",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        PlusActivity plusActivity=new PlusActivity();
        if (plusActivity.count==true) {
            result();
            bAdapter2.notifyDataSetChanged();
        }
    }
    public void result(){
        arrayList.clear();
        for (int i = 0; i < db.EventDao().getAll().size(); i++) {
            int image = db.EventDao().getAll().get(i).getImage();
            String event = db.EventDao().getAll().get(i).getEvent_name();
            arrayList.add(new brandData2(image, event));

        }
    }
}