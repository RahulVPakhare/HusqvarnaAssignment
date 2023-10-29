package com.husqvarna.assignment.presentation.detail

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.husqvarna.assignment.common.Constant
import com.husqvarna.assignment.data.remote.model.Movie
import com.husqvarna.assignment.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie: Movie.Result? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("movie", Movie.Result::class.java)
        } else {
            intent.getParcelableExtra("movie") as? Movie.Result
        }

        movie?.let {
            Glide.with(this).load(Constant.IMAGE_BASE_URL + Constant.IMAGE_SIZE + it.backdropPath)
                .into(binding.ivBackdrop)
            binding.run {
                tvDescription.text = it.overview
                tvTitle.text = it.title
                tvRating.text = it.voteAverage.toString()
                tvReleaseDate.text = it.releaseDate
                tvVoteCount.text = it.voteCount.toString()
            }
        }
    }
}