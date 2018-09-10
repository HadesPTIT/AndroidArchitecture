package com.hades.mvvmdatabindinglivedata.screen.home;

import android.arch.lifecycle.Lifecycle;
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

import com.hades.mvvmdatabindinglivedata.MainActivity;
import com.hades.mvvmdatabindinglivedata.R;
import com.hades.mvvmdatabindinglivedata.callback.OnItemProjectClickListener;
import com.hades.mvvmdatabindinglivedata.data.model.Project;
import com.hades.mvvmdatabindinglivedata.data.viewmodel.ListProjectViewModel;
import com.hades.mvvmdatabindinglivedata.databinding.FragmentListProjectBinding;

import java.util.List;

/**
 * Created by Hades on 9/10/2018.
 */
public class ListProjectFragment extends Fragment {

    private FragmentListProjectBinding mBinding;
    private ListProjectAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_project, container, false);
        mAdapter = new ListProjectAdapter(callBack);
        mBinding.recyclerProjects.setAdapter(mAdapter);
        mBinding.recyclerProjects.setHasFixedSize(true);
        mBinding.setIsLoading(true);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListProjectViewModel viewModel = ViewModelProviders.of(this).get(ListProjectViewModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(ListProjectViewModel viewModel) {
        viewModel.getListProjectLiveData().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                if (projects != null) {
                    mBinding.setIsLoading(false);
                    mAdapter.update(projects);
                }
            }
        });
    }

    private OnItemProjectClickListener callBack = new OnItemProjectClickListener() {
        @Override
        public void onClick(Project project) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                // TODO after
                ((MainActivity) getActivity()).showDetail(project);
            }
        }
    };
}
