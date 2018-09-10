package com.hades.mvvmdatabindinglivedata.screen.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hades.mvvmdatabindinglivedata.R;
import com.hades.mvvmdatabindinglivedata.callback.OnItemProjectClickListener;
import com.hades.mvvmdatabindinglivedata.data.model.Project;
import com.hades.mvvmdatabindinglivedata.databinding.ItemProjectBinding;

import java.util.List;

/**
 * Created by Hades on 9/10/2018.
 */
public class ListProjectAdapter extends ListAdapter<Project, ListProjectAdapter.ListProjectViewHolder> {

    private List<Project> mListProjects;
    private OnItemProjectClickListener mListener;


    protected ListProjectAdapter(OnItemProjectClickListener listener) {
        super(Project.DIFF_CALLBACK);
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ListProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProjectBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_project, parent, false);
        binding.setCallback(mListener);
        return new ListProjectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProjectViewHolder holder, int position) {
        holder.binding.setProject(mListProjects.get(position));
        holder.binding.executePendingBindings();
    }

    public void update(List<Project> projects) {
        this.mListProjects = projects;
        submitList(projects);
    }

    static class ListProjectViewHolder extends RecyclerView.ViewHolder {

        final ItemProjectBinding binding;

        public ListProjectViewHolder(ItemProjectBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}


