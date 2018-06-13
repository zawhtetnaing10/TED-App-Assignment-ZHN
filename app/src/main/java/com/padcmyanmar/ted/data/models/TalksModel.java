package com.padcmyanmar.ted.data.models;


public class TalksModel {
    private static TalksModel objInstance;

    private TalksModel() {

    }

    public static TalksModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new TalksModel();
        }
        return objInstance;
    }

    public void loadTalksList() {

    }
}
