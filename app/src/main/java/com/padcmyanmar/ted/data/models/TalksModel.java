package com.padcmyanmar.ted.data.models;


import com.padcmyanmar.ted.data.vos.TalksVO;
import com.padcmyanmar.ted.events.SuccessGetTalksEvent;
import com.padcmyanmar.ted.network.HttpUrlConnectionDataAgentImpl;
import com.padcmyanmar.ted.network.OkHttpDataAgentImpl;
import com.padcmyanmar.ted.network.RetrofitDataAgentImpl;
import com.padcmyanmar.ted.network.TalksDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;

public class TalksModel {
    private static TalksModel objInstance;
    private TalksDataAgent mDataAgent;
    private Map<Integer, TalksVO> mTalksMap;

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private TalksModel() {
        //mDataAgent = HttpUrlConnectionDataAgentImpl.getObjInstance();
        //mDataAgent = OkHttpDataAgentImpl.getObjInstance();
        mDataAgent = RetrofitDataAgentImpl.getObjInstance();
        mTalksMap = new HashMap<>();

        EventBus.getDefault().register(this);
    }

    public static TalksModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new TalksModel();
        }
        return objInstance;
    }

    public void loadTalksList() {
        mDataAgent.loadTalksList(1, DUMMY_ACCESS_TOKEN);
    }

    public TalksVO getTalksById(int id) {
        return mTalksMap.get(id);
    }

    public List<TalksVO> getAllTalks() {
        return new ArrayList<>(mTalksMap.values());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetTalks(SuccessGetTalksEvent event) {
        for (TalksVO talks : event.getTalks()) {
            mTalksMap.put(talks.getTalkId(), talks);
        }
    }
}
