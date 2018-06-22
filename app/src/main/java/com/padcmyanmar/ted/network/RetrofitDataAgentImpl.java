package com.padcmyanmar.ted.network;


import com.padcmyanmar.ted.data.vos.TalksVO;
import com.padcmyanmar.ted.events.APIErrorEvent;
import com.padcmyanmar.ted.events.SuccessGetTalksEvent;
import com.padcmyanmar.ted.network.responses.GetTalksResponse;
import com.padcmyanmar.ted.utils.TEDTalksConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements TalksDataAgent {

    private static RetrofitDataAgentImpl objInstance;
    private TalksApi mTheApi;

    private RetrofitDataAgentImpl() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TEDTalksConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        mTheApi = retrofit.create(TalksApi.class);
    }

    public static RetrofitDataAgentImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadTalksList(int page, String accessToken) {
        Call<GetTalksResponse> getTalksCall = mTheApi.loadTalksList(accessToken, page);
        getTalksCall.enqueue(new Callback<GetTalksResponse>() {
            @Override
            public void onResponse(Call<GetTalksResponse> call, Response<GetTalksResponse> response) {
                GetTalksResponse talksResponse = response.body();
                if (talksResponse != null && talksResponse.isResponseOk()) {
                    SuccessGetTalksEvent event = new SuccessGetTalksEvent(talksResponse.getTalks());
                    EventBus.getDefault().post(event);
                } else if (talksResponse == null){
                    APIErrorEvent event = new APIErrorEvent("Empty response from network");
                    EventBus.getDefault().post(event);
                }else{
                    APIErrorEvent event = new APIErrorEvent(talksResponse.getMessage());
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(Call<GetTalksResponse> call, Throwable t) {
                APIErrorEvent event  = new APIErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });
    }
}
