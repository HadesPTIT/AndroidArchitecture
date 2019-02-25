package com.hades.kotlintrainning.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.hades.kotlintrainning.ui.base.BaseViewModel
import com.hades.kotlintrainning.data.TodoRepository
import com.hades.kotlintrainning.data.entity.Todo
import java.util.concurrent.TimeUnit

class TodoViewModel(application: Application) : BaseViewModel(application) {

    private var mTodoRepo: TodoRepository = TodoRepository.instance
    private val mTodos = MutableLiveData<List<Todo>>()

    fun getListTodos() {
        subscribe(mTodoRepo.listTodos
                .delay(2000, TimeUnit.MILLISECONDS)
                .doOnSubscribe {
                    loadingLiveData.postValue(true)
                }
                .doAfterSuccess {
                    loadingLiveData.postValue(false)
                }
                .subscribe({ todos ->
                    if (todos == null || todos.isEmpty()) {
                        return@subscribe
                    }
                    mTodos.postValue(todos)
                }, {
                    // Todo : handle error here`
                }))
    }

    fun getTodos(): MutableLiveData<List<Todo>> {
        return mTodos
    }

}
