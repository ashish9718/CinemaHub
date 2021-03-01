package com.ashish.cinemahub.data.models.movies

data class TopRatedMoviesModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

