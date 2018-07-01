package com.ldj.wow.contacts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
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
        init();
        //设置状态栏随系统自动变化 （开源库：https://github.com/msdx/status-bar-compat）
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#FFFFFF"),true);
    }



    private void setSelectedTab(){
        lin_tab_cont = (LinearLayout) findViewById(R.id.lin_tab_cont);
        lin_tab_cal = (LinearLayout) findViewById(R.id.lin_tab_cal);
        lin_tab_rec = (LinearLayout) findViewById(R.id.lin_tab_rec);
        cont_tab_text = (TextView) findViewById(R.id.cont_tv);
        cal_tab_text = (TextView) findViewById(R.id.cal_tv);
        rec_tab_text = (TextView) findViewById(R.id.rec_tv);
        tab_line_left = (ImageView) findViewById(R.id.tab_line_left);
        tab_line_mid = (ImageView) findViewById(R.id.tab_line_middle);
        tab_line_right = (ImageView) findViewById(R.id.tab_line_right);
        lin_tab_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cont_tab_text.setTextColor(Color.parseColor("#00CD66"));
                cal_tab_text.setTextColor(Color.parseColor("#000000"));
                rec_tab_text.setTextColor(Color.parseColor("#000000"));
                tab_line_left.setVisibility(View.VISIBLE);
                tab_line_mid.setVisibility(View.INVISIBLE);
                tab_line_right.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(getApplicationContext(), "通讯录", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        lin_tab_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cont_tab_text.setTextColor(Color.parseColor("#000000"));
                cal_tab_text.setTextColor(Color.parseColor("#00CD66"));
                rec_tab_text.setTextColor(Color.parseColor("#000000"));
                tab_line_left.setVisibility(View.INVISIBLE);
                tab_line_mid.setVisibility(View.INVISIBLE);
                tab_line_right.setVisibility(View.VISIBLE);
                Toast toast = Toast.makeText(getApplicationContext(), "日历", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        lin_tab_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cont_tab_text.setTextColor(Color.parseColor("#000000"));
                cal_tab_text.setTextColor(Color.parseColor("#000000"));
                rec_tab_text.setTextColor(Color.parseColor("#00CD66"));
                tab_line_left.setVisibility(View.INVISIBLE);
                tab_line_mid.setVisibility(View.VISIBLE);
                tab_line_right.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(getApplicationContext(), "通话记录", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
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

            }
        });
    }
}
