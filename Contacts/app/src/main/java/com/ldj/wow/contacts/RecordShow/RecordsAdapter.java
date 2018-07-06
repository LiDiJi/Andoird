package com.ldj.wow.contacts.RecordShow;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldj.wow.contacts.R;

import java.util.List;

/**
 * Created by wowsc on 2018/7/4.
 */

public class RecordsAdapter extends RecyclerView.Adapter<RecordsViewHolder> {

    private List<RecordModel> records;

    public RecordsAdapter(List<RecordModel> records) {
        this.records = records;
    }

    @Override
    public RecordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.record_item, null);
        RecordsViewHolder viewHolder = new RecordsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecordsViewHolder holder, int position) {
        RecordModel recordModel = records.get(position);
        if (recordModel.getName() != null){
            holder.Name_Phone.setText(recordModel.getName());
        }
        else {
            String PhoneNumber = recordModel.getPhoneNumber();
            int i = PhoneNumber.length();
            if (i == 11){
                PhoneNumber = PhoneNumber.substring(0, 3) + " " + PhoneNumber.substring(3, 7) + " " + PhoneNumber.substring(7, 11);
                holder.Name_Phone.setText(PhoneNumber);
            }
            else if (i == 10){
                PhoneNumber = PhoneNumber.substring(0, 3) + " " + PhoneNumber.substring(3, 6) + " " + PhoneNumber.substring(6, 10);
                holder.Name_Phone.setText(PhoneNumber);
            }
            else holder.Name_Phone.setText(PhoneNumber);
        }
        String Time_date = recordModel.getRecordTime();
        holder.Time.setText(Time_date);
        if (position == 0 || !records.get(position-1).getRecordDay().equals(recordModel.getRecordDay())) {
            holder.DayIndex.setVisibility(View.VISIBLE);
            holder.DayIndex.setText(recordModel.getRecordDay());
        } else {
            holder.DayIndex.setVisibility(View.GONE);
        }
        holder.Place.setText(recordModel.getPlace());
        String variety = recordModel.getVariety();
        switch (variety){
            case "1":
                //接入
                holder.Variety.setImageResource(R.drawable.ic_call_received_light_green_700_18dp);
                break;
            case "2":
                //拨出
                holder.Variety.setImageResource(R.drawable.ic_call_made_amber_800_18dp);
                break;
            case "3":
                //未接来电
                holder.Variety.setImageResource(R.drawable.ic_call_missed_red_600_18dp);
                break;
            case "5":
                //拨出未接通
                holder.Variety.setImageResource(R.drawable.ic_call_missed_outgoing_purple_300_18dp);
                break;
            default:
                //拒绝通话
                holder.Variety.setImageResource(R.drawable.ic_call_end_blue_600_18dp);
                break;

        }

    }

    public int getItemCount() {
        return records.size();
    }
}
