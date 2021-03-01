package com.ashish.cinemahub.main.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashish.cinemahub.R
import com.ashish.cinemahub.data.api.RetrofitIstance
import com.ashish.cinemahub.data.models.tvs.SimilarTVModel
import com.ashish.cinemahub.data.models.tvs.TVDetailModel
import com.ashish.cinemahub.databinding.ActivityTVShowDetailsBinding
import com.ashish.cinemahub.main.adapters.SimilarTVsAdapter
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTVShowDetailsBinding
    private lateinit var adapter: SimilarTVsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTVShowDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val id = intent.getIntExtra("id", 0)
        getTVShowDetail(id)
        getSimilarTVShows(id)
    }

    private fun getSimilarTVShows(id: Int) {
        val call =
            RetrofitIstance.apiService.getSimilarTVShows(id, RetrofitIstance.apiKey)

        call.enqueue(object : Callback<SimilarTVModel> {
            override fun onResponse(
                call: Call<SimilarTVModel>,
                response: Response<SimilarTVModel>
            ) {
                binding.shimmerFrameLayoutSimilarTV.stopShimmer()
                binding.shimmerFrameLayoutSimilarTV.visibility = View.GONE
                binding.similarRecyclerView.visibility = View.VISIBLE

                adapter = response.body()?.results?.let {
                    SimilarTVsAdapter(
                        this@TVShowDetailsActivity,
                        it
                    )
                }!!
                binding.similarRecyclerView.layoutManager =
                    LinearLayoutManager(
                        this@TVShowDetailsActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                binding.similarRecyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<SimilarTVModel>, t: Throwable) {
                binding.shimmerFrameLayoutSimilarTV.stopShimmer()
                binding.shimmerFrameLayoutSimilarTV.visibility = View.GONE
            }

        })
    }

    private fun getTVShowDetail(id: Int) {
        val call =
            RetrofitIstance.apiService.getTVShowDetail(id, RetrofitIstance.apiKey)

        call.enqueue(object : Callback<TVDetailModel> {
            override fun onResponse(
                call: Call<TVDetailModel>,
                response: Response<TVDetailModel>
            ) {
                response.body()?.let { setData(it) }
            }

            override fun onFailure(call: Call<TVDetailModel>, t: Throwable) {
            }

        })
    }

    private fun setData(model: TVDetailModel) {

        var imgurl = "https://image.tmdb.org/t/p/w500${model.backdrop_path}"
        Picasso.get().load(imgurl).fit().placeholder(R.drawable.pic)
            .into(binding.poster)
        imgurl = "https://image.tmdb.org/t/p/w500${model.poster_path}"
        Picasso.get().load(imgurl).fit().placeholder(R.drawable.pic)
            .into(binding.image)
        binding.title.text = model.name
        binding.desc.text = model.overview
        binding.tagline.text = model.tagline
        var genre = ""
        model.genres.forEach {
            genre += "${it.name} , "
        }

        binding.genres.text = genre.removeSuffix(", ")
    }


}