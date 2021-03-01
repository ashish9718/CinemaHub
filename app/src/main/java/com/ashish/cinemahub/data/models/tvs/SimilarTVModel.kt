package com.ashish.cinemahub.data.models.tvs

data class SimilarTVModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)