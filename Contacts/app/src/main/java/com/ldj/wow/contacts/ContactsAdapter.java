package com.ldj.wow.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {

    private List<ContactModel> contacts;
    private static final String TAG = "ContactsAdapter";
    private OnInfoClickListener mInfoClickListener;// 声明自定义的接口
    private OnCallClickListener mCallClickListener;
    private OnMsgClickListener onMsgClickListener;


    public ContactsAdapter(List<ContactModel> contacts) {
        this.contacts = contacts;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_item, null);
        ContactsViewHolder viewHolder = new ContactsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ContactsViewHolder holder, int position) {
        ContactModel contact = contacts.get(position);
        if (position == 0 || !contacts.get(position-1).getIndex().equals(contact.getIndex())) {
            holder.tvIndex.setVisibility(View.VISIBLE);
            holder.tvIndex.setText(contact.getIndex());
        } else {
            holder.tvIndex.setVisibility(View.GONE);
        }
        holder.tvName.setText(contact.getName());
        holder.index = contact.getOffset();
        if (mInfoClickListener != null) {
            holder.ivInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mInfoClickListener.onInfoClick(holder.itemView, position, holder.index);
                }
            });
        }

        if (mCallClickListener != null){
            holder.ivCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mCallClickListener.onCallClick(holder.itemView, position);
                }
            });
        }

        if (onMsgClickListener != null){
            holder.ivMsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onMsgClickListener.onMsgClick(holder.itemView, position);
                }
            });
        }
    }

    public void setOnInfoClickListener(OnInfoClickListener listener){
        this.mInfoClickListener = listener;
    }

    public void setOnCallClickListener(OnCallClickListener listener){
        this.mCallClickListener = listener;
    }

    public void setOnMsgClickListener(OnMsgClickListener listener){
        this.onMsgClickListener = listener;
    }
    @Override
    public int getItemCount() {
        return contacts.size();
    }

}
