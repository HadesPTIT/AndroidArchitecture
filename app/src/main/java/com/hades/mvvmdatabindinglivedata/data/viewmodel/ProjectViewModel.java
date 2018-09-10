package com.hades.mvvmdatabindinglivedata.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.hades.mvvmdatabindinglivedata.data.model.Project;
import com.hades.mvvmdatabindinglivedata.data.source.remote.GitRemoteDataSource;
import com.hades.mvvmdatabindinglivedata.data.source.repository.GitRepository;

/**
 * Created by Hades on 9/10/2018.
 */
public class ProjectViewModel extends AndroidViewModel {

    private final LiveData<Project> mProjectLiveData;
    public ObservableField<Project> mProjectObservable = new ObservableField<>();

    public ProjectViewModel(@NonNull Application application, String userId, String projectId) {
        super(application);
        mProjectLiveData = GitRepository.getInstance(null,
                GitRemoteDataSource.getInstance()).getProjectDetails(userId, projectId);
    }

    public LiveData<Project> getProjectLiveData() {
        return mProjectLiveData;
    }

    public void setProject(Project project) {
        this.mProjectObservable.set(project);
    }

    /**
     * A Factory is used to inject the project ID into ViewModel
     */

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;
        @NonNull
        private final String mUserId;
        @NonNull
        private final String mProjectId;

        public Factory(@NonNull Application application, @NonNull String userId, @NonNull String projectId) {
            mApplication = application;
            mUserId = userId;
            mProjectId = projectId;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ProjectViewModel(mApplication, mUserId, mProjectId);
        }
    }


}
