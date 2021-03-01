package com.ashish.cinemahub.main.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashish.cinemahub.data.api.RetrofitIstance
import com.ashish.cinemahub.data.models.tvs.*
import com.ashish.cinemahub.databinding.FragmentTVShowsBinding
import com.ashish.cinemahub.main.activities.AllActivity
import com.ashish.cinemahub.main.adapters.TVsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "TVShows"

class TVShows : Fragment() {

    private lateinit var binding: FragmentTVShowsBinding
    private lateinit var tvsAdapter: TVsAdapter
    val type = "tv"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTVShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getTrendingTVShowOfWeek()
        getNowPlayingTVShows()
        getOngoingTVShows()
        getPopularTVShows()
        getTopRatedTVShows()

        binding.trendingTvShows.setOnClickListener{
            context?.startActivity(Intent(context,AllActivity::class.java)
                .putExtra("name","Trending TV Shows")
                .putExtra("type",type))
        }
        binding.topRatedTv.setOnClickListener{
            context?.startActivity(Intent(context,AllActivity::class.java)
                .putExtra("name","Top Rated TV Shows")
                .putExtra("type",type))
        }
        binding.popularTv.setOnClickListener{
            context?.startActivity(Intent(context,AllActivity::class.java)
                .putExtra("name","Popular TV Shows")
                .putExtra("type",type))
        }
        binding.ongoingTv.setOnClickListener{
            context?.startActivity(Intent(context,AllActivity::class.java)
                .putExtra("name","Ongoing TV Shows")
                .putExtra("type",type))
        }
        binding.nowPlayingTv.setOnClickListener{
            context?.startActivity(Intent(context,AllActivity::class.java)
                .putExtra("name","Now Playing TV Shows")
                .putExtra("type",type))
        }
    }


    private fun getTrendingTVShowOfWeek() {
        val call =
            RetrofitIstance.apiService.getTrendingTVShowOfWeek(RetrofitIstance.apiKey,1)

        call.enqueue(object : Callback<TrendingTVModel> {
            override fun onResponse(
                call: Call<TrendingTVModel>,
                response: Response<TrendingTVModel>
            ) {
                binding.shimmerFrameLayoutTrending.stopShimmer()
                binding.shimmerFrameLayoutTrending.visibility = View.GONE
                binding.trendingTvRecyclerview.visibility = View.VISIBLE

                tvsAdapter = response.body()?.results?.let { TVsAdapter(requireContext(), it) }!!
                binding.trendingTvRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.trendingTvRecyclerview.adapter = tvsAdapter
            }

            override fun onFailure(call: Call<TrendingTVModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getNowPlayingTVShows() {
        val call =
            RetrofitIstance.apiService.getNowPlayingTVShows(RetrofitIstance.apiKey,1)

        call.enqueue(object : Callback<NowPlayingTVModel> {
            override fun onResponse(
                call: Call<NowPlayingTVModel>,
                response: Response<NowPlayingTVModel>
            ) {
                binding.shimmerFrameLayoutNowPlaying.stopShimmer()
                binding.shimmerFrameLayoutNowPlaying.visibility = View.GONE
                binding.nowPlayingTvRecyclerview.visibility = View.VISIBLE

                tvsAdapter = response.body()?.results?.let { TVsAdapter(requireContext(), it) }!!
                binding.nowPlayingTvRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.nowPlayingTvRecyclerview.adapter = tvsAdapter
            }

            override fun onFailure(call: Call<NowPlayingTVModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getOngoingTVShows() {
        val call =
            RetrofitIstance.apiService.getOngoingTVShows(RetrofitIstance.apiKey,1)

        call.enqueue(object : Callback<OngoingTVModel> {
            override fun onResponse(
                call: Call<OngoingTVModel>,
                response: Response<OngoingTVModel>
            ) {
                binding.shimmerFrameLayoutOngoing.stopShimmer()
                binding.shimmerFrameLayoutOngoing.visibility = View.GONE
                binding.ongoingTvRecyclerview.visibility = View.VISIBLE

                tvsAdapter = response.body()?.results?.let { TVsAdapter(requireContext(), it) }!!
                binding.ongoingTvRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.ongoingTvRecyclerview.adapter = tvsAdapter
            }

            override fun onFailure(call: Call<OngoingTVModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getPopularTVShows() {
        val call =
            RetrofitIstance.apiService.getPopularTVShows(RetrofitIstance.apiKey,1)

        call.enqueue(object : Callback<PopularTVModel> {
            override fun onResponse(
                call: Call<PopularTVModel>,
                response: Response<PopularTVModel>
            ) {
                binding.shimmerFrameLayoutPopular.stopShimmer()
                binding.shimmerFrameLayoutPopular.visibility = View.GONE
                binding.popularTvRecyclerview.visibility = View.VISIBLE

                tvsAdapter = response.body()?.results?.let { TVsAdapter(requireContext(), it) }!!
                binding.popularTvRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.popularTvRecyclerview.adapter = tvsAdapter
            }

            override fun onFailure(call: Call<PopularTVModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getTopRatedTVShows() {
        val call =
            RetrofitIstance.apiService.getTopRatedTVShows(RetrofitIstance.apiKey,1)

        call.enqueue(object : Callback<TopRatedTVModel> {
            override fun onResponse(
                call: Call<TopRatedTVModel>,
                response: Response<TopRatedTVModel>
            ) {
                binding.shimmerFrameLayoutTopRated.stopShimmer()
                binding.shimmerFrameLayoutTopRated.visibility = View.GONE
                binding.topRatedTvRecyclerview.visibility = View.VISIBLE

                tvsAdapter = response.body()?.results?.let { TVsAdapter(requireContext(), it) }!!
                binding.topRatedTvRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.topRatedTvRecyclerview.adapter = tvsAdapter
            }

            override fun onFailure(call: Call<TopRatedTVModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

}