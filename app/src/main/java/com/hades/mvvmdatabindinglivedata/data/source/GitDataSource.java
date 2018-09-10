package com.hades.mvvmdatabindinglivedata.data.source;

import android.arch.lifecycle.LiveData;

import com.hades.mvvmdatabindinglivedata.data.model.Project;

import java.util.List;

/**
 * Created by Hades on 9/10/2018.
 */
public interface GitDataSource {

    LiveData<List<Project>> getListProjects(String userId);

    LiveData<Project> getProjectDetails(String userId, String projectName);
}
