package com.ldj.wow.contacts.FragPage;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldj.wow.contacts.PinnedHeaderDecoration;
import com.ldj.wow.contacts.R;
import com.ldj.wow.contacts.RecordShow.RecordModel;
import com.ldj.wow.contacts.RecordShow.RecordsAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by wowsc on 2018/6/28.
 */

public class RecordFrag extends Fragment {
    private List<RecordModel> mShowModels;
    private RecordsAdapter mAdapter;
    private RecyclerView recyclerView;
    private final int PERMS_REQUEST_CODE = 300;
    private String[] perms = {Manifest.permission.READ_CALL_LOG};
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.record_page, container, false);
        initData();
        queryCallLog();
        return view;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initData() {
        mShowModels = new ArrayList<RecordModel>();
    }

    private void setRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recordView);
        mAdapter = new RecordsAdapter(mShowModels);
        // RecyclerView设置相关
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
        decoration.registerTypePinnedHeader(2, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(mAdapter);

    }


    public void queryCallLog() {
        //  权限检查
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
          requestPermissions(perms, PERMS_REQUEST_CODE);//请求权限
        }
        else {
            ArrayList<RecordModel> record_result = new ArrayList<RecordModel>();;
            ContentResolver resolver = getContext().getContentResolver();
            Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, // 查询通话记录的URI
                    new String[]{CallLog.Calls.CACHED_NAME// 通话记录的联系人
                            , CallLog.Calls.NUMBER// 通话记录的电话号码
                            , CallLog.Calls.DATE// 通话记录的日期
                            , CallLog.Calls.TYPE// 通话类型
                            , CallLog.Calls.GEOCODED_LOCATION}
                    , null, null, CallLog.Calls.DEFAULT_SORT_ORDER// 按照时间逆序排列，最近打的最先显示
            );
            Log.v("CursorSize", String.valueOf(cursor.getCount()));
            if (cursor != null) {
                try {
                    while (cursor.moveToNext()) {
                        String date = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
                        String place = cursor.getString(cursor.getColumnIndex(CallLog.Calls.GEOCODED_LOCATION));
                        String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                        String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                        String type = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));
                        DateFormat CurDay = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String Day_date = CurDay.format(new Date(Long.parseLong(date)));
                        DateFormat CurTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                        String Time_date = CurTime.format(new Date(Long.parseLong(date)));
                        RecordModel recordModel = new RecordModel(Time_date, Day_date, place, number, name, type);
                        record_result.add(recordModel);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cursor.close();  //关闭cursor，避免内存泄露
                }
            }
            mShowModels.addAll(record_result);
            setRecyclerView(view);
        }

    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case PERMS_REQUEST_CODE:
                //获取cursor对象
                boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (storageAccepted){
                    // TODO: Consider calling
                    ArrayList<RecordModel> record_result = new ArrayList<RecordModel>();;
                    ContentResolver resolver = getContext().getContentResolver();
                    Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, // 查询通话记录的URI
                            new String[]{CallLog.Calls.CACHED_NAME// 通话记录的联系人
                                    , CallLog.Calls.NUMBER// 通话记录的电话号码
                                    , CallLog.Calls.DATE// 通话记录的日期
                                    , CallLog.Calls.TYPE// 通话类型
                                    , CallLog.Calls.GEOCODED_LOCATION}
                            , null, null, CallLog.Calls.DEFAULT_SORT_ORDER// 按照时间逆序排列，最近打的最先显示
                    );
                    Log.v("CursorSize", String.valueOf(cursor.getCount()));
                    if (cursor != null) {
                        try {
                            while (cursor.moveToNext()) {
                                String date = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
                                String place = cursor.getString(cursor.getColumnIndex(CallLog.Calls.GEOCODED_LOCATION));
                                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                                String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                                String type = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));
                                DateFormat CurDay = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                String Day_date = CurDay.format(new Date(Long.parseLong(date)));
                                DateFormat CurTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                                String Time_date = CurTime.format(new Date(Long.parseLong(date)));
                                RecordModel recordModel = new RecordModel(Time_date, Day_date, place, number, name, type);
                                record_result.add(recordModel);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            cursor.close();  //关闭cursor，避免内存泄露
                        }
                    }
                    mShowModels.addAll(record_result);
                    setRecyclerView(view);
                    Log.v("ModelSize", String.valueOf(mShowModels.size()));
                }
                break;
        }
    }
}
