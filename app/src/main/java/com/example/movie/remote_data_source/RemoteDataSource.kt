package com.example.movie.remote_data_source

import com.example.movie.data_source.database.entity.ActorEntity
import com.example.movie.data_source.database.entity.MovieEntity
import io.reactivex.Single

interface RemoteDataSource {

    //Movie
    fun getPopularMovie():Single<List<MovieEntity>>
    fun getMovieById(id: Int):Single<MovieEntity>

    //Actor
    fun getMovieCast(id: Int):Single<List<ActorEntity>>
}

class RemoteDataSourceImpl(private val movieApi: MovieApi):RemoteDataSource{

    //Movie

    override fun getPopularMovie(): Single<List<MovieEntity>> {
        return movieApi.getPopularMovie().map { it.result }
    }

    override fun getMovieById(id: Int): Single<MovieEntity> {
    return movieApi.getMovieById(id = id)
    }

    //Actor

    override fun getMovieCast(id: Int): Single<List<ActorEntity>> {
        return movieApi.getMovieCast(id).map { it.cast }
    }
}
