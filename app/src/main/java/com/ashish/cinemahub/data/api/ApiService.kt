package com.ashish.cinemahub.data.api

import com.ashish.cinemahub.data.models.SearchModel
import com.ashish.cinemahub.data.models.movies.*
import com.ashish.cinemahub.data.models.tvs.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // movies

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): Call<NowPlayingMoviesModel>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): Call<UpcomingMoviesModel>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): Call<PopularMoviesModel>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): Call<TopRatedMoviesModel>

    @GET("trending/movie/week")
    fun getTrendingMoviesOfWeek(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): Call<TrendingMoviesModel>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") tv_id: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetailModel>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Path("movie_id") tv_id: Int,
        @Query("api_key") apiKey: String
    ): Call<SimilarMoviesModel>

    // tv shows

    @GET("trending/tv/week")
    fun getTrendingTVShowOfWeek(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): Call<TrendingTVModel>

    @GET("tv/airing_today")
    fun getNowPlayingTVShows(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): Call<NowPlayingTVModel>

    @GET("tv/on_the_air")
    fun getOngoingTVShows(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): Call<OngoingTVModel>

    @GET("tv/popular")
    fun getPopularTVShows(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): Call<PopularTVModel>

    @GET("tv/top_rated")
    fun getTopRatedTVShows(
        @Query("api_key") apiKey: String, @Query("page") page: Int
    ): Call<TopRatedTVModel>

    @GET("tv/{tv_id}")
    fun getTVShowDetail(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") apiKey: String
    ): Call<TVDetailModel>

    @GET("tv/{tv_id}/similar")
    fun getSimilarTVShows(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") apiKey: String
    ): Call<SimilarTVModel>

    //search all

    @GET("search/multi")
    fun getSearch(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Call<SearchModel>


}