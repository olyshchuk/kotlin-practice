package com.msl.kotlinpractice.model

data class BookListModel(val items: ArrayList<Info>)
data class Info(val albumId: Int, val id: Int, val title: String, val url: String, val thumbnailUrl: String)