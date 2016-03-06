package com.singh.harsukh.redder.data;

import com.singh.harsukh.redder.BuildConfig;
import com.singh.harsukh.redder.model.Listing;
import com.singh.harsukh.redder.model.Listing.DataEntity.ChildrenEntity;
import com.singh.harsukh.redder.model.ThingWithMedia;
import com.singh.harsukh.redder.model.ThingWithMedia.DataEntity.ChildrenEntity.ChildrenDataEntity.PreviewEntity.ImagesEntity;

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
        //testGetPostsFromSubreddit("android");
        try {
            testSearchListingsWithQueryParam("cat");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

            List<Listing> decodedResponse = response.body();

            List<ChildrenEntity> children = decodedResponse.get(1).getData().getChildren();
            for (ChildrenEntity child : children) {
                try {
                    // t1 is a comment Thing so, testing if the result is a comment
                    assertEquals("t1", child.getKind());
                    assertFalse("Child's body was empty", child.getData().getSelftext() == null);

                } catch (AssertionError e) {
                    System.err.println("Child kind was: " + child.getKind());
                    System.err.println("Child's content was : " + child.getData().getSelftext());
                    System.err.println(buildCommentUrl(child.getData().getName()));

                }
            }
            System.out.println("Number of comments: " + children.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String buildCommentUrl(String name) {
        return BuildConfig.BASE_REDDIT_URL + "/api/info.json?id=" + name;
    }

    public void testSearchListingsWithQueryParam(String searchQuery) throws Exception {

        String subreddit = "pics";

        Retrofit retrofit = BaseRetrofitImplementation.initRetrofit();
        RedditAPI redditAPI = retrofit.create(RedditAPI.class);
        Call<ThingWithMedia> call = redditAPI.searchListingsWithQueryParam(subreddit, searchQuery);
        Response<ThingWithMedia> response = call.execute();

        assertTrue(response.isSuccess());
        ThingWithMedia thing = response.body();
        for (ThingWithMedia.DataEntity.ChildrenEntity child : thing.getData().getChildren()) {
            //System.out.println(child.getData().getSelftext());

            if(child.getData().getPreview() != null) {
                for (ImagesEntity images : child.getData().getPreview().getImages()) {
                    System.out.println(images.getSource().getUrl());
                }
            }
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