package com.ldj.wow.contacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.ldj.wow.contacts.FragPage.CalendarFrag;
import com.ldj.wow.contacts.FragPage.ContactFrag;
import com.ldj.wow.contacts.FragPage.RecordFrag;
import com.necer.ncalendar.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private LinearLayout lin_tab_cont;
    private LinearLayout lin_tab_cal;
    private LinearLayout lin_tab_rec;
    private TextView cont_tab_text;
    private TextView cal_tab_text;
    private TextView rec_tab_text;
    private ImageView tab_line_left;
    private ImageView tab_line_mid;
    private ImageView tab_line_right;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private ViewPager mPageVp;
    private RecordFrag mRecordFg;
    private CalendarFrag mCalendarFg;
    private ContactFrag mContactFg;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPageVp = (ViewPager) findViewById(R.id.main_vp);

        cont_tab_text = (TextView) findViewById(R.id.cont_tv);
        cal_tab_text = (TextView) findViewById(R.id.cal_tv);
        rec_tab_text = (TextView) findViewById(R.id.rec_tv);
        tab_line_left = (ImageView) findViewById(R.id.tab_line_left);
        tab_line_mid = (ImageView) findViewById(R.id.tab_line_middle);
        tab_line_right = (ImageView) findViewById(R.id.tab_line_right);
        init();
        //设置状态栏随系统自动变化 （开源库：https://github.com/msdx/status-bar-compat）
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#FFFFFF"),true);
    }



    private void init() {
        mContactFg = new ContactFrag();
        mCalendarFg = new CalendarFrag();
        mRecordFg = new RecordFrag();

        mFragmentList.add(mContactFg);
        mFragmentList.add(mRecordFg);
        mFragmentList.add(mCalendarFg);

        mFragmentAdapter = new FragmentAdapter(
                this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

        mPageVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int state) {

            }

            @Override
            public void onPageScrolled(int position, float offset, int offsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        cont_tab_text.setTextColor(Color.parseColor("#00CD66"));
                        cal_tab_text.setTextColor(Color.parseColor("#000000"));
                        rec_tab_text.setTextColor(Color.parseColor("#000000"));
                        tab_line_left.setVisibility(View.VISIBLE);
                        tab_line_mid.setVisibility(View.INVISIBLE);
                        tab_line_right.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        cont_tab_text.setTextColor(Color.parseColor("#000000"));
                        cal_tab_text.setTextColor(Color.parseColor("#00CD66"));
                        rec_tab_text.setTextColor(Color.parseColor("#000000"));
                        tab_line_left.setVisibility(View.INVISIBLE);
                        tab_line_mid.setVisibility(View.INVISIBLE);
                        tab_line_right.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        cont_tab_text.setTextColor(Color.parseColor("#000000"));
                        cal_tab_text.setTextColor(Color.parseColor("#000000"));
                        rec_tab_text.setTextColor(Color.parseColor("#00CD66"));
                        tab_line_left.setVisibility(View.INVISIBLE);
                        tab_line_mid.setVisibility(View.VISIBLE);
                        tab_line_right.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });
    }
}
