package com.padcmyanmar.ted.network;

import com.padcmyanmar.ted.data.vos.TalksVO;
import com.padcmyanmar.ted.network.responses.GetTalksResponse;
import com.padcmyanmar.ted.utils.TEDTalksConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by User on 6/21/2018.
 */

public interface TalksApi {
    @FormUrlEncoded
    @POST(TEDTalksConstants.GET_TED_TALKS)
    Call<GetTalksResponse> loadTalksList(@Field(TEDTalksConstants.PARAM_ACCESS_TOKEN)String accessToken,
                                         @Field(TEDTalksConstants.PARAM_PAGE)int page);
}
