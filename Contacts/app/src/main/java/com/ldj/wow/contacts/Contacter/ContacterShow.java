package com.ldj.wow.contacts.Contacter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.ldj.wow.contacts.HotelActivity;
import com.ldj.wow.contacts.MainActivity;
import com.ldj.wow.contacts.R;
import com.ldj.wow.contacts.DAO.ContacterSQL;

/**
 * Created by wowsc on 2018/7/2.
 */

public class ContacterShow extends Activity {
    private ImageView go_back_main, go_delete;
    TextView tv_name, tv_phone, tv_email, tv_organization;
    int main_id;
    private Button QRCode_btn;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacter_view);
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        String str_offset = bundle.getString("main_id");
        main_id = Integer.parseInt(str_offset);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#FFFFFF"),true);
        go_back_main = (ImageView) findViewById(R.id.show_back_contact);
        QRCode_btn = (Button) findViewById(R.id.QRCode_existed);
        go_back_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ContacterShow.this, MainActivity.class);
//                startActivity(intent);
                onBackPressed();  //调用系统返回按钮
                finish();
            }
        });
        go_delete = (ImageView) findViewById(R.id.delete_contacter);
        go_delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog();
                return false;
            }
        });
        tv_phone = (TextView) findViewById(R.id.phoneNumber);
        tv_name = (TextView) findViewById(R.id.name);
        tv_email = (TextView) findViewById(R.id.emailAddress);
        tv_organization = (TextView) findViewById(R.id.organizationName);

        ContacterSQL db = new ContacterSQL(getApplication());
        Cursor cursor = db.queryMainId(main_id);
        cursor.moveToLast();
        String phone = cursor.getString(cursor.getColumnIndex("tel_number"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String email = cursor.getString(cursor.getColumnIndex("email"));
        String organization = cursor.getString(cursor.getColumnIndex("organization"));
        tv_phone.setText(phone);
        tv_name.setText(name);
        tv_email.setText(email);
        tv_organization.setText(organization);
        cursor.close();
        QRCode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, phone, email, organization;
                name = tv_name.getText().toString();
                phone = tv_phone.getText().toString();
                email = tv_email.getText().toString();
                organization = tv_organization.getText().toString();

                ContentValues values = new ContentValues();
                values.put("tel_number",phone);
                values.put("name",name);
                values.put("email",email);
                values.put("organization",organization);
                ContacterSQL db = new ContacterSQL(getApplication());
                Cursor cursor = db.queryAll();
                cursor.moveToLast();
                int main_key_id = cursor.getInt(0);
                Intent intent = new Intent(ContacterShow.this, HotelActivity.class);
                intent.putExtra("data_return", name + "," + phone+ "," + email + "," + organization + "," + String.valueOf(main_key_id));
                startActivity(intent);
            }
        });
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_info_red_500_36dp);
        builder.setTitle("确认提醒");
        builder.setMessage("是否删除该联系人？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("删除",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContacterSQL db = new ContacterSQL(getApplication());
                        db.del(main_id);
                        Intent intent = new Intent(ContacterShow.this, MainActivity.class);
                        intent.putExtra("delte_model_id", String.valueOf(main_id));
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(intent, 423);
                        finish();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
