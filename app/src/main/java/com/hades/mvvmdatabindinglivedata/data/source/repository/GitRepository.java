package com.hades.mvvmdatabindinglivedata.data.source.repository;

import android.arch.lifecycle.LiveData;

import com.hades.mvvmdatabindinglivedata.data.model.Project;
import com.hades.mvvmdatabindinglivedata.data.source.GitDataSource;

import java.util.List;

/**
 * Created by Hades on 9/10/2018.
 */
public class GitRepository implements GitDataSource {

    private GitDataSource mLocalDataSource;
    private GitDataSource mRemoteDataSource;

    private static GitRepository sInstance;

    private GitRepository(GitDataSource localDataSource, GitDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static GitRepository getInstance(GitDataSource localDataSource, GitDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new GitRepository(localDataSource, remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public LiveData<List<Project>> getListProjects(String userId) {
        return mRemoteDataSource.getListProjects(userId);
    }

    @Override
    public LiveData<Project> getProjectDetails(String userId, String projectName) {
        return mRemoteDataSource.getProjectDetails(userId, projectName);
    }
}
