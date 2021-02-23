package com.msl.kotlinpractice.network

import com.msl.kotlinpractice.model.BookListModel
import io.reactivex.Observable
import retrofit2.http.GET

interface RetroService {

    @GET("/photos")
    fun getBookList(): Observable<BookListModel>
}