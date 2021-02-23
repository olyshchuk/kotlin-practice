package com.msl.kotlinpractice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msl.kotlinpractice.model.BookListModel
import com.msl.kotlinpractice.network.RetroInstance
import com.msl.kotlinpractice.network.RetroService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BookViewModel: ViewModel() {

    var bookList: MutableLiveData<BookListModel> = MutableLiveData()

    fun getBookListObserver(): MutableLiveData<BookListModel> {
        return bookList
    }

    fun getCallFromApi() {

        val retroInstance  = RetroInstance.getRetroInstance().create(RetroService::class.java)
        retroInstance.getBookList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getBookListObserverRx())
    }

    private fun getBookListObserverRx():Observer<BookListModel> {
        return object :Observer<BookListModel>{
            override fun onComplete() {
                //hide progress indicator .
            }

            override fun onError(e: Throwable) {
                bookList.postValue(null)
            }

            override fun onNext(t: BookListModel) {
                bookList.postValue(t)
            }

            override fun onSubscribe(d: Disposable) {
                //start showing progress indicator.
            }
        }
    }

}