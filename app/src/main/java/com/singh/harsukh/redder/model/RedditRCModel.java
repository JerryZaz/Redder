package com.singh.harsukh.redder.model;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.singh.harsukh.redder.AccessActivity;
import com.singh.harsukh.redder.RefreshService;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpRequest;

/**
 * Created by harsukh on 3/6/16.
 */
public class RedditRCModel {


    private static AsyncHttpClient client = new AsyncHttpClient();
    private static String access_token;

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        client.post(url, params, responseHandler);
    }

    private void setToken(String token)
    {
        access_token = token;
    }

    public void getToken(String relativeUrl,String grant_type,String device_id, String CLIENT_ID,
                           String CLIENT_SECRET, String REDIRECT_URI,String code) throws JSONException {
        client.setBasicAuth(CLIENT_ID, CLIENT_SECRET);
        RequestParams requestParams = new RequestParams();
        requestParams.put("code",code);
        requestParams.put("grant_type",grant_type);
        requestParams.put("redirect_uri", REDIRECT_URI);

        post(relativeUrl, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.i("response", response.toString());
                try {
                    String token = response.getString("access_token");
                    String refresh_token = response.getString("refresh_token");
                    AccessActivity.setToken(token);
                    AccessActivity.setRefresh(refresh_token);
                    Log.e("Access_token", token);
                } catch (JSONException j) {
                    j.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("statusCode", "" + statusCode);
            }
        });
    }

    public void refreshToken(String token, String CLIENT_ID, String CLIENT_SECRET)
    {
        client.setBasicAuth(CLIENT_ID, CLIENT_SECRET);
        String relative_url = "https://www.reddit.com/api/v1/access_token";
        String grant_type = "refresh_token";
        RequestParams requestParams = new RequestParams();
        requestParams.put("grant_type",grant_type);
        requestParams.put("refresh_token", token);
        //requestParams.put("redirect_uri","https://google.com");
        post(relative_url, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.i("response", response.toString());
                try {
                    String token = response.getString("access_token");
                    RefreshService.refresh(token); //needs to be changed
                    Log.e("Access_token", token);
                } catch (JSONException j) {
                    j.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("statusCode", "" + statusCode);
            }
        });
    }

}
