package com.hades.mvvmdatabindinglivedata.data.source.api.service;

import com.hades.mvvmdatabindinglivedata.data.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Hades on 9/10/2018.
 */
public interface GithubService {

    @GET("users/{user}/repos")
    Call<List<Project>> getListProjects(@Path("user") String user);

    @GET("/repos/{user}/{reponame}")
    Call<Project> getProjectDetails(@Path("user") String user, @Path("reponame") String projectName);
}
