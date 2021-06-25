package com.dicoding.capstoneakhir.core.adapter

import android.view.LayoutInflater.from as layoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.dicoding.capstoneakhir.core.Constant.IMAGE_BASE_URL
import com.dicoding.capstoneakhir.core.Constant.ITEMS_POSTER_RADIUS
import com.dicoding.capstoneakhir.core.Constant.RADIUS
import com.dicoding.capstoneakhir.core.Constant.SAMPLING
import com.dicoding.capstoneakhir.core.R
import com.dicoding.capstoneakhir.core.databinding.ItemListBinding
import com.dicoding.capstoneakhir.core.databinding.ItemListBinding.inflate as ItemListBinding
import com.dicoding.capstoneakhir.core.domain.model.Movee
import jp.wasabeef.glide.transformations.BlurTransformation

class MoveeAdapter : RecyclerView.Adapter<MoveeAdapter.MoveeViewHolder>() {

    private var moviesList = ArrayList<Movee>()
    var onItemClick: ((Movee) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoveeViewHolder {
        viewGroup.apply {
            ItemListBinding(layoutInflater(context), this, false).also {
                return MoveeViewHolder(it)
            }
        }
    }

    override fun onBindViewHolder(holder: MoveeViewHolder, position: Int) {
        moviesList[position].also {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = moviesList.size

    fun setData(newMoviesList: List<Movee>?) {
        if (newMoviesList == null) return
        moviesList.apply {
            clear()
            addAll(newMoviesList)
            notifyDataSetChanged()
        }
    }

    inner class MoveeViewHolder(private val itemListBinding: ItemListBinding) : RecyclerView.ViewHolder(itemListBinding.root) {
        fun bind(movee: Movee) {
            itemListBinding.apply {
                movee.apply {
                    itemView.apply {
                        Glide
                            .with(context)
                            .load("$IMAGE_BASE_URL$backdrop")
                            .apply(bitmapTransform(BlurTransformation(RADIUS, SAMPLING)))
                            .into(imgBackdrop)
                        Glide
                            .with(context)
                            .load("$IMAGE_BASE_URL$poster")
                            .transform(RoundedCorners(ITEMS_POSTER_RADIUS))
                            .apply(placeholderOf(R.drawable.bg_loading_poster).error(R.drawable.bg_loading_poster))
                            .into(imgPoster)
                        tvTitle.text = title
                        tvRating.text = rating.toString()
                        tvReleaseDate.text = releaseDate
                        tvSynopsis.text = synopsis
                    }
                }
            }
        }

        init {
            itemListBinding.root.setOnClickListener {
                onItemClick?.invoke(moviesList[absoluteAdapterPosition])
            }
        }
    }

}