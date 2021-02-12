package com.example.ksj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class brandAdapter extends RecyclerView.Adapter<brandAdapter.CustomViewHolder> {
    private ArrayList<brandData> arraylist;
    private OnClickListener bListener=null;

    /////커스텀 리스너 인터페이스 OnClickListener 정의/////
    public interface OnClickListener{
        void onItemClick(View v, int pos);
    }
    //리스너 객체를 전달하는 메서드(setonclicklistener)롸 전달된 객체를 저장할 변수 (blistener)추가
    public void setOnItemClickListener(OnClickListener listener)
    {
        this.bListener=listener;
    }
    public brandAdapter(ArrayList arraylist)
    {
        this.arraylist=arraylist;
    }
    public brandData getItem(int position){ return arraylist.get(position); }
    @NonNull
    @Override
    public brandAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_recyclerview,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull brandAdapter.CustomViewHolder holder, int position) {
        holder.brand_image.setImageResource(arraylist.get(position).get_Image());
        holder.brand_name.setText(arraylist.get(position).get_brand());
        holder.brand_content.setText(arraylist.get(position).get_content());
    }

    @Override
    public int getItemCount() {
        return (null != arraylist ? arraylist.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView brand_image;
        private TextView brand_name;
        private TextView brand_content;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            brand_image=(ImageView)itemView.findViewById(R.id.brand_image);
            brand_name=(TextView)itemView.findViewById(R.id.brand_name);
            brand_content=(TextView)itemView.findViewById(R.id.brand_content);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos =getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION)
                    {
                        //아이템 클릭 이벤트 핸들러 메서드에서 리스너 객체 메서드 (onItemClick)호출
                        if(bListener !=null) {
                            bListener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }


    }
}
