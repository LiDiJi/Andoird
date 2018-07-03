package com.ldj.wow.contacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ldj.wow.contacts.dao.ContacterSQL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ContactModel {
    private String index;
    private String name;
    private String phoneNumber, organization, emailAddress;
    private int offset;
    public ContactModel() {
        this.index = this.name = this.phoneNumber = this.emailAddress = this.organization = "";
        this.offset = 0;
    }

    public ContactModel(String name){
        this.index = FirstLetterUtil.getFirstLetter(name);
        this.name = name;
        this.phoneNumber = "10086";
        this.organization = this.emailAddress = "";
        this.offset = 0;

    }
    public ContactModel(String name, String phoneNumber, int mOffset){
        this.index = FirstLetterUtil.getFirstLetter(name);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.organization = this.emailAddress = "";
        this.offset = mOffset;
    }
    public ContactModel(String name, String phoneNumber, String emailAddress, String organization, int mOffset){
        this.index = FirstLetterUtil.getFirstLetter(name);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.organization = organization;
        this.emailAddress = emailAddress;
        this.offset = mOffset;
    }

    public String getIndex() {
        return index;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public String getOrganization() { return organization; }

    public String getEmailAddress() { return emailAddress; }

    public String getName() {
        return name;
    }

    public int getOffset(){ return offset; }

    public static List<ContactModel> getContacts(Context context) {
        List<ContactModel> contacts = new ArrayList<>();
        ContacterSQL contacter = new ContacterSQL(context);
        Cursor cursor = contacter.query();
        int offset = 0;
        while(cursor.moveToNext()){
            String phone = cursor.getString(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String organ = cursor.getString(3);
            contacts.add(new ContactModel(name, phone, email, organ, offset));
            offset++;
        }
        Collections.sort(contacts, new LetterComparator());
        return contacts;
    }
}
