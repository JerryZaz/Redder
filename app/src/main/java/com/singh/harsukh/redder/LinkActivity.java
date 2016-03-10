package com.singh.harsukh.redder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class LinkActivity extends AppCompatActivity {
    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        mImage = (ImageView) findViewById(R.id.image_pic);

        Intent intent = getIntent();
        String url = intent.getStringExtra("image");

        Picasso.with(getBaseContext())
                .load(url)
                .fit()
                .centerCrop()
                .into(mImage);
    }
}