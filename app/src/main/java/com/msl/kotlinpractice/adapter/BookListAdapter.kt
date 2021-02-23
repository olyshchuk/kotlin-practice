package com.msl.kotlinpractice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.msl.kotlinpractice.R
import com.msl.kotlinpractice.model.Info

class BookListAdapter : RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {

    var bookListData = ArrayList<Info>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false)
        return MyViewHolder(inflater)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(bookListData[position])
    }

    override fun getItemCount(): Int {
        return bookListData.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        private val tvPublisher = view.findViewById<TextView>(R.id.tvPublisher)
        private val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        private val thumbImageView = view.findViewById<ImageView>(R.id.thumbImageView)

        fun bind(data: Info) {
            tvTitle.text = data.title
            tvPublisher.text = data.title
            tvDescription.text = data.title

            val url = data.thumbnailUrl
            Glide.with(thumbImageView)
                .load(url)
                .circleCrop()
                .into(thumbImageView)
        }
    }
}