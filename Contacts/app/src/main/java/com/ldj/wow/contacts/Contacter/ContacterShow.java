package com.ldj.wow.contacts.Contacter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ldj.wow.contacts.ContactModel;
import com.ldj.wow.contacts.R;

/**
 * Created by wowsc on 2018/7/2.
 */

public class ContacterShow extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacter_view);
        ContactModel sample = new ContactModel("QIDONG-LAI", "18819253296", "laiqd@mail2..sysu.edu.cn", "School of Dat and Computer Science");
        TextView name, phone, email, organization;
        name = (TextView) findViewById(R.id.name);
        name.setText(sample.getName());
        phone = (TextView) findViewById(R.id.phoneNumber);
        phone.setText(sample.getPhoneNumber());
        email = (TextView) findViewById(R.id.emailAddress);
        email.setText(sample.getEmailAddress());
        organization = (TextView) findViewById(R.id.organizationName);
        organization.setText(sample.getOrganization());
    }
}
