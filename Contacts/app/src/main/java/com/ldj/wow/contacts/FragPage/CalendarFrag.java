package com.ldj.wow.contacts.FragPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ldj.wow.contacts.R;
import com.necer.ncalendar.calendar.MonthCalendar;

/**
 * Created by wowsc on 2018/6/28.
 */

public class CalendarFrag extends Fragment {
    private Button ToLastMonth;
    private MonthCalendar ToGetLastMonth;
    private Button ToNextMonth;
    private MonthCalendar ToGetNextMonth;
    private Button ToToday;
    private MonthCalendar ToGetToday;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View contactsView = inflater.inflate(R.layout.calendar_page, container,false);
//        ToLastMonth = (Button) contactsView.findViewById(R.id.toLastMonth);
//        ToGetLastMonth = (MonthCalendar) contactsView.findViewById(R.id.monthcalendar);
//        ToLastMonth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToGetLastMonth.toLastPager();
//            }
//        });
//        ToNextMonth = (Button) contactsView.findViewById(R.id.toNextMonth);
//        ToGetNextMonth = (MonthCalendar) contactsView.findViewById(R.id.monthcalendar);
//        ToNextMonth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToGetNextMonth.toNextPager();
//            }
//        });
//        ToToday = (Button) contactsView.findViewById(R.id.toToday);
//        ToGetToday = (MonthCalendar) contactsView.findViewById(R.id.monthcalendar);
//        ToToday.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToGetToday.toToday();
//            }
//        });
        return contactsView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }
}
