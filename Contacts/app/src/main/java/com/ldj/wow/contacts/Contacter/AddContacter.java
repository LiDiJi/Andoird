package com.ldj.wow.contacts.Contacter;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.githang.statusbar.StatusBarCompat;
import com.ldj.wow.contacts.ContactModel;
import com.ldj.wow.contacts.R;
import com.ldj.wow.contacts.dao.ContacterSQL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wowsc on 2018/7/2.
 */

public class AddContacter extends Activity {
    private Button Confirm_btn;
    private Button Cancal_btn;
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

        Confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, phone, email, organization;
                if (TextUtils.isEmpty(nameText.getText())){
                    name =  "联系人";
                }
                else name = nameText.getText().toString();

                if (TextUtils.isEmpty(phoneText.getText())){
                    phone = "00001";
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
