package com.ldj.wow.contacts.RecordShow;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldj.wow.contacts.R;

/**
 * Created by wowsc on 2018/7/4.
 */

public class RecordsViewHolder extends RecyclerView.ViewHolder{

    TextView Name_Phone;
    TextView Time;
    TextView Place;
    ImageView Variety;
    TextView DayIndex;
    RecordsViewHolder(View itemView) {
        super(itemView);
        Name_Phone = (TextView) itemView.findViewById(R.id.tv_name);
        Time = (TextView) itemView.findViewById(R.id.tv_time);
        Place = (TextView) itemView.findViewById(R.id.tv_place);
        Variety = (ImageView) itemView.findViewById(R.id.iv_variety);
        DayIndex = (TextView) itemView.findViewById(R.id.tv_day_index);
    }
}

