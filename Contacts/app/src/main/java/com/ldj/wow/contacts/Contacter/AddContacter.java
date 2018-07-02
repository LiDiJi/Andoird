package com.ldj.wow.contacts.Contacter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.githang.statusbar.StatusBarCompat;
import com.ldj.wow.contacts.ContactModel;
import com.ldj.wow.contacts.MainActivity;
import com.ldj.wow.contacts.R;

/**
 * Created by wowsc on 2018/7/2.
 */

public class AddContacter extends Activity {
    private Button Confirm_btn;
    private Button Cancal_btn;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contacter);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#FFFFFF"),true);
        Confirm_btn = (Button) findViewById(R.id.confirm_new);
        Cancal_btn = (Button) findViewById(R.id.cancal_new);

        Confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameText, phoneText, emailText, organizationText;
                String name, phone, email, organization;
                nameText = (EditText) findViewById(R.id.username);
                name = nameText.getText().toString();
                phoneText = (EditText) findViewById(R.id.userphone);
                phone = phoneText.getText().toString();
                emailText = (EditText) findViewById(R.id.useremail);
                email = emailText.getText().toString();
                organizationText = (EditText) findViewById(R.id.userorganization);
                organization = organizationText.getText().toString();
                ContactModel sample = new ContactModel(name, phone, email, organization);
            }
        });

        Cancal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddContacter.this, MainActivity.class);
                startActivity(intent);
                AddContacter.this.finish();
            }
        });
    }
}
