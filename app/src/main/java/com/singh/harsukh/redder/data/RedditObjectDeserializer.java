package com.singh.harsukh.redder.data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.singh.harsukh.redder.model.Reddit.RedditObject;
import com.singh.harsukh.redder.model.Reddit.RedditObjectWrapper;

import java.lang.reflect.Type;

/**
 * Created by Henry on 3/6/2016.
 */
public class RedditObjectDeserializer implements JsonDeserializer<RedditObject> {

    public static final String TAG = RedditObjectDeserializer.class.getSimpleName();
    public static final String KIND = "kind";

    @Override
    public RedditObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if(!json.isJsonObject()){
            return null;
        }
        try {
            RedditObjectWrapper wrapper = new Gson().fromJson(json, RedditObjectWrapper.class);
            return context.deserialize(wrapper.getData(), wrapper.getKind().getDerivedClass());
        } catch (JsonParseException e){
            Log.e(TAG, "Failed to deserialize");
            return null;
        }

    }
}
