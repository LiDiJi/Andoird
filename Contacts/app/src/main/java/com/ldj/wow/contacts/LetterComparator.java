package com.ldj.wow.contacts;

import com.ldj.wow.contacts.ContacterShow.ContactModel;

import java.util.Comparator;


public class LetterComparator implements Comparator<ContactModel> {

    @Override
    public int compare(ContactModel contactModel, ContactModel t1) {
        if (contactModel == null || t1 == null){
            return 0;
        }
        String lhsSortLetters = contactModel.getIndex().substring(0, 1).toUpperCase();
        String rhsSortLetters = t1.getIndex().substring(0, 1).toUpperCase();
        return lhsSortLetters.compareTo(rhsSortLetters);
    }
}
