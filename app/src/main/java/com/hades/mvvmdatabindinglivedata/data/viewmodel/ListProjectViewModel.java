package com.hades.mvvmdatabindinglivedata.data.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hades.mvvmdatabindinglivedata.data.model.Project;
import com.hades.mvvmdatabindinglivedata.data.source.remote.GitRemoteDataSource;
import com.hades.mvvmdatabindinglivedata.data.source.repository.GitRepository;

import java.util.List;

/**
 * Created by Hades on 9/10/2018.
 */
public class ListProjectViewModel extends AndroidViewModel {

    private LiveData<List<Project>> mListLiveData;

    public ListProjectViewModel(@NonNull Application application) {
        super(application);
        /**
         * If any transformation is needed, this can be simply done by transformations class
         */
        // TODO Get livedata list project here
        mListLiveData = GitRepository.getInstance(null,
                GitRemoteDataSource.getInstance()).getListProjects("Google");
    }

    /**
     * Expose the LiveData projects query so the UI can observe it
     */
    public LiveData<List<Project>> getListProjectLiveData() {
        return mListLiveData;
    }
}
