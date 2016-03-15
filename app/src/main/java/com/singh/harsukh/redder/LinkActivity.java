package com.singh.harsukh.redder;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.singh.harsukh.redder.fragment.CommentsFragment;
import com.singh.harsukh.redder.fragment.DetailFragment;
import com.singh.harsukh.redder.model.Reddit.RedditLink;

public class LinkActivity extends AppCompatActivity {

    private RedditLink selectedLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        FragmentManager fragmentManager = getFragmentManager();
        Intent intent = getIntent();
        selectedLink = intent.getParcelableExtra("subreddit");

        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        transaction.replace(R.id.link_header_container, DetailFragment.newInstance(null, selectedLink), "newDetail");
        transaction.replace(R.id.link_comments_container, CommentsFragment.newInstance(1, selectedLink), "newCom");
        transaction.commit();
    }
}
