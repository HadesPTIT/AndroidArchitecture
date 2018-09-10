package com.hades.mvvmdatabindinglivedata;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hades.mvvmdatabindinglivedata.data.model.Project;
import com.hades.mvvmdatabindinglivedata.screen.detail.ProjectDetailFragment;
import com.hades.mvvmdatabindinglivedata.screen.home.ListProjectFragment;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            ListProjectFragment fragment = new ListProjectFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_container, fragment, ListProjectFragment.class.getSimpleName())
                    .commit();
        }
    }

    public void showDetail(Project project) {
        ProjectDetailFragment fragment = ProjectDetailFragment.forProject("Google", project.name);
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frame_container, fragment, null)
                .commit();
    }


}
