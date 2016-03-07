package com.singh.harsukh.redder.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.singh.harsukh.redder.BuildConfig;
import com.singh.harsukh.redder.model.Listing;
import com.singh.harsukh.redder.model.Reddit.RedditListing;
import com.singh.harsukh.redder.model.Reddit.RedditObject;
import com.singh.harsukh.redder.model.Reddit.RedditResponse;
import com.singh.harsukh.redder.model.Thing;

import org.joda.time.DateTime;

import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Henry on 3/4/2016.
 */
public interface RedditService {

    /**
     * Retrieves a Listing object containing a List of Children that represent
     * the top rated posts of the selected section
     * @param subreddit the section to be queried
     * @return Top rated t3 things tied to the t5 parameter
     */
    @GET("r/{subreddit}/.json")
    Call<Listing> getPostsFromSubreddit(
            @Path("subreddit") String subreddit
    );

    /**
     * Same as getPostsFromSubreddit, this one using a different model class
     * that picks up multimedia. A merge of both model classes to be considered
     * @param subreddit the t5 to be queried
     * @return top rated t3 things tied to the t5 param
     */
    @GET("r/{subreddit}.json")
    Call<Thing> getPostsFromSubredditWithMedia(
            @Path("subreddit") String subreddit
    );

    @GET("r/{subreddit}.json")
    Call<RedditResponse<RedditListing>> getSubreddit(
            @Path("subreddit") String subreddit
    );

    /**
     * Retrieves the comments tree for the passed article
     * @param nameOfSubreddit must be the full name prefix_id (i.e. t5_2cneq)
     * @param articleID field "id" of the ChildrenDataEntity class (i.e. 492viu)
     * @return List of two Listing objects, first one is a t3 representing the
     * post's head section. Second element on the list contains t1 elements only,
     * in other words, the comments attached to the post
     */
    @GET("r/{subreddit}/comments/{articleID}.json")
    Call<List<Listing>> getCommentsFromPost(
            @Path("subreddit") String nameOfSubreddit,
            @Path("articleID") String articleID
    );

    /**
     * Fetches a specific comment. This endpoint can be used to draw focus on a single t1
     * @param name format (full name, not just id. Sample: t1_d0ooweb )
     * @return t1
     */
    @GET("api/info.json")
    Call<Listing> fetchComment(
            @Query("id") String name
    );

    /**
     * Uses a search param to query a specified subreddit
     * @param subreddit t5 thing
     * @param queryParam search query
     * @return t3 things
     */
    @GET("r/{subreddit}/search.json")
    Call<Thing> searchListingsWithQueryParam(
            @Path("subreddit") String subreddit,
            @Query("q") String queryParam
    );

    /**
     *
     * @param queryParam
     * @param sortOrder
     * @return
     */
    @GET("r/{subreddit}/search.json")
    Call<Listing> searchListingsWithQueryParamAndSort(
            @Query("q") String queryParam,
            @Query("sort") String sortOrder
    );

    class Implementation{

        public static RedditService get(){
            return getBuilder()
                    .build()
                    .create(RedditService.class);
        }

        static Retrofit.Builder getBuilder(){
            return new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_REDDIT_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()));
        }

        private static Gson getGson() {
            return new GsonBuilder()
                    .registerTypeAdapter(RedditObject.class, new RedditObjectDeserializer())
                    .registerTypeAdapter(DateTime.class, new DateTimeDeserializer())
                    .create();
        }
    }

}
