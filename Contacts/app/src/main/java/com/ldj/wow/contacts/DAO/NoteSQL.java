package com.ldj.wow.contacts.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wowsc on 2018/7/6.
 */

public class NoteSQL extends SQLiteOpenHelper{
    private static final String DB_NAME = "note.db";
    private static final String TABLE_NAME = "note";
    private static final String CREATE_TABLE = "create table note(id integer primary key autoincrement," +
            "day varchar,"+
            "place varchar,"+
            "title varchar," +
            "note_txt varchar);";
    private SQLiteDatabase db;
    public NoteSQL(Context context){
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

    public void close(){
        if(db != null){
            db.close();
        }
    }

    public void onCreate(SQLiteDatabase db){
        this.db = db;
        db.execSQL(CREATE_TABLE);
        ContentValues cv = new ContentValues();
        cv.put("day","2018-07-09");
        cv.put("place","中山大学");
        cv.put("title", "安卓大作业！");
        cv.put("note_txt", "星期一晚上提交安卓大作业！");
        db.insert(TABLE_NAME,null, cv);

        cv.put("day","2018-07-07");
        cv.put("place","慎6");
        cv.put("title", "app完成");
        cv.put("note_txt", "希望明天就能做完app");
        db.insert(TABLE_NAME,null, cv);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
