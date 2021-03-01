package com.ashish.cinemahub.main.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashish.cinemahub.R
import com.ashish.cinemahub.data.api.RetrofitIstance
import com.ashish.cinemahub.data.models.movies.MovieDetailModel
import com.ashish.cinemahub.data.models.movies.SimilarMoviesModel
import com.ashish.cinemahub.databinding.ActivityMovieDetailsBinding
import com.ashish.cinemahub.main.adapters.SimilarMoviesAdapter
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var adapter: SimilarMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val id = intent.getIntExtra("id", 0)
        getMovieDetail(id)
        getSimilarMovies(id)

    }

    private fun getSimilarMovies(id: Int) {
        val call =
            RetrofitIstance.apiService.getSimilarMovies(id, RetrofitIstance.apiKey)

        call.enqueue(object : Callback<SimilarMoviesModel> {
            override fun onResponse(
                call: Call<SimilarMoviesModel>,
                response: Response<SimilarMoviesModel>
            ) {

                binding.shimmerFrameLayoutSimilarMovie.stopShimmer()
                binding.shimmerFrameLayoutSimilarMovie.visibility = View.GONE
                binding.similarRecyclerView.visibility = View.VISIBLE

                adapter = response.body()?.results?.let {
                    SimilarMoviesAdapter(
                        this@MovieDetailsActivity,
                        it
                    )
                }!!
                binding.similarRecyclerView.layoutManager =
                    LinearLayoutManager(
                        this@MovieDetailsActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                binding.similarRecyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<SimilarMoviesModel>, t: Throwable) {
            }

        })
    }

    private fun getMovieDetail(id: Int) {
        val call =
            RetrofitIstance.apiService.getMovieDetail(id, RetrofitIstance.apiKey)

        call.enqueue(object : Callback<MovieDetailModel> {
            override fun onResponse(
                call: Call<MovieDetailModel>,
                response: Response<MovieDetailModel>
            ) {
                response.body()?.let { setData(it) }

            }

            override fun onFailure(call: Call<MovieDetailModel>, t: Throwable) {

            }

        })
    }

    private fun setData(model: MovieDetailModel) {
        var imgurl = "https://image.tmdb.org/t/p/w500${model.backdrop_path}"
        Picasso.get().load(imgurl).fit().placeholder(R.drawable.pic)
            .into(binding.poster)
        imgurl = "https://image.tmdb.org/t/p/w500${model.poster_path}"
        Picasso.get().load(imgurl).fit().placeholder(R.drawable.pic)
            .into(binding.image)
        binding.title.text = model.title
        binding.desc.text = model.overview
        binding.tagline.text = model.tagline
        binding.budget.text = "Budget : $${model.budget}"
        binding.releaseDate.text = "Release Date : ${model.release_date}"
        var genre = ""
        model.genres.forEach {
            genre += "${it.name} , "
        }
        binding.genres.text = genre.removeSuffix(", ")
        binding.runtime.text = "Duration : ${model.runtime} min"
    }


}