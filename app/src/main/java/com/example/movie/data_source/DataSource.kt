package com.example.movie.data_source

import com.example.movie.data_source.database.MovieDataBase
import com.example.movie.data_source.database.entity.ActorEntity
import com.example.movie.data_source.database.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

interface DataSource {

    //Movie
    fun deleteMovie(): Completable
    fun showPopularMovie(): Single<List<MovieEntity>>
    fun insertListMovie(movieList: List<MovieEntity>): Completable
    fun showMovieInfo(id: Int): Single<MovieEntity>
    fun insertMovieInfo(movie: MovieEntity): Completable
    fun insertAndClearList(movieList: List<MovieEntity>): Completable
    fun insertAndClearMovieInfo(movie: MovieEntity): Completable

    //Actor
    fun deleteActor(): Completable
    fun showMovieCast(): Single<List<ActorEntity>>
    fun insertActor(actorList: List<ActorEntity>): Completable
    fun insertAndClearListActor(actorList: List<ActorEntity>): Completable
}

class DataSourceImpl(private val movieDataBase: MovieDataBase) : DataSource {

    //Movie

    override fun deleteMovie(): Completable =
            Completable.fromAction {
                movieDataBase.MovieDao().deleteMovie()
            }

    override fun showPopularMovie(): Single<List<MovieEntity>> =
            movieDataBase.MovieDao().showPopularListMovie()

    override fun insertListMovie(movieList: List<MovieEntity>): Completable =
            Completable.fromAction {
                movieDataBase.MovieDao().insertListMovie(movieList)
            }

    override fun showMovieInfo(id: Int): Single<MovieEntity> =
            movieDataBase.MovieDao().showMovieInfo(id = id)

    override fun insertMovieInfo(movie: MovieEntity): Completable =
            Completable.fromAction {
                movieDataBase.MovieDao().insertMovieInfo(movie)
            }

    override fun insertAndClearList(movieList: List<MovieEntity>): Completable =
            Completable.fromAction {
                movieDataBase.MovieDao().insertAndClearList(movieList)
            }

    override fun insertAndClearMovieInfo(movie: MovieEntity): Completable =
            Completable.fromAction {
                movieDataBase.MovieDao().insertAndClearMovieInfo(movie)
            }

    //Actor

    override fun deleteActor(): Completable =
            Completable.fromAction {
                movieDataBase.MovieDao().deleteActor()
            }

    override fun showMovieCast(): Single<List<ActorEntity>> =
            movieDataBase.MovieDao().showMovieCast()

    override fun insertActor(actorList: List<ActorEntity>): Completable =
            Completable.fromAction {
                movieDataBase.MovieDao().insertActor(actorList)
            }

    override fun insertAndClearListActor(actorList: List<ActorEntity>): Completable =
            Completable.fromAction {
                movieDataBase.MovieDao().insertAndClearListActor(actorList)
            }
}