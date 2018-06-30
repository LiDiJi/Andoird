package com.ldj.wow.contacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ContactModel {
    private String index;
    private String name;

    public ContactModel(String name){
        this.index = FirstLetterUtil.getFirstLetter(name);
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }


    public static List<ContactModel> getContacts() {
        List<ContactModel> contacts = new ArrayList<>();

        contacts.add(new ContactModel("黎丁嘉"));
        contacts.add(new ContactModel("妈妈"));
        contacts.add(new ContactModel("爸爸"));
        contacts.add(new ContactModel("妹妹"));
        contacts.add(new ContactModel("梁沛霖"));
        contacts.add(new ContactModel("梁志聪"));
        contacts.add(new ContactModel("赖启东"));
        contacts.add(new ContactModel("王甲海"));
        contacts.add(new ContactModel("毛明志"));
        contacts.add(new ContactModel("张永民"));
        contacts.add(new ContactModel("潘嵘"));
        contacts.add(new ContactModel("余丰人"));
        contacts.add(new ContactModel("陈炬华"));
        contacts.add(new ContactModel("K测试"));
        contacts.add(new ContactModel("A测试"));
        contacts.add(new ContactModel("E测试"));
        contacts.add(new ContactModel("T测试"));
        contacts.add(new ContactModel("えええ"));
        Collections.sort(contacts, new LetterComparator());
        return contacts;
    }
}
