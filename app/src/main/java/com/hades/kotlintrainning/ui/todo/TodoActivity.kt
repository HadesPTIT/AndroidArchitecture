package com.hades.kotlintrainning.ui.todo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import com.hades.kotlintrainning.R
import com.hades.kotlintrainning.ui.base.BaseDataBindingActivity
import com.hades.kotlintrainning.ui.base.BaseViewAdapter
import com.hades.kotlintrainning.ui.base.SingleTypeAdapter
import com.hades.kotlintrainning.databinding.ActivityTodoBinding
import com.hades.kotlintrainning.data.entity.Todo
import com.hades.kotlintrainning.viewmodel.TodoViewModel

class TodoActivity : BaseDataBindingActivity<ActivityTodoBinding>(), NavigationListener {

    private lateinit var mTodoViewModel: TodoViewModel
    private lateinit var mAdapter: SingleTypeAdapter<Todo>

    override val layoutID: Int get() = R.layout.activity_todo

    override fun initData() {
        mTodoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        mAdapter = SingleTypeAdapter(this, R.layout.item_todo)
        mBinding.navigator = this
        mBinding.recyclerTodo.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerTodo.adapter = mAdapter
        mTodoViewModel.getListTodos()
        initEvent()
    }

    private fun initEvent() {
        mTodoViewModel.getTodos().observe(this, Observer {
            it?.let {
                mAdapter.set(it)
            }
        })
        mTodoViewModel.loadingLiveData.observe(this, Observer {
            it?.let {
                showLoading(it)
            }
        })
    }

    /**
     * Navigation Listener
     */
    override fun onGoBack() {
        finish()
    }

    override fun onAddNewTodoTask() {
        mAdapter.add(Todo("Hades", "No description now..."))
    }

    inner class ItemClickListener : BaseViewAdapter.Presenter {

        fun onItemClick(todo: Todo) {
            showToast("Item".plus(todo.description))
        }

    }
}