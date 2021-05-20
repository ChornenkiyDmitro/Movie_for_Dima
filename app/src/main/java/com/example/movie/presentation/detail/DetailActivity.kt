package com.example.movie.presentation.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.movie.MovieApp
import com.example.movie.R
import com.example.movie.data_source.database.entity.MovieEntity
import com.example.movie.view_model.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_movie.view.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    var detailViewModel: DetailViewModel? = null @Inject set
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        (application as MovieApp).getViewModelComponent().inject(this)

        val id = intent.getIntExtra("movie_id",-1)
            detailViewModel?.getMovie(id)
        writeMovieInfo()
    }

    private fun writeMovieInfo() {

            detailViewModel?.gotMovie?.observe(this, Observer<MovieEntity>{
                movie ->
                textTitleDetail.text = movie.title

                //Todo make good looking format
                textGenreDetail.text = movie.overview.map { it.name }.toString()
                textvoteAverageDetail.text =movie.voteAverage
                Glide.with(this)
                    .load(IMAGE_BASE + movie.posterPath)
                    .into(imageMoviePosterDetail)
            })
        }
    }
