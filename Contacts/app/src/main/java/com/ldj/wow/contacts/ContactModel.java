package com.ldj.wow.contacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ContactModel {
    private String index;
    private String name;
    private String phoneNumber, organization, emailAddress;

    public ContactModel() {
        this.index = this.name = this.phoneNumber = this.emailAddress = this.organization = "";
    }

    public ContactModel(String name){
        this.index = FirstLetterUtil.getFirstLetter(name);
        this.name = name;
        this.phoneNumber = "00000000000";
        this.organization = this.emailAddress = "";
    }
    public ContactModel(String name, String phoneNumber){
        this.index = FirstLetterUtil.getFirstLetter(name);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.organization = this.emailAddress = "";
    }
    public ContactModel(String name, String phoneNumber, String emailAddress, String organization){
        this.index = FirstLetterUtil.getFirstLetter(name);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.organization = organization;
        this.emailAddress = emailAddress;
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


    public static List<ContactModel> getContacts() {
        List<ContactModel> contacts = new ArrayList<>();

        contacts.add(new ContactModel("黎丁嘉", "11111"));
        contacts.add(new ContactModel("妈妈", "2222222"));
        contacts.add(new ContactModel("爸爸", "3333"));
        contacts.add(new ContactModel("妹妹", "44444"));
        contacts.add(new ContactModel("梁沛霖", "121231"));
        contacts.add(new ContactModel("梁志聪", "1231"));
        contacts.add(new ContactModel("赖启东", "1231"));
        contacts.add(new ContactModel("王甲海", "1231"));
        contacts.add(new ContactModel("毛明志", "1231"));
        contacts.add(new ContactModel("张永民", "1231"));
        contacts.add(new ContactModel("潘嵘", "1231"));
        contacts.add(new ContactModel("余丰人", "1231"));
        contacts.add(new ContactModel("陈炬华", "1231"));
        contacts.add(new ContactModel("K测试", "1231"));
        contacts.add(new ContactModel("A测试", "1231"));
        contacts.add(new ContactModel("E测试", "1231"));
        contacts.add(new ContactModel("T测试", "1231"));
        contacts.add(new ContactModel("えええ", "1231"));
        Collections.sort(contacts, new LetterComparator());
        return contacts;
    }
}
