package com.singh.harsukh.redder.retrofit;

import com.singh.harsukh.redder.BuildConfig;
import com.singh.harsukh.redder.data.RedditAPI;
import com.singh.harsukh.redder.model.Listing;

import junit.framework.TestCase;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Henry on 3/4/2016.
 */
public class SubredditRetroTest extends TestCase {

    public void testServerResponse(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_REDDIT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        RedditAPI redditAPI = retrofit.create(RedditAPI.class);

        Call<Listing> call = redditAPI.getData("android");

        try {
            Response<Listing> response = call.execute();

            System.out.println("Response status code: " + response.code());

            if (!response.isSuccess()) {
                try {
                    System.out.println(response.errorBody().string());
                } catch (IOException ignored) {
                }
                return;
            }

            Listing decodedResponse = response.body();
            if(decodedResponse != null){
                System.out.println("Response (contains request infos):");
                System.out.println("Response kind: " + decodedResponse.getKind());
                System.out.println("");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        /*call.enqueue(new Callback<Listing>() {
            @Override
            public void onResponse(Response<Listing> response) {
                System.out.println("Response status code: " + response.code());

                if (!response.isSuccess()) {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                    }
                    return;
                }

                Listing decodedResponse = response.body();
                if(decodedResponse != null){
                    System.out.println("Response (contains request infos):");
                    System.out.println("Response kind: " + decodedResponse.getKind());
                    System.out.println("");
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });*/
    }

}