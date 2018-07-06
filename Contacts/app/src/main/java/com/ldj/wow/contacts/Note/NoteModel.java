package com.ldj.wow.contacts.Note;

import android.content.Context;
import android.database.Cursor;

import com.ldj.wow.contacts.ContacterShow.ContactModel;
import com.ldj.wow.contacts.DAO.ContacterSQL;
import com.ldj.wow.contacts.DAO.NoteSQL;
import com.ldj.wow.contacts.FirstLetterUtil;
import com.ldj.wow.contacts.LetterComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wowsc on 2018/7/6.
 */

public class NoteModel {
    private String note_txt, day, place,title;
    private int id;
    public NoteModel() {
        this.title = this.note_txt = this.place = this.day = "";
        this.id = 0;
    }

    public NoteModel(String note_txt){
        this.note_txt = note_txt;
        this.title= "提醒";
        this.day = this.place = "";
        this.id = 0;

    }
    public NoteModel(String title, String txt, int Id){
        this.title = title;
        this.note_txt = txt;
        this.day = this.place = "";
        this.id = Id;
    }
    public NoteModel(String title, String txt, String day, String place){
        this.title = title;
        this.note_txt = txt;
        this.day = day;
        this.place = place;
        this.id = 0;
    }
    public NoteModel(String title, String txt, String day, String place, int mId){
        this.title = title;
        this.note_txt = txt;
        this.day = day;
        this.place = place;
        this.id = mId;
    }


    public String getNote_txt() { return note_txt; }

    public String getDay() { return day; }

    public String getPlace() { return place; }

    public String getTitle() {
        return title;
    }

    public int getId(){ return id; }

    public void setId(int _id){
        this.id = _id;
    }

    public static List<NoteModel> getNotes(Context context) {
        List<NoteModel> notes = new ArrayList<>();
        NoteSQL note_db = new NoteSQL(context);
        Cursor cursor = note_db.queryAll();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            String day = cursor.getString(1);
            String place = cursor.getString(2);
            String title = cursor.getString(3);
            String txt = cursor.getString(4);
            int main_id = cursor.getInt(0);
            notes.add(new NoteModel(title, txt, day, place, main_id));
        }
        //Collections.sort(notes, new LetterComparator());
        return notes;
    }
}
