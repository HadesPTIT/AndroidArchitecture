package com.hades.kotlintrainning.data

import com.hades.kotlintrainning.data.entity.Todo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class TodoRepository private constructor() {

    init {
    }

    private object Holder {
        val INSTANCE = TodoRepository()
    }

    /**
     * Khi khai bao companion object thi cac ham trong viet trong doi tuong
     * se duoc khoi tao dong hanh cung class
     */
    companion object {
        val instance: TodoRepository by lazy {
            Holder.INSTANCE
        }
    }

    val listTodos: Single<List<Todo>>
        get() = Single.just(dummyTodos())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

    fun dummyTodos(): List<Todo> {
        val list = ArrayList<Todo>()
        for (i in 0..49) {
            list.add(Todo("Task $i", i.toString()))
        }
        return list
    }
}