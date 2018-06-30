package com.ldj.wow.contacts;

import android.graphics.Color;
import android.nfc.Tag;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cc.solart.wave.WaveSideBarView;
public class MainActivity extends AppCompatActivity {
    public WaveSideBarView mWave;
    public RecyclerView mRecView;
    public SearchView mSearchView;
    private LinearLayout lin_tab_cont;
    private LinearLayout lin_tab_cal;
    private LinearLayout lin_tab_rec;
    private TextView cont_tab_text;
    private TextView cal_tab_text;
    private TextView rec_tab_text;
    private ImageView tab_line_left;
    private ImageView tab_line_mid;
    private ImageView tab_line_right;
    private List<ContactModel> mContactModels;
    private List<ContactModel> mShowModels;
    private RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWave = (WaveSideBarView) findViewById(R.id.side_view);
        initData();
        setRecyclerView();
        setSelected();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "onClick", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mSearchView = (SearchView) findViewById(R.id.searchView);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query)) {
                    Toast toast = Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "空搜索字符串", Toast.LENGTH_SHORT);
                    toast.show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (mSearchView != null) {
                    // 得到输入管理对象
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        // 这将让键盘在所有的情况下都被隐藏，但是一般我们在点击搜索按钮后，输入法都会乖乖的自动隐藏的。
                        imm.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0); // 输入法如果是显示状态，那么就隐藏输入法
                    }
                    mSearchView.clearFocus(); // 不获取焦点
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setSelected(){
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
                Toast toast = Toast.makeText(getApplicationContext(), "通讯记录", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void initData() {
        mContactModels = new ArrayList<>();
        mShowModels = new ArrayList<>();
        mContactModels.addAll(ContactModel.getContacts());
        mShowModels.addAll(mContactModels);
    }

    private void setRecyclerView(){
        mAdapter = new ContactsAdapter(mShowModels);
        // RecyclerView设置相关
        mRecyclerView = (RecyclerView) findViewById(R.id.recView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setWaresier(){

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mContactModels != null){
            mContactModels.clear();
            mContactModels = null;
        }
    }
}
