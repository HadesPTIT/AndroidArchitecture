package com.hades.mvvmdatabindinglivedata.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hades on 9/10/2018.
 */
public class User {

    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("node_id")
    @Expose
    public String nodeId;
    @SerializedName("avatar_url")
    @Expose
    public String avatarUrl;
    @SerializedName("gravatar_id")
    @Expose
    public String gravatarId;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("html_url")
    @Expose
    public String htmlUrl;
    @SerializedName("followers_url")
    @Expose
    public String followersUrl;
    @SerializedName("following_url")
    @Expose
    public String followingUrl;
    @SerializedName("gists_url")
    @Expose
    public String gistsUrl;
    @SerializedName("starred_url")
    @Expose
    public String starredUrl;
    @SerializedName("subscriptions_url")
    @Expose
    public String subscriptionsUrl;
    @SerializedName("organizations_url")
    @Expose
    public String organizationsUrl;
    @SerializedName("repos_url")
    @Expose
    public String reposUrl;
    @SerializedName("events_url")
    @Expose
    public String eventsUrl;
    @SerializedName("received_events_url")
    @Expose
    public String receivedEventsUrl;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("site_admin")
    @Expose
    public boolean siteAdmin;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("company")
    @Expose
    public String company;
    @SerializedName("blog")
    @Expose
    public String blog;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("hireable")
    @Expose
    public boolean hireable;
    @SerializedName("bio")
    @Expose
    public String bio;
    @SerializedName("public_repos")
    @Expose
    public int publicRepos;
    @SerializedName("public_gists")
    @Expose
    public int publicGists;
    @SerializedName("followers")
    @Expose
    public int followers;
    @SerializedName("following")
    @Expose
    public int following;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("total_private_repos")
    @Expose
    public int totalPrivateRepos;
    @SerializedName("owned_private_repos")
    @Expose
    public int ownedPrivateRepos;
    @SerializedName("private_gists")
    @Expose
    public int privateGists;
    @SerializedName("disk_usage")
    @Expose
    public int diskUsage;
    @SerializedName("collaborators")
    @Expose
    public int collaborators;
    @SerializedName("two_factor_authentication")
    @Expose
    public boolean twoFactorAuthentication;
}
