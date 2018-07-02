package com.ldj.wow.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {

    private List<ContactModel> contacts;
    private static final String TAG = "ContactsAdapter";
    private OnItemClickListener mClickListener;// 声明自定义的接口
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

        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mClickListener = listener;
    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

}
