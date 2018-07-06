package com.ldj.wow.contacts.RecordShow;

/**
 * Created by wowsc on 2018/7/4.
 */

public class RecordModel {
    String RecordTime;
    String Place;
    String PhoneNumber;
    String Name;
    String Variety;
    String RecordDay;

    public  RecordModel(){
        RecordTime = Place = PhoneNumber =  Name = Variety = RecordDay = "";
    }

    public  RecordModel(String _time, String _day, String _place, String _number, String _name, String _variety){
        RecordTime = _time;
        RecordDay = _day;
        Place = _place;
        PhoneNumber = _number;
        Name = _name;
        Variety = _variety;
    }

    public String getPhoneNumber(){return PhoneNumber;}
    public String getRecordTime() {return RecordTime;}
    public String getPlace() {return  Place;}
    public String getName() {return Name;}
    public String getVariety() {return Variety;}
    public String getRecordDay() {return RecordDay;}
}
