package com.ari.movies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ari.movies.databinding.ItemMovieBinding
import com.ari.movies.ui.model.MovieUi

class MoviesAdapter(
    private val onClickMovie: (movie: MovieUi, position: Int) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val movies = arrayListOf<MovieUi>()

    fun setList(movies: List<MovieUi>) {
        this.movies.apply {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }

    fun addItems(movies: List<MovieUi>) {
        val positionStart = this.movies.size
        this.movies.addAll(movies)
        notifyItemRangeInserted(positionStart, movies.size)
    }

    fun clearList() {
        this.movies.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(movies[position], position)

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieUi, position: Int) {
            binding.movieItem = movie
            binding.ivPoster.setOnClickListener { onClickMovie(movie, position) }
        }
    }
}