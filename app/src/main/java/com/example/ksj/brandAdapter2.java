package com.example.ksj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class brandAdapter2 extends RecyclerView.Adapter<brandAdapter2.CustomViewHolder> {
    private ArrayList<brandData2> arrayList;
    private OnClickListener bListener=null;
    public interface OnClickListener{
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnClickListener listener)
    {
        this.bListener=listener;
    }
    public brandAdapter2(ArrayList arrayList){
        this.arrayList=arrayList;
    }

    public brandData2 getItem(int position){ return arrayList.get(position); }
    @NonNull
    @Override
    public brandAdapter2.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_grid,parent,false);
        brandAdapter2.CustomViewHolder holder = new brandAdapter2.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull brandAdapter2.CustomViewHolder holder, int position) {
        holder.brand_image.setImageResource(arrayList.get(position).get_Image());
        holder.brand_name.setText(arrayList.get(position).get_Brand());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView brand_image;
        TextView brand_name;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            brand_image=(ImageView)itemView.findViewById(R.id.brand_image2);
            brand_name=(TextView)itemView.findViewById(R.id.brand_name2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos !=RecyclerView.NO_POSITION){
                        if(bListener !=null){
                            bListener.onItemClick(v,pos);
                        }
                    }
                }
            });
        }
    }
}
