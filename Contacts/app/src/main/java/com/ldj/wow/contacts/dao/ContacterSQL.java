package com.ldj.wow.contacts.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ContacterSQL extends SQLiteOpenHelper {
    private static final String DB_NAME = "contacts.db";
    private static final String TABLE_NAME = "contact";
    private static final String CREATE_TABLE = "create table contact(id integer primary key autoincrement," +
            "tel_number varchar,"+
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
    }

    public void del(int number){
        if(db == null)
            db = getWritableDatabase();
        db.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(number)});
    }

    public Cursor queryAll(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }

    public Cursor queryMainId(int _id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "id=?", new String[]{String.valueOf(_id)},null,null,null);
        return cursor;
    }

    public Cursor queryPhoneNumber(String number){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "tel_number=?", new String[]{number},null,null,null);
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
        cv.put("name","丁嘉黎");
        cv.put("email", "605202637@qq.com");
        cv.put("organization", "SYSU-CS");
        db.insert(TABLE_NAME,null,cv);

        cv.put("tel_number","18819253296");
        cv.put("name","启东赖");
        cv.put("email", "laiqd@mail2.sysu.edu.cn");
        cv.put("organization", "SYSU-CS");
        db.insert(TABLE_NAME,null,cv);

        cv.put("tel_number","10086");
        cv.put("name","中国移动");
        cv.put("email", "未知");
        cv.put("organization", "移动");
        db.insert(TABLE_NAME,null,cv);

        cv.put("tel_number","13129382700");
        cv.put("name","志聪梁");
        cv.put("email", "未知");
        cv.put("organization", "SYSU-CS");
        db.insert(TABLE_NAME,null,cv);

        cv.put("tel_number","13435644823");
        cv.put("name","世豪梁");
        cv.put("email", "未知");
        cv.put("organization", "SYSU-CS");
        db.insert(TABLE_NAME,null,cv);

        cv.put("tel_number","13602886293");
        cv.put("name","沛霖梁");
        cv.put("email", "未知");
        cv.put("organization", "SYSU-CS");
        db.insert(TABLE_NAME,null,cv);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}


}
