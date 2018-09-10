package com.hades.mvvmdatabindinglivedata.data.source.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.hades.mvvmdatabindinglivedata.data.model.Project;
import com.hades.mvvmdatabindinglivedata.data.source.GitDataSource;
import com.hades.mvvmdatabindinglivedata.data.source.api.service.GithubService;
import com.hades.mvvmdatabindinglivedata.data.source.api.service.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hades on 9/10/2018.
 */
public class GitRemoteDataSource implements GitDataSource {

    private static GitRemoteDataSource sInstance;

    public static synchronized GitRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new GitRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public LiveData<List<Project>> getListProjects(String userId) {
        final MutableLiveData<List<Project>> data = new MutableLiveData<>();

        GithubService githubService = ServiceGenerator.createService();
        githubService.getListProjects(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                // TODO handle error after
                data.setValue(null);
            }
        });
        return data;
    }

    @Override
    public LiveData<Project> getProjectDetails(String userId, String projectName) {
        final MutableLiveData<Project> data = new MutableLiveData<>();

        GithubService githubService = ServiceGenerator.createService();
        githubService.getProjectDetails(userId, projectName).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                // TODO handle error after
                data.setValue(null);
            }
        });
        return data;
    }
}
