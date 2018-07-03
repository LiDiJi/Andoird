package com.ldj.wow.contacts.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ldj.wow.contacts.ContactModel;

import java.util.List;


public class ContacterSQL extends SQLiteOpenHelper {
    private static final String DB_NAME = "contacts.db";
    private static final String TABLE_NAME = "contact";
    private static final String CREATE_TABLE = "create table contact(tel_number varchar primary key,"+
            "name varchar,"+
            "email varchar,"+
            "organization varchar);";
    private SQLiteDatabase db;
    public ContacterSQL(Context context){
        super(context, DB_NAME,null,2);
    }
    public void insert(ContentValues values){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void del(int number){
        if(db == null)
            db = getWritableDatabase();
        db.delete(TABLE_NAME,"tel_number=?",new String[]{String.valueOf(number)});
    }

    public Cursor query(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }

    public Cursor query(String[] col){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, col,null,null,null,null,null);
        return cursor;
    }
    public void close(){
        if(db != null){
            db.close();
        }
    }

    public void onCreate(SQLiteDatabase db){
        this.db = db;
        db.execSQL(CREATE_TABLE);
        ContentValues cv = new ContentValues();
        cv.put("tel_number","13719322092");
        cv.put("name","黎丁嘉");
        cv.put("email", "605202637@qq.com");
        cv.put("organization", "SYSU-CS");
        db.insert(TABLE_NAME,null,cv);

        cv.put("tel_number","18819253296");
        cv.put("name","赖启东");
        cv.put("email", "laiqd@mail2.sysu.edu.cn");
        cv.put("organization", "SYSU-CS");
        db.insert(TABLE_NAME,null,cv);

        cv.put("tel_number","10086");
        cv.put("name","测试");
        cv.put("email", "未知");
        cv.put("organization", "未知");
        db.insert(TABLE_NAME,null,cv);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}


}
