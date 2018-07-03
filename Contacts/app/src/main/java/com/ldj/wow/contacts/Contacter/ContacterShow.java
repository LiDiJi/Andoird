package com.ldj.wow.contacts.Contacter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.ldj.wow.contacts.ContactModel;
import com.ldj.wow.contacts.MainActivity;
import com.ldj.wow.contacts.R;
import com.ldj.wow.contacts.dao.ContacterSQL;

/**
 * Created by wowsc on 2018/7/2.
 */

public class ContacterShow extends Activity {
    private ImageView go_back_main;
    TextView tv_name, tv_phone, tv_email, tv_organization;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacter_view);
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        String str_offset = bundle.getString("offset");
        int offset = Integer.parseInt(str_offset);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#FFFFFF"),true);
        go_back_main = (ImageView) findViewById(R.id.show_back_contact);
        go_back_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ContacterShow.this, MainActivity.class);
//                startActivity(intent);
                onBackPressed();  //调用系统返回按钮
                ContacterShow.this.finish();
            }
        });
        tv_phone = (TextView) findViewById(R.id.phoneNumber);
        tv_name = (TextView) findViewById(R.id.name);
        tv_email = (TextView) findViewById(R.id.emailAddress);
        tv_organization = (TextView) findViewById(R.id.organizationName);

        ContacterSQL contacter = new ContacterSQL(getApplication());
        Cursor cursor = contacter.query();
        cursor.moveToFirst();
        cursor.move(offset);
        String phone = cursor.getString(cursor.getColumnIndex("tel_number"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String email = cursor.getString(cursor.getColumnIndex("email"));
        String organization = cursor.getString(cursor.getColumnIndex("organization"));
        tv_phone.setText(phone);
        tv_name.setText(name);
        tv_email.setText(email);
        tv_organization.setText(organization);
    }
}
