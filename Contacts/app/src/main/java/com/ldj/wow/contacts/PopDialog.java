package com.ldj.wow.contacts;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ldj.wow.contacts.DAO.ContacterSQL;
import com.ldj.wow.contacts.DAO.NoteSQL;
import com.ldj.wow.contacts.Note.NoteModel;

/**
 * Created by wowsc on 2018/7/6.
 */

public class PopDialog extends Dialog {
    private Button yes;
    private Button no;
    private EditText Note_Day;
    private EditText Note_Place;
    private EditText Note_Title;
    private EditText Note_Txt;
    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    public PopDialog(Context context){
        super(context);
    }

    public PopDialog(Context context, int theme){
        super(context, theme);
    }

    public void setNoOnclickListener(onNoOnclickListener onNoOnclickListener) {
        this.noOnclickListener = onNoOnclickListener;
    }

    public void setYesOnclickListener(String Today, onYesOnclickListener yesOnclickListener) {
        Note_Day.setText(Today);
        this.yesOnclickListener = yesOnclickListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_dialog);
        //空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();

        //初始化界面控件的事件
        initEvent();

    }

    private void initView(){
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);
        Note_Day = (EditText) findViewById(R.id.et_day_input);
        Note_Place = (EditText) findViewById(R.id.et_place_input);
        Note_Title = (EditText) findViewById(R.id.et_title_input);
        Note_Txt = (EditText) findViewById(R.id.et_txt_input);
    }

    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    String day, place, title, txt;
                    if (TextUtils.isEmpty(Note_Day.getText())){
                        day =  "2018-07-07";
                    }
                    else day = Note_Day.getText().toString();
                    if (TextUtils.isEmpty(Note_Place.getText())){
                        place =  "中国";
                    }
                    else place = Note_Place.getText().toString();
                    if (TextUtils.isEmpty(Note_Title.getText())){
                        title =  "新建提醒";
                    }
                    else title = Note_Title.getText().toString();
                    if (TextUtils.isEmpty(Note_Txt.getText())){
                        txt =  "新建提醒";
                    }
                    else txt = Note_Txt.getText().toString();
                    yesOnclickListener.onYesOnclick(title, txt, day, place);
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    public interface onNoOnclickListener {
        public void onNoClick();
    }

    public interface onYesOnclickListener {
        public void onYesOnclick(String title, String txt, String day, String place);
    }
}
