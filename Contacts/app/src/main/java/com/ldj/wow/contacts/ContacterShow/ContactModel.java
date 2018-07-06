package com.ldj.wow.contacts.ContacterShow;

import android.content.Context;
import android.database.Cursor;

import com.ldj.wow.contacts.FirstLetterUtil;
import com.ldj.wow.contacts.LetterComparator;
import com.ldj.wow.contacts.DAO.ContacterSQL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ContactModel {
    private String index;
    private String name;
    private String phoneNumber, organization, emailAddress;
    private int id;
    public ContactModel() {
        this.index = this.name = this.phoneNumber = this.emailAddress = this.organization = "";
        this.id = 0;
    }

    public ContactModel(String name){
        this.index = FirstLetterUtil.getFirstLetter(name);
        this.name = name;
        this.phoneNumber = "10086";
        this.organization = this.emailAddress = "";
        this.id = 0;

    }
    public ContactModel(String name, String phoneNumber, int mId){
        this.index = FirstLetterUtil.getFirstLetter(name);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.organization = this.emailAddress = "";
        this.id = mId;
    }
    public ContactModel(String name, String phoneNumber, String emailAddress, String organization, int mId){
        this.index = FirstLetterUtil.getFirstLetter(name);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.organization = organization;
        this.emailAddress = emailAddress;
        this.id = mId;
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

    public int getId(){ return id; }

    public static List<ContactModel> getContacts(Context context) {
        List<ContactModel> contacts = new ArrayList<>();
        ContacterSQL contacter = new ContacterSQL(context);
        Cursor cursor = contacter.queryAll();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            String phone = cursor.getString(1);
            String name = cursor.getString(2);
            String email = cursor.getString(3);
            String organ = cursor.getString(4);
            int main_id = cursor.getInt(0);
            contacts.add(new ContactModel(name, phone, email, organ, main_id));
        }
        Collections.sort(contacts, new LetterComparator());
        return contacts;
    }
}
