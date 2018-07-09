package com.ldj.wow.contacts.Contacter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.githang.statusbar.StatusBarCompat;
import com.ldj.wow.contacts.ContacterShow.ContactModel;
import com.ldj.wow.contacts.MainActivity;
import com.ldj.wow.contacts.R;
import com.ldj.wow.contacts.DAO.ContacterSQL;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wowsc on 2018/7/2.
 */

public class AddContacter extends Activity {
    private Button Confirm_btn;
    private Button Cancal_btn;
    private Button btn_scan;
    private List<ContactModel> mContactModels;
    EditText nameText, phoneText, emailText, organizationText;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contacter);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#FFFFFF"),true);
        Confirm_btn = (Button) findViewById(R.id.confirm_new);
        Cancal_btn = (Button) findViewById(R.id.cancal_new);
        nameText = (EditText) findViewById(R.id.username);
        phoneText = (EditText) findViewById(R.id.userphone);
        emailText = (EditText) findViewById(R.id.useremail);
        organizationText = (EditText) findViewById(R.id.userorganization);
        mContactModels = new ArrayList<>();
        final ContacterSQL contacterSQL = new ContacterSQL(this);
        btn_scan = (Button) findViewById(R.id.scanQR);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(AddContacter.this, CaptureActivity.class);
                startActivityForResult(intent, 423);
            }
        });
        Confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, phone, email, organization;
                if (TextUtils.isEmpty(nameText.getText())){
                    name =  "联系人";
                }
                else name = nameText.getText().toString();

                if (TextUtils.isEmpty(phoneText.getText())){
                    int x=(int)(Math.random()*100);
                    phone = "00001"+x;
                }
                else phone = phoneText.getText().toString();

                if (TextUtils.isEmpty(emailText.getText())){
                    email = "@email.com";
                }
                else email = emailText.getText().toString();

                if (TextUtils.isEmpty(organizationText.getText())){
                    organization = "SYSU";
                }
                else organization = organizationText.getText().toString();

                ContentValues values = new ContentValues();
                values.put("tel_number",phone);
                values.put("name",name);
                values.put("email",email);
                values.put("organization",organization);
                contacterSQL.insert(values);
                ContacterSQL db = new ContacterSQL(getApplication());
                Cursor cursor = db.queryAll();
                cursor.moveToLast();
                int main_key_id = cursor.getInt(0);
                Intent intent = new Intent(AddContacter.this, MainActivity.class);
                intent.putExtra("data_return", name + "," + phone+ "," + email + "," + organization + "," + String.valueOf(main_key_id));

                startActivityForResult(intent, 415);
                finish();
            }
        });

        Cancal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();  //调用系统返回按钮
                finish();
            }
        });
    }
    //处理扫描后的结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 423){
            if (data != null){
                Bundle bundle = data.getExtras();
                String scanData = data.getStringExtra("result");
                String[] value_col = scanData.split(",");
                String phone = value_col[1];//name + phone + email + org
                String name = value_col[0];
                String email = value_col[2];
                String organization = value_col[3];
                nameText.setText(name);
                phoneText.setText(phone);
                emailText.setText(email);
                organizationText.setText(organization);
            }
        }
    }
}
