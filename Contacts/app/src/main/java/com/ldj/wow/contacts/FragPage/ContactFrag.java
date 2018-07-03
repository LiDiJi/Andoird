package com.ldj.wow.contacts.FragPage;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

import android.os.Vibrator;
import android.app.NotificationManager;
import android.content.Intent;

import com.ldj.wow.contacts.ContactModel;
import com.ldj.wow.contacts.Contacter.AddContacter;
import com.ldj.wow.contacts.Contacter.ContacterShow;
import com.ldj.wow.contacts.ContactsAdapter;
import com.ldj.wow.contacts.OnCallClickListener;
import com.ldj.wow.contacts.OnInfoClickListener;
import com.ldj.wow.contacts.OnMsgClickListener;
import com.ldj.wow.contacts.PinnedHeaderDecoration;
import com.ldj.wow.contacts.R;
import com.ldj.wow.contacts.Search.SearchEditText;
import com.ldj.wow.contacts.Search.Trans2PinYinUtil;

import cc.solart.wave.WaveSideBarView;

/**
 * Created by wowsc on 2018/7/1.
 */

public class ContactFrag extends Fragment {
    private List<ContactModel> mContactModels;
    private List<ContactModel> mShowModels;
    private RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;
    private WaveSideBarView mWaveSideBarView;
    private SearchEditText mSearch;
    private ImageView mute_mode;
    int sleep_state = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.contacts_page, container,false);
        initData();
        setRecyclerView(view);
        setWaveSideBarView(view);
        setAddFab(view);
        setSearchView(view);
        setAudioImageView(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    private void initData() {
        mContactModels = new ArrayList<>();
        mShowModels = new ArrayList<>();
        mContactModels.addAll(ContactModel.getContacts());
        mShowModels.addAll(mContactModels);
    }

    private void setRecyclerView(View view){
        mAdapter = new ContactsAdapter(mShowModels);
        // RecyclerView设置相关
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnInfoClickListener() {
            @Override
            public void onInfoClick(View view, int postion) {
                Activity curActivity = getActivity();
                Intent intent = new Intent(curActivity, ContacterShow.class);
                startActivity(intent);
                //curActivity.finish();
            }
        });
        mAdapter.setOnCallClickListener(new OnCallClickListener() {
            @Override
            public void onCallClick(View view, int postion) {
                Activity curActivity = getActivity();
                Intent intent = new Intent(curActivity, ContacterShow.class);
                startActivity(intent);
            }
        });
        mAdapter.setOnMsgClickListener(new OnMsgClickListener() {
            @Override
            public void onMsgClick(View view, int postion) {
                Activity curActivity = getActivity();
                Intent intent = new Intent(curActivity, ContacterShow.class);
                startActivity(intent);
            }
        });
    }

    private void setWaveSideBarView(View view){
        mWaveSideBarView = (WaveSideBarView) view.findViewById(R.id.side_view);
        mWaveSideBarView.setOnSelectIndexItemListener(new WaveSideBarView.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String letter) {
                for (int i=0; i<mContactModels.size(); i++) {
                    if (mContactModels.get(i).getIndex().equals(letter)) {
                        ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
    }

    private void setAddFab(View view){
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity curActivity = getActivity();
                Intent intent = new Intent(curActivity, AddContacter.class);
                startActivity(intent);
                //curActivity.finish();
            }
        });
    }

    private void setAudioImageView(View view){

        AudioManager mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        NotificationManager notificationManager = (NotificationManager)getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
                && !notificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            getContext().startActivity(intent);
            return;
        }

        mute_mode = (ImageView) view.findViewById(R.id.mute_audio);
                mute_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sleep_state == 0){
                    AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
                    if(audioManager != null){
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                        audioManager.getStreamVolume(AudioManager.STREAM_RING);

                    }
                    sleep_state = 1;
                    Vibrator vibrator = (Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.cancel();
                    Snackbar.make(view, "open", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    mute_mode.setImageResource(R.drawable.ic_alarm_on_light_green_700_36dp);
                }
                else {
                    AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
                    if(audioManager != null){
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        audioManager.getStreamVolume(AudioManager.STREAM_RING);

                    }
                    audioManager.adjustStreamVolume(AudioManager.STREAM_RING,AudioManager.ADJUST_RAISE,
                            AudioManager.FX_FOCUS_NAVIGATION_UP);
                    sleep_state = 0;
                    Vibrator vibrator = (Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(1000);
                    Snackbar.make(view, "close", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    mute_mode.setImageResource(R.drawable.ic_alarm_off_grey_600_36dp);
                }
            }
        });
    }

    private void setSearchView(View view){
        mSearch = (SearchEditText) view.findViewById(R.id.search_go);
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSearch.clearFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
                mShowModels.clear();
                for (ContactModel model : mContactModels) {
                    String str = Trans2PinYinUtil.trans2PinYin(model.getName());
                    if (str.contains(s.toString()) || model.getName().contains(s.toString())) {
                        mShowModels.add(model);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
