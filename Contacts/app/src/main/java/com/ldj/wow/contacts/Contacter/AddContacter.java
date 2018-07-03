package com.ldj.wow.contacts.Contacter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.githang.statusbar.StatusBarCompat;
import com.ldj.wow.contacts.ContactModel;
import com.ldj.wow.contacts.MainActivity;
import com.ldj.wow.contacts.R;
import com.ldj.wow.contacts.dao.SQLiteHelper;

/**
 * Created by wowsc on 2018/7/2.
 */

public class AddContacter extends Activity {
    private Button Confirm_btn;
    private Button Cancal_btn;
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

        final SQLiteHelper dbHelper = new SQLiteHelper(this);

        Confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, phone, email, organization;
                if (TextUtils.isEmpty(nameText.getText())){
                    name =  "联系人";
                }
                else name = nameText.getText().toString();

                if (TextUtils.isEmpty(phoneText.getText())){
                    phone = "110";
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

                ContactModel sample = new ContactModel(name, phone, email, organization);

                ContentValues values = new ContentValues();
                values.put("tel_number",phone);
                values.put("name",name);
                values.put("email",email);
                values.put("organization",organization);
                dbHelper.insert(values);


            }
        });

        Cancal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(AddContacter.this, MainActivity.class);
//                startActivity(intent);
                onBackPressed();  //调用系统返回按钮
                AddContacter.this.finish();
            }
        });
    }
}
