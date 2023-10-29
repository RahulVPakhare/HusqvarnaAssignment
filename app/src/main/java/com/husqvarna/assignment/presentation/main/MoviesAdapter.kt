package com.husqvarna.assignment.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.husqvarna.assignment.common.Constant
import com.husqvarna.assignment.data.remote.model.Movie
import com.husqvarna.assignment.databinding.RowMoviesListBinding

class MoviesAdapter(
    private val moviesList: ArrayList<Movie.Result?>?,
    private val movieClickListener: MovieClickListener
) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            RowMoviesListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList?.get(position)
        holder.bind(movie)
        holder.binding.cvMovie.setOnClickListener {
            movieClickListener.onMovieClick(movie)
        }
    }

    override fun getItemCount(): Int = moviesList?.count() ?: 0

    class MovieViewHolder(val binding: RowMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie.Result?) {
            Glide.with(itemView.context)
                .load(Constant.IMAGE_BASE_URL + Constant.IMAGE_SIZE + movie?.posterPath)
                .into(binding.ivMovie)
        }
    }
}