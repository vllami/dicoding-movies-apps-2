package com.dicoding.capstoneakhir.core.source.remote.network

import com.dicoding.capstoneakhir.core.BuildConfig.TMDB_API_KEY
import com.dicoding.capstoneakhir.core.source.remote.response.ListResponse
import retrofit2.http.GET

interface TMDBService {

    @GET("movie/now_playing?api_key=$TMDB_API_KEY")
    suspend fun getMovieNowPlaying(): ListResponse

}