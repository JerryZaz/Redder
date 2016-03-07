package com.singh.harsukh.redder.data;

import com.singh.harsukh.redder.model.Reddit.RedditComment;
import com.singh.harsukh.redder.model.Reddit.RedditLink;
import com.singh.harsukh.redder.model.Reddit.RedditListing;
import com.singh.harsukh.redder.model.Reddit.RedditObject;
import com.singh.harsukh.redder.model.Reddit.RedditResponse;

import junit.framework.TestCase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Henry on 3/6/2016.
 */
public class RedditServiceTest extends TestCase {

    public void testGetSubreddit() throws Exception {
        RedditService service = RedditService.Implementation.get();
        Call<RedditResponse<RedditListing>> call = service.getSubreddit("android");
        Response<RedditResponse<RedditListing>> response = call.execute();
        assertTrue(response.isSuccess());

        RedditResponse<RedditListing> decodedResponse = response.body();
        List<RedditObject> children = decodedResponse.getData().getChildren();
        for(RedditObject child : children){
            RedditLink link = (RedditLink) child;
            System.out.println(link.getTitle());
            testGetComments(link.getSubreddit(), link.getId());
        }
    }

    public void testGetComments(String subreddit, String id) throws Exception {
        Call<List<RedditResponse<RedditListing>>> call = RedditService.Implementation.get()
                .getComments(subreddit, id);
        Response<List<RedditResponse<RedditListing>>> response = call.execute();
        assertTrue(response.isSuccess());
        List<RedditResponse<RedditListing>> decodedResponse = response.body();
        List<RedditObject> children = decodedResponse.get(1).getData().getChildren();

        System.out.println("----- COMMENTS -----");
        for(RedditObject child : children){
            if(child instanceof RedditComment) {
                RedditComment comment = (RedditComment) child;
                System.out.println(comment.getBody());
            }
        }
        System.out.println("----- END -----");
    }
}