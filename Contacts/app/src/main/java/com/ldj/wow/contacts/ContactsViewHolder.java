package com.ldj.wow.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wowsc on 2018/7/2.
 */

public class ContactsViewHolder extends RecyclerView.ViewHolder{
    TextView tvIndex;
    ImageView ivAvatar;
    TextView tvName;
    ImageView ivCall;
    ImageView ivMsg;
    ImageView ivInfo;
    int index;
    ContactsViewHolder(View itemView) {
        super(itemView);
        tvIndex = (TextView) itemView.findViewById(R.id.tv_index);
        ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        ivCall = (ImageView) itemView.findViewById(R.id.iv_call);
        ivMsg = (ImageView) itemView.findViewById(R.id.iv_msg);
        ivInfo = (ImageView) itemView.findViewById(R.id.iv_info);
        index = 0;
    }
}
