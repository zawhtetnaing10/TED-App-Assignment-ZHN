package com.padcmyanmar.ted.network;


import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.padcmyanmar.ted.events.APIErrorEvent;
import com.padcmyanmar.ted.events.SuccessGetTalksEvent;
import com.padcmyanmar.ted.network.responses.GetTalksResponse;
import com.padcmyanmar.ted.utils.TEDTalksConstants;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HttpUrlConnectionDataAgentImpl implements TalksDataAgent {
    private static HttpUrlConnectionDataAgentImpl objInstance;

    private HttpUrlConnectionDataAgentImpl() {

    }

    public static HttpUrlConnectionDataAgentImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new HttpUrlConnectionDataAgentImpl();
        }
        return objInstance;
    }


    @Override
    public void loadTalksList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                URL url;
                StringBuilder stringBuilder;
                BufferedReader reader = null;

                try {

                    //Create the HttpUrlConnection
                    url = new URL(TEDTalksConstants.API_BASE + TEDTalksConstants.GET_TED_TALKS);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    //Setting the request method
                    connection.setRequestMethod("POST");

                    //Setting the response time out
                    connection.setReadTimeout(15 * 1000);

                    //Specifying the input and ouput
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    //Setting up name and value pairs for params
                    List<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair(TEDTalksConstants.PARAM_ACCESS_TOKEN, accessToken));
                    params.add(new BasicNameValuePair(TEDTalksConstants.PARAM_PAGE, String.valueOf(page)));

                    //Write the parameters form the params to connection
                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    //Connect the connection object
                    connection.connect();

                    //read the response from connection and saving it in the response STring
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    stringBuilder = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }

                    String responseString = stringBuilder.toString();

                    return responseString;

                } catch (Exception e) {
                    Log.e("", e.getMessage());
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson = new Gson();
                GetTalksResponse talksResponse = gson.fromJson(responseString, GetTalksResponse.class);

                if(talksResponse.isResponseOk()){
                    SuccessGetTalksEvent event = new SuccessGetTalksEvent(talksResponse.getTalks());
                    EventBus.getDefault().post(event);
                }else{
                    APIErrorEvent event = new APIErrorEvent(talksResponse.getMessage());
                    EventBus.getDefault().post(event);
                }

            }
        }.execute();
    }


    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (NameValuePair param : params) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode(param.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(param.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
