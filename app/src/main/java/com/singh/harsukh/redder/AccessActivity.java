package com.singh.harsukh.redder;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.singh.harsukh.redder.model.RedditRCModel;

import org.json.JSONException;

import java.util.UUID;

public class AccessActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "ReMLibe7JwFH0Q";
    private static final String CLIENT_SECRET ="";
    private static final String REDIRECT_URI="https://google.com";
    private static final String GRANT_TYPE="https://oauth.reddit.com/grants/installed_client";
    private static final String GRANT_TYPE2="authorization_code";
    private static final String TOKEN_URL ="access_token";
    private static final String OAUTH_URL ="https://www.reddit.com/api/v1/"+TOKEN_URL;
    private static final String OAUTH_SCOPE="mysubreddits";
    private static final String DURATION = "permanent";
    private static final String DEVICE_ID = UUID.randomUUID().toString();
    private static String ACCESS_TOKEN = null;
    private static String REFRESH_TOKEN = null;
    private WebView mWebView;
    private WebViewClient mWebViewClient = new WebViewClient(){
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if(url != null && url.startsWith(REDIRECT_URI)) //on redirect we want to do this
            {
                mWebView.stopLoading();
                mWebView.setVisibility(View.INVISIBLE);
                Uri uri = Uri.parse(url); //the uri has stuff attached to it that we want to check see reddit OAuth2 docs
                System.out.println(url);
                try {
                    String error  = uri.getQueryParameter("error"); //check if an error exists in the redirect
                    //if an error exists throw a new Toast message
                    if(!error.equals("null")) {
                        Toast.makeText(getApplicationContext(), "The user could not be verified; error value: " + error, Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                catch(NullPointerException e)
                {
                    e.printStackTrace();
                }
                try
                {
                    final String code = uri.getQueryParameter("code");
                    System.out.println("code: " + code);
                    //now make the POST request
                    RedditRCModel model = new RedditRCModel();
                    model.getToken(OAUTH_URL, GRANT_TYPE2, DEVICE_ID, CLIENT_ID,
                            CLIENT_SECRET, REDIRECT_URI, code);
                    TokenThread thread = new TokenThread();
                    thread.start();
                    Log.e("AccessActivity", "TOKEN IS NULL");
                }
                catch(NullPointerException f)
                {
                    f.printStackTrace();
                    finish(); //at this point nothing exists here so something in this methodology is wrong
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public static Intent getIntent(Context context)
    {
        return new Intent(context, AccessActivity.class);
    }

    //get token from http service
    public static void setToken(String token)
    {
        ACCESS_TOKEN = token;
    }

    //get token from http service
    public static void setRefresh(String token)
    {
        REFRESH_TOKEN = token;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
        mWebView = (WebView) findViewById(R.id.webview); //get access to the webview
        mWebView.clearCache(true); //clear the cache
        mWebView.getSettings().setJavaScriptEnabled(true);//enable javascript
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(true);
        mWebView.setWebViewClient(mWebViewClient);

        startAuthorize();
    }

    private void startAuthorize()
    {
        (new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
        /*
        This is the authorization url that is needed
        https://www.reddit.com/api/v1/authorize?client_id=CLIENT_ID&response_type=TYPE&
    state=RANDOM_STRING&redirect_uri=URI&duration=DURATION&scope=SCOPE_STRING
         */
                String RANDOM_STRING = "hgjnklmkiuy";
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("https")
                        .authority("www.reddit.com")
                        .appendPath("api")
                        .appendPath("v1")
                        .appendPath("authorize")
                        .appendQueryParameter("client_id", CLIENT_ID)
                        .appendQueryParameter("response_type", "code")
                        .appendQueryParameter("state", RANDOM_STRING);
                String url= builder.build().toString()+"&redirect_uri=https://google.com&duration="+DURATION+"&scope="+OAUTH_SCOPE;
                System.out.println(url);
                return url;
            }

            @Override
            protected void onPostExecute(String url) {
                mWebView.loadUrl(url);
            }
        }).execute();
    }

    private  void finishActivity(String token, String refresh)
    {
        Intent intent = new Intent();
        intent.putExtra("token", token);
        intent.putExtra("refresh", refresh);
        setResult(RESULT_OK, intent);
        System.out.println(token);
        this.finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) //disable orientation changing
    {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private class TokenThread extends Thread
    {
        @Override
        public void run() {
            while(ACCESS_TOKEN == null && REFRESH_TOKEN == null)
            {
                synchronized (this) {
                    try {
                        wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            finishActivity(ACCESS_TOKEN, REFRESH_TOKEN);
        }
    }

}
