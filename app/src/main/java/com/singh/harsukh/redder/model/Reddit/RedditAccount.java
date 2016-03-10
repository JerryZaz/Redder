package com.singh.harsukh.redder.model.Reddit;

/**
 * Created by Henry on 3/6/2016.
 */
@SuppressWarnings("unused")
public class RedditAccount extends RedditObject {
    private int comment_karma;
    private boolean has_mail;
    private boolean has_mod_mail;
    private boolean has_verified_email;
    private String id;
    private boolean is_friend;
    private boolean is_gold;
    private boolean is_mod;
    private int link_karma;
    private String modhash;
    private String name;
    private boolean over_18;
    public RedditAccount(int comment_karma, boolean has_mail, boolean has_mod_mail, boolean has_verified_email,
                         String id, boolean is_friend, boolean is_gold, boolean is_mod, int link_karma,
                         String modhash, String name, boolean over_18) {
        this.comment_karma = comment_karma;
        this.has_mail = has_mail;
        this.has_mod_mail = has_mod_mail;
        this.has_verified_email = has_verified_email;
        this.id = id;
        this.is_friend = is_friend;
        this.is_gold = is_gold;
        this.is_mod = is_mod;
        this.link_karma = link_karma;
        this.modhash = modhash;
        this.name = name;
        this.over_18 = over_18;
    }

    public int getComment_karma() {
        return comment_karma;
    }

    public void setComment_karma(int comment_karma) {
        this.comment_karma = comment_karma;
    }

    public boolean isHas_mail() {
        return has_mail;
    }

    public void setHas_mail(boolean has_mail) {
        this.has_mail = has_mail;
    }

    public boolean isHas_mod_mail() {
        return has_mod_mail;
    }

    public void setHas_mod_mail(boolean has_mod_mail) {
        this.has_mod_mail = has_mod_mail;
    }

    public boolean isHas_verified_email() {
        return has_verified_email;
    }

    public void setHas_verified_email(boolean has_verified_email) {
        this.has_verified_email = has_verified_email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean is_friend() {
        return is_friend;
    }

    public void setIs_friend(boolean is_friend) {
        this.is_friend = is_friend;
    }

    public boolean is_gold() {
        return is_gold;
    }

    public void setIs_gold(boolean is_gold) {
        this.is_gold = is_gold;
    }

    public boolean is_mod() {
        return is_mod;
    }

    public void setIs_mod(boolean is_mod) {
        this.is_mod = is_mod;
    }

    public int getLink_karma() {
        return link_karma;
    }

    public void setLink_karma(int link_karma) {
        this.link_karma = link_karma;
    }

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOver_18() {
        return over_18;
    }

    public void setOver_18(boolean over_18) {
        this.over_18 = over_18;
    }
}
