package com.singh.harsukh.redder.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by nano1 on 3/6/2016.
 */
public class Utilities {

    public static final String PERSISTENT = "main.persistent.state";
    public static final String SECTIONS = "main.persistent.state.sections.list";
    public static final String[] SECTIONS_DEFAULT = new String[]{
            "All",
            "AskReddit",
            "Android",
            "books",
            "creepy",
            "Documentaries",
            "Fitness",
            "Food",
            "funny",
            "movies",
            "Music",
            "news",
            "OldSchoolCool",
            "philosophy",
            "sports",
            "television",
            "videos",
            "worldnews"

    };

    public static String getDiff(Date date) {
        Date date2 = new Date();
        long diff = getDateDiff(date, date2, TimeUnit.HOURS);
        if (diff > 24) {
            diff = getDateDiff(date, date2, TimeUnit.DAYS);
            return String.valueOf(diff) + "d";
        } else if (diff < 1) {
            diff = getDateDiff(date, date2, TimeUnit.MINUTES);
            if (diff < 1) {
                diff = getDateDiff(date, date2, TimeUnit.SECONDS);
                return String.valueOf(diff) + "s";
            }
            return String.valueOf(diff) + "m";
        } else {
            return String.valueOf(diff) + "h";
        }
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

}
