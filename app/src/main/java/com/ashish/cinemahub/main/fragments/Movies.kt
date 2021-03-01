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
import com.ashish.cinemahub.data.models.movies.*
import com.ashish.cinemahub.databinding.FragmentMoviesBinding
import com.ashish.cinemahub.main.activities.AllActivity
import com.ashish.cinemahub.main.adapters.MoviesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "Movies"

class Movies : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var adapter: MoviesAdapter
    val type = "movie"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getTrendingMoviesOfWeek()
        getNowPlayingMovies()
        getUpcomingMovies()
        getPopularMovies()
        getTopRatedMovies()

        binding.trendingMovies.setOnClickListener{
            context?.startActivity(Intent(context, AllActivity::class.java)
                .putExtra("name","Trending Movies")
                .putExtra("type",type))
        }
        binding.topRatedMovie.setOnClickListener{
            context?.startActivity(Intent(context, AllActivity::class.java)
                .putExtra("name","Top Rated Movies")
                .putExtra("type",type))
        }
        binding.popularMovies.setOnClickListener{
            context?.startActivity(Intent(context, AllActivity::class.java)
                .putExtra("name","Popular Movies")
                .putExtra("type",type))
        }
        binding.upcomingMovies.setOnClickListener{
            context?.startActivity(Intent(context, AllActivity::class.java)
                .putExtra("name","Upcoming Movies")
                .putExtra("type",type))
        }
        binding.nowPlayingMovies.setOnClickListener{
            context?.startActivity(Intent(context, AllActivity::class.java)
                .putExtra("name","Now Playing Movies")
                .putExtra("type",type))
        }

    }

    private fun getTrendingMoviesOfWeek() {
        val call =
            RetrofitIstance.apiService.getTrendingMoviesOfWeek(RetrofitIstance.apiKey,1)

        call.enqueue(object : Callback<TrendingMoviesModel> {
            override fun onResponse(
                call: Call<TrendingMoviesModel>,
                response: Response<TrendingMoviesModel>
            ) {

                binding.shimmerFrameLayoutTrending.stopShimmer()
                binding.shimmerFrameLayoutTrending.visibility = View.GONE
                binding.trendingMoviesRecyclerview.visibility = View.VISIBLE

                adapter = response.body()?.results?.let { MoviesAdapter(requireContext(), it) }!!
                binding.trendingMoviesRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.trendingMoviesRecyclerview.adapter = adapter
            }

            override fun onFailure(call: Call<TrendingMoviesModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }


    private fun getNowPlayingMovies() {
        val call =
            RetrofitIstance.apiService.getNowPlayingMovies(RetrofitIstance.apiKey,1)

        call.enqueue(object : Callback<NowPlayingMoviesModel> {
            override fun onResponse(
                call: Call<NowPlayingMoviesModel>,
                response: Response<NowPlayingMoviesModel>
            ) {
                binding.shimmerFrameLayoutNowPlaying.stopShimmer()
                binding.shimmerFrameLayoutNowPlaying.visibility = View.GONE
                binding.nowPlayingRecyclerview.visibility = View.VISIBLE

                adapter = response.body()?.results?.let { MoviesAdapter(requireContext(), it) }!!
                binding.nowPlayingRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.nowPlayingRecyclerview.adapter = adapter
            }

            override fun onFailure(call: Call<NowPlayingMoviesModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getUpcomingMovies() {
        val call =
            RetrofitIstance.apiService.getUpcomingMovies(RetrofitIstance.apiKey,1)

        call.enqueue(object : Callback<UpcomingMoviesModel> {
            override fun onResponse(
                call: Call<UpcomingMoviesModel>,
                response: Response<UpcomingMoviesModel>
            ) {
                binding.shimmerFrameLayoutUpcoming.stopShimmer()
                binding.shimmerFrameLayoutUpcoming.visibility = View.GONE
                binding.upcomingRecyclerview.visibility = View.VISIBLE

                adapter = response.body()?.results?.let { MoviesAdapter(requireContext(), it) }!!
                binding.upcomingRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.upcomingRecyclerview.adapter = adapter            }

            override fun onFailure(call: Call<UpcomingMoviesModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getPopularMovies() {
        val call =
            RetrofitIstance.apiService.getPopularMovies(RetrofitIstance.apiKey,1)

        call.enqueue(object : Callback<PopularMoviesModel> {
            override fun onResponse(
                call: Call<PopularMoviesModel>,
                response: Response<PopularMoviesModel>
            ) {
                binding.shimmerFrameLayoutPopular.stopShimmer()
                binding.shimmerFrameLayoutPopular.visibility = View.GONE
                binding.popularRecyclerview.visibility = View.VISIBLE

                adapter = response.body()?.results?.let { MoviesAdapter(requireContext(), it) }!!
                binding.popularRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.popularRecyclerview.adapter = adapter            }

            override fun onFailure(call: Call<PopularMoviesModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getTopRatedMovies() {
        val call =
            RetrofitIstance.apiService.getTopRatedMovies(RetrofitIstance.apiKey,1)

        call.enqueue(object : Callback<TopRatedMoviesModel> {
            override fun onResponse(
                call: Call<TopRatedMoviesModel>,
                response: Response<TopRatedMoviesModel>
            ) {
                binding.shimmerFrameLayoutTopRated.stopShimmer()
                binding.shimmerFrameLayoutTopRated.visibility = View.GONE
                binding.topRatedRecyclerview.visibility = View.VISIBLE

                adapter = response.body()?.results?.let { MoviesAdapter(requireContext(), it) }!!
                binding.topRatedRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.topRatedRecyclerview.adapter = adapter            }

            override fun onFailure(call: Call<TopRatedMoviesModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    
}