package com.ldj.wow.contacts.Note;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldj.wow.contacts.R;

/**
 * Created by wowsc on 2018/7/6.
 */

public class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView day;
        TextView place;
        TextView txt;
        ImageView delete;
        int id;
        public NoteViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.note_title);
            day = (TextView) itemView.findViewById(R.id.note_day);
            place = (TextView) itemView.findViewById(R.id.note_place);
            txt = (TextView) itemView.findViewById(R.id.tv_note);
            delete = (ImageView) itemView.findViewById(R.id.delete_note);
            id = 0;
        }
}
