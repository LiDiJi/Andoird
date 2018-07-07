package com.ldj.wow.contacts.FragPage;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldj.wow.contacts.Contacter.ContacterShow;
import com.ldj.wow.contacts.DAO.ContacterSQL;
import com.ldj.wow.contacts.DAO.NoteSQL;
import com.ldj.wow.contacts.MainActivity;
import com.ldj.wow.contacts.Note.NoteAdapter;
import com.ldj.wow.contacts.Note.NoteModel;
import com.ldj.wow.contacts.OnDeleteClickListener;
import com.ldj.wow.contacts.PopDialog;
import com.ldj.wow.contacts.R;
import com.necer.ncalendar.calendar.NCalendar;
import com.necer.ncalendar.listener.OnCalendarChangedListener;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wowsc on 2018/6/28.
 */

public class CalendarFrag extends Fragment {
   private TextView curYear;
   private TextView curMonth;
   private Button Go_Today;
   private NCalendar ncalendar;
   private RecyclerView recyclerView;
   private List<NoteModel> mShowNoteModel;
   private ImageView add_Note;
   private NoteAdapter noteAdapter;
   private List<String> Point_list;
   private ImageView Note_delete;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.calendar_page, container,false);
        initView(view);
        Go_Today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ncalendar.toToday();
            }
        });
        SimpleDateFormat YearDateFormat = new SimpleDateFormat("yyyy");
        Date Yeardate = new Date(System.currentTimeMillis());
        curYear.setText(YearDateFormat.format(Yeardate));
        SimpleDateFormat DayDateFormat = new SimpleDateFormat("M月dd日");
        Date Monthdate = new Date(System.currentTimeMillis());
        curMonth.setText(DayDateFormat.format(Monthdate));
        final SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = new Date(System.currentTimeMillis());
        ncalendar.setDateInterval("2000-01-01", "2100-01-01");
        ncalendar.setDate(DateFormat.format(date));
        ncalendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(LocalDate date) {
                curMonth.setText(date.getMonthOfYear() + "月" + date.getDayOfMonth() + "日");
                curYear.setText(date.getYear() + "");
            }
        });
        mShowNoteModel = new ArrayList<>();
        mShowNoteModel.addAll(NoteModel.getNotes(getContext()));
        Point_list = new ArrayList<>();
        for (int i = 0;i < mShowNoteModel.size();i++){
            if (!Point_list.contains(mShowNoteModel.get(i).getDay())){
                Point_list.add(mShowNoteModel.get(i).getDay());
            }
        }
        ncalendar.post(new Runnable() {
            @Override
            public void run() {
                ncalendar.setPoint(Point_list);
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.NoteView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        noteAdapter = new NoteAdapter(mShowNoteModel);
        recyclerView.setAdapter(noteAdapter);
        noteAdapter.setOnDeleteClickListener(new OnDeleteClickListener() {
            @Override
            public void onInfoClick(View view, int postion, int id) {
                final int main_id = id;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setIcon(R.drawable.ic_info_red_500_36dp);
                builder.setTitle("确认提醒");
                builder.setMessage("是否删除该记录？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("删除",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NoteSQL db = new NoteSQL(getContext());
                                db.del(main_id);
                                String remove_date = "";
                                for (int j = 0;j < mShowNoteModel.size();j++){
                                    if (mShowNoteModel.get(j).getId() == main_id){
                                        remove_date = mShowNoteModel.get(j).getDay();
                                        mShowNoteModel.remove(j);
                                        break;
                                    }
                                }
                                noteAdapter.notifyDataSetChanged();
                                for (int j = 0;j < Point_list.size();j++){
                                    if (Point_list.get(j).equals(remove_date)){
                                        Point_list.remove(j);
                                        break;
                                    }
                                }
                                ncalendar.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ncalendar.setPoint(Point_list);
                                    }
                                });
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        add_Note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopDialog dialog = new PopDialog(getContext(), R.style.MyDialog);
                dialog.show();
                final Activity curActivity = getActivity();
                WindowManager.LayoutParams lp = curActivity.getWindow().getAttributes();
                lp.alpha = 0.5f;
                curActivity.getWindow().setAttributes(lp);
                curActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

                dialog.setNoOnclickListener(new PopDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        dialog.dismiss();
                        WindowManager.LayoutParams lp = curActivity.getWindow().getAttributes();
                        lp.alpha = 1.0f;
                        curActivity.getWindow().setAttributes(lp);
                        curActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    }
                });
                dialog.setYesOnclickListener(DateFormat.format(date), new PopDialog.onYesOnclickListener() {
                    @Override
                    public void onYesOnclick(String title, String txt, String day, String place) {
                        ContentValues values = new ContentValues();
                        values.put("day", day);
                        values.put("place", place);
                        values.put("title", title);
                        values.put("note_txt", txt);
                        NoteSQL noteSQL = new NoteSQL(getContext());
                        noteSQL.insert(values);
                        Cursor cursor = noteSQL.queryAll();
                        cursor.moveToLast();
                        int main_key_id = cursor.getInt(0);
                        NoteModel noteModel = new NoteModel(title, txt, day, place, main_key_id);
                        mShowNoteModel.add(noteModel);
                        noteAdapter.notifyDataSetChanged();
                        Point_list.add(noteModel.getDay());
                        ncalendar.post(new Runnable() {
                            @Override
                            public void run() {
                                ncalendar.setPoint(Point_list);
                            }
                        });
                        dialog.dismiss();
                        WindowManager.LayoutParams lp = curActivity.getWindow().getAttributes();
                        lp.alpha = 1.0f;
                        curActivity.getWindow().setAttributes(lp);
                        curActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    }
                });
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    private void  initView(View view){
        curYear = (TextView) view.findViewById(R.id.tv_year);
        curMonth = (TextView) view.findViewById(R.id.tv_month);
        ncalendar = (NCalendar) view.findViewById(R.id.ncalendar);
        add_Note = (ImageView) view.findViewById(R.id.add_note);
        Go_Today = (Button) view.findViewById(R.id.Today);
    }
}
