package com.hades.mvvmdatabindinglivedata.screen.detail;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.mvvmdatabindinglivedata.R;
import com.hades.mvvmdatabindinglivedata.data.model.Project;
import com.hades.mvvmdatabindinglivedata.data.viewmodel.ProjectViewModel;
import com.hades.mvvmdatabindinglivedata.databinding.FragmentProjectDetailBinding;

/**
 * Created by Hades on 9/10/2018.
 */
public class ProjectDetailFragment extends Fragment implements LifecycleOwner {

    private static final String KEY_PROJECT_ID = "KEY_PROJECT_ID";
    private static final String KEY_PROJECT_USER_ID = "KEY_PROJECT_USER_ID";

    private FragmentProjectDetailBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_detail, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProjectViewModel.Factory factory = new ProjectViewModel.Factory(
                getActivity().getApplication(),
                getArguments().getString(KEY_PROJECT_USER_ID),
                getArguments().getString(KEY_PROJECT_ID));
        ProjectViewModel viewModel = ViewModelProviders.of(this, factory)
                .get(ProjectViewModel.class);
        mBinding.setProjectViewModel(viewModel);
        mBinding.setIsLoading(true);
        observeViewModel(viewModel);

    }

    private void observeViewModel(final ProjectViewModel viewModel) {
        viewModel.getProjectLiveData().observe(this, new Observer<Project>() {
            @Override
            public void onChanged(@Nullable Project project) {
                if (project != null) {
                    mBinding.setIsLoading(false);
                    viewModel.setProject(project);
                }
            }
        });
    }

    /**
     * Create project fragment for specific projectID & userID
     */
    public static ProjectDetailFragment forProject(String userID, String projectID) {
        ProjectDetailFragment fragment = new ProjectDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PROJECT_USER_ID, userID);
        bundle.putString(KEY_PROJECT_ID, projectID);
        fragment.setArguments(bundle);
        return fragment;
    }

}
