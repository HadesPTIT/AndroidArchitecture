package com.hades.mvvmdatabindinglivedata.data.model;

import android.support.v7.util.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Hades on 9/10/2018.
 */
public class Project {

    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("full_name")
    @Expose
    public String full_name;
    @SerializedName("owner")
    @Expose
    public User owner;
    @SerializedName("html_url")
    @Expose
    public String html_url;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("created_at")
    @Expose
    public Date created_at;
    @SerializedName("updated_at")
    @Expose
    public Date updated_at;
    @SerializedName("pushed_at")
    @Expose
    public Date pushed_at;
    @SerializedName("git_url")
    @Expose
    public String git_url;
    @SerializedName("ssh_url")
    @Expose
    public String ssh_url;
    @SerializedName("clone_url")
    @Expose
    public String clone_url;
    @SerializedName("language")
    @Expose
    public String language;
    @SerializedName("open_issues")
    @Expose
    public int open_issues;
    @SerializedName("watchers")
    @Expose
    public int watchers;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    /**
     * DiffUtil callback
     */
    public static DiffUtil.ItemCallback<Project> DIFF_CALLBACK = new DiffUtil.ItemCallback<Project>() {
        @Override
        public boolean areItemsTheSame(Project oldItem, Project newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(Project oldItem, Project newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        Project project = (Project) obj;
        return project.id == this.id;
    }
}
