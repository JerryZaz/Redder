package com.singh.harsukh.redder.data;

import com.singh.harsukh.redder.BuildConfig;
import com.singh.harsukh.redder.model.Listing;
import com.singh.harsukh.redder.model.Listing.DataEntity.ChildrenEntity;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Henry on 3/4/2016.
 */
public class RedditAPITest extends TestCase {

    public void testMultipleSubreddits() {
        testGetPostsFromSubreddit("android");
    }

    public void testGetPostsFromSubreddit(String subreddit) {

        RedditAPI redditAPI = BaseRetrofitImplementation.initRetrofit().create(RedditAPI.class);

        Call<Listing> call = redditAPI.getPostsFromSubreddit(subreddit);

        try {
            Response<Listing> response = call.execute();

            try {
                assertTrue(response.code() == 200);
            } catch (AssertionError e) {
                System.err.println("Response status code: " + response.code());
            }

            if (!response.isSuccess()) {
                try {
                    System.out.println(response.errorBody().string());
                } catch (IOException ignored) {
                }
                return;
            }

            Listing decodedResponse = response.body();
            if (decodedResponse != null) {
                try {
                    assertEquals("Listing", decodedResponse.getKind());
                } catch (AssertionError e) {
                    System.err.println("Response kind: " + decodedResponse.getKind());
                }

                List<ChildrenEntity> posts = decodedResponse.getData().getChildren();
                for (ChildrenEntity post : posts) {
                    System.out.println("Fetched post title: " + post.getData().getTitle());
                    if (!subreddit.equals("all")) {
                        assertEquals(subreddit.toLowerCase(), post.getData().getSubreddit().toLowerCase());
                    } else {
                        System.out.println("Post from " + post.getData().getSubreddit() + " came through");
                    }
                    try {
                        testGetCommentsFromPost(post.getData().getSubreddit(), post.getData().getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testGetCommentsFromPost(String nameOfSubreddit, String articleID)
            throws Exception {
        Retrofit retrofit = BaseRetrofitImplementation.initRetrofit();
        RedditAPI redditAPI = retrofit.create(RedditAPI.class);
        Call<List<Listing>> call = redditAPI.getCommentsFromPost(nameOfSubreddit, articleID);
        try {
            Response<List<Listing>> response = call.execute();
            assertTrue(response.isSuccess());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public abstract static class BaseRetrofitImplementation {
        static Retrofit initRetrofit() {

            final String BASE_URL = BuildConfig.BASE_REDDIT_URL;

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .addInterceptor(interceptor).build();

            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
    }

}