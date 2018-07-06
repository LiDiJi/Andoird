package com.ldj.wow.contacts.Note;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldj.wow.contacts.ContacterShow.ContactModel;
import com.ldj.wow.contacts.OnDeleteClickListener;
import com.ldj.wow.contacts.R;

import java.util.List;

/**
 * Created by wowsc on 2018/7/6.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder>{
    private List<NoteModel> notes;

    public NoteAdapter(List<NoteModel> notes) {
        this.notes = notes;
    }

    private OnDeleteClickListener mDeleteClickListener;
    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.note_item, null);
        NoteViewHolder viewHolder = new NoteViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NoteViewHolder holder, final int position) {
        NoteModel noteModel = notes.get(position);
        holder.title.setText(noteModel.getTitle());
        holder.day.setText(noteModel.getDay());
        holder.place.setText(noteModel.getPlace());
        holder.txt.setText(noteModel.getNote_txt());
        holder.id = noteModel.getId();
        if (mDeleteClickListener != null){
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mDeleteClickListener.onInfoClick(holder.itemView, position, holder.id);
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void  setOnDeleteClickListener(OnDeleteClickListener listener){this.mDeleteClickListener = listener;}
}
