package com.ldj.wow.contacts.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "contacts.db";
    private static final String TABLE_NAME = "contact";
    private static final String CREATE_TABLE = "create table contact(tel_number varchar primary key,"+
            "name varchar,"+
            "email varchar,"+
            "organization varchar);";
    private SQLiteDatabase db;
    public SQLiteHelper(Context context){
        super(context,DB_NAME,null,2);
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

    public void close(){
        if(db != null){
            db.close();
        }
    }

    public void onCreate(SQLiteDatabase db){
        this.db = db;
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
