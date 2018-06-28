package com.ldj.wow.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by wowsc on 2018/6/28.
 */

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView title;
        public ViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    private List<String> mData;
    public NormalAdapter(List<String> date){
        this.mData = date;
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        holder.title.setText(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=Toast.makeText(v.getContext(), "默认的Toast", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public int getItemCount(){
        return mData.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }
}
