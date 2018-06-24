package com.padcmyanmar.ted.network;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.padcmyanmar.ted.events.APIErrorEvent;
import com.padcmyanmar.ted.events.SuccessGetTalksEvent;
import com.padcmyanmar.ted.network.responses.GetTalksResponse;
import com.padcmyanmar.ted.utils.TEDTalksConstants;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkHttpDataAgentImpl implements TalksDataAgent {

    private static OkHttpDataAgentImpl objInstance;
    private static OkHttpClient mHttpClient;


    private OkHttpDataAgentImpl() {
        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

    }

    public static OkHttpDataAgentImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new OkHttpDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadTalksList(final int page, final String accessToken) {
        NetworkTask networkTask = new NetworkTask(accessToken , page);
        networkTask.execute();
    }

    private static class NetworkTask extends AsyncTask<Void, Void, String> {
        private String mAccessToken;
        private int mPage;

        public NetworkTask(String accessToken, int page) {
            this.mAccessToken = accessToken;
            this.mPage = page;
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestBody formBody = new FormBody.Builder()
                    .add(TEDTalksConstants.PARAM_ACCESS_TOKEN, mAccessToken)
                    .add(TEDTalksConstants.PARAM_PAGE, String.valueOf(mPage))
                    .build();

            Request request = new Request.Builder()
                    .url(TEDTalksConstants.API_BASE + TEDTalksConstants.GET_TED_TALKS)
                    .post(formBody)
                    .build();

            try {
                Response response = mHttpClient.newCall(request).execute();
                if (response != null) {
                    String responseString = response.body().string();
                    return responseString;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String responseString) {
            super.onPostExecute(responseString);
            Gson gson = new Gson();
            GetTalksResponse talksResponse = gson.fromJson(responseString, GetTalksResponse.class);

            if (talksResponse.isResponseOk()) {
                SuccessGetTalksEvent event = new SuccessGetTalksEvent(talksResponse.getTalks());
                EventBus.getDefault().post(event);
            } else {
                APIErrorEvent event = new APIErrorEvent(talksResponse.getMessage());
                EventBus.getDefault().post(event);
            }

        }
    }
}
