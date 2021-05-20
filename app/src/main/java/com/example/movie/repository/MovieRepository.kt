package com.example.movie.repository

import com.example.movie.data_source.DataSource
import com.example.movie.data_source.database.entity.ActorEntity
import com.example.movie.data_source.database.entity.MovieEntity
import com.example.movie.remote_data_source.RemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single

interface MovieRepository {

    //Movie
    fun getPopularMovie(): Completable
    fun deleteMovie(): Completable
    fun showPopularMovie(): Single<List<MovieEntity>>
    fun getMovieById(id: Int): Completable
    fun showMovieInfo(id: Int): Single<MovieEntity>

    //Actor
    fun getMovieCast(id: Int): Completable
    fun deleteActor(): Completable
    fun showMovieCast(): Single<List<ActorEntity>>
}

class MovieRepositoryImpl(
        private val dataSource: DataSource,
        private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    //Movie
    override fun getPopularMovie(): Completable {
        return remoteDataSource.getPopularMovie()
                .flatMapCompletable { list ->
                    saveListMovie(itemsMovie = list, isCached = true)
                }
    }

    override fun deleteMovie(): Completable {
        return dataSource.deleteMovie()
    }

    override fun showPopularMovie(): Single<List<MovieEntity>> {
        return dataSource.showPopularMovie()

    }

    override fun getMovieById(id: Int): Completable {
        return remoteDataSource.getMovieById(id)
                .flatMapCompletable { movie ->
                    saveMovieInfo(movieEntity = movie, isCached = true)
                }
    }

    override fun showMovieInfo(id: Int): Single<MovieEntity> {
        return dataSource.showMovieInfo(id)
    }

    private fun saveListMovie(
            itemsMovie: List<MovieEntity>, isCached: Boolean
    ): Completable {
        if (isCached) {
            return dataSource.insertListMovie(itemsMovie)
        } else {
            return dataSource.insertAndClearList(itemsMovie)
        }
    }

    private fun saveMovieInfo(
            movieEntity: MovieEntity, isCached: Boolean
    ): Completable {
        if (isCached) {
            return dataSource.insertMovieInfo(movieEntity)
        } else {
            return dataSource.insertAndClearMovieInfo(movieEntity)
        }
    }

    //Actor
    override fun getMovieCast(id: Int): Completable {
        return  remoteDataSource.getMovieCast(id)
                .flatMapCompletable { actor ->
                    saveActorList(actorItem = actor, isCached = true)
                }
    }

    override fun deleteActor(): Completable {
        return dataSource.deleteActor()
    }

    override fun showMovieCast(): Single<List<ActorEntity>> {
        return dataSource.showMovieCast()
    }

    private fun saveActorList(
            actorItem: List<ActorEntity>, isCached: Boolean
    ): Completable {
        if (isCached) {
            return dataSource.insertActor(actorItem)
        } else {
            return dataSource.insertAndClearListActor(actorItem)
        }
    }
}

