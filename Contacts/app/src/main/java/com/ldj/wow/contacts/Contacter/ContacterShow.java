package com.ldj.wow.contacts.Contacter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
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
