package com.padcmyanmar.ted.data.models;


import com.padcmyanmar.ted.network.HttpUrlConnectionDataAgentImpl;
import com.padcmyanmar.ted.network.TalksDataAgent;

public class TalksModel {
    private static TalksModel objInstance;
    private TalksDataAgent mDataAgent;

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private TalksModel() {
        mDataAgent = HttpUrlConnectionDataAgentImpl.getObjInstance();
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
}
