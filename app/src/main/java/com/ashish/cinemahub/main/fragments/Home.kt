package com.ashish.cinemahub.main.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashish.cinemahub.data.api.RetrofitIstance
import com.ashish.cinemahub.data.models.movies.*
import com.ashish.cinemahub.data.models.tvs.*
import com.ashish.cinemahub.databinding.FragmentHomeBinding
import com.ashish.cinemahub.main.activities.AllActivity
import com.ashish.cinemahub.main.adapters.MoviesAdapter
import com.ashish.cinemahub.main.adapters.TVsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "Home"

class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var tvsAdapter: TVsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getTrendingMoviesOfWeek()
        getNowPlayingMovies()
        getUpcomingMovies()
        getPopularMovies()
        getTopRatedMovies()

        getTrendingTVShowOfWeek()
        getNowPlayingTVShows()
        getOngoingTVShows()
        getPopularTVShows()
        getTopRatedTVShows()

        binding.trendingMovies.setOnClickListener {
            context?.startActivity(
                Intent(context, AllActivity::class.java)
                    .putExtra("name", "Trending Movies")
                    .putExtra("type", "movie")
            )
        }
        binding.trendingTvShows.setOnClickListener {
            context?.startActivity(
                Intent(context, AllActivity::class.java)
                    .putExtra("name", "Trending TV Shows")
                    .putExtra("type", "tv")
            )
        }

        binding.topRatedMovie.setOnClickListener {
            context?.startActivity(
                Intent(context, AllActivity::class.java)
                    .putExtra("name", "Top Rated Movies")
                    .putExtra("type", "movie")
            )
        }
        binding.topRatedTv.setOnClickListener {
            context?.startActivity(
                Intent(context, AllActivity::class.java)
                    .putExtra("name", "Top Rated TV Shows")
                    .putExtra("type", "tv")
            )
        }

        binding.popularMovies.setOnClickListener {
            context?.startActivity(
                Intent(context, AllActivity::class.java)
                    .putExtra("name", "Popular Movies")
                    .putExtra("type", "movie")
            )
        }
        binding.popularTv.setOnClickListener {
            context?.startActivity(
                Intent(context, AllActivity::class.java)
                    .putExtra("name", "Popular TV Shows")
                    .putExtra("type", "tv")
            )
        }

        binding.upcomingMovie.setOnClickListener {
            context?.startActivity(
                Intent(context, AllActivity::class.java)
                    .putExtra("name", "Upcoming Movies")
                    .putExtra("type", "movie")
            )
        }
        binding.OngoingTv.setOnClickListener {
            context?.startActivity(
                Intent(context, AllActivity::class.java)
                    .putExtra("name", "Ongoing TV Shows")
                    .putExtra("type", "tv")
            )
        }

        binding.nowPlayingMovie.setOnClickListener {
            context?.startActivity(
                Intent(context, AllActivity::class.java)
                    .putExtra("name", "Now Playing Movies")
                    .putExtra("type", "movie")
            )
        }
        binding.nowPlayingTv.setOnClickListener {
            context?.startActivity(
                Intent(context, AllActivity::class.java)
                    .putExtra("name", "Now Playing TV Shows")
                    .putExtra("type", "tv")
            )
        }

    }

    private fun getTrendingTVShowOfWeek() {
        val call =
            RetrofitIstance.apiService.getTrendingTVShowOfWeek(RetrofitIstance.apiKey, 1)

        call.enqueue(object : Callback<TrendingTVModel> {
            override fun onResponse(
                call: Call<TrendingTVModel>,
                response: Response<TrendingTVModel>
            ) {
                binding.shimmerFrameLayoutTrendingTV.stopShimmer()
                binding.shimmerFrameLayoutTrendingTV.visibility = View.GONE
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

    private fun getNowPlayingMovies() {
        val call =
            RetrofitIstance.apiService.getNowPlayingMovies(RetrofitIstance.apiKey, 1)

        call.enqueue(object : Callback<NowPlayingMoviesModel> {
            override fun onResponse(
                call: Call<NowPlayingMoviesModel>,
                response: Response<NowPlayingMoviesModel>
            ) {
                binding.shimmerFrameLayoutNowPlayingMovies.stopShimmer()
                binding.shimmerFrameLayoutNowPlayingMovies.visibility = View.GONE
                binding.nowPlayingRecyclerview.visibility = View.VISIBLE

                moviesAdapter =
                    response.body()?.results?.let { MoviesAdapter(requireContext(), it) }!!
                binding.nowPlayingRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.nowPlayingRecyclerview.adapter = moviesAdapter
            }

            override fun onFailure(call: Call<NowPlayingMoviesModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getUpcomingMovies() {
        val call =
            RetrofitIstance.apiService.getUpcomingMovies(RetrofitIstance.apiKey, 1)

        call.enqueue(object : Callback<UpcomingMoviesModel> {
            override fun onResponse(
                call: Call<UpcomingMoviesModel>,
                response: Response<UpcomingMoviesModel>
            ) {
                binding.shimmerFrameLayoutUpcomingMovies.stopShimmer()
                binding.shimmerFrameLayoutUpcomingMovies.visibility = View.GONE
                binding.upcomingRecyclerview.visibility = View.VISIBLE

                moviesAdapter =
                    response.body()?.results?.let { MoviesAdapter(requireContext(), it) }!!
                binding.upcomingRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.upcomingRecyclerview.adapter = moviesAdapter
            }

            override fun onFailure(call: Call<UpcomingMoviesModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getPopularMovies() {
        val call =
            RetrofitIstance.apiService.getPopularMovies(RetrofitIstance.apiKey, 1)

        call.enqueue(object : Callback<PopularMoviesModel> {
            override fun onResponse(
                call: Call<PopularMoviesModel>,
                response: Response<PopularMoviesModel>
            ) {
                binding.shimmerFrameLayoutPopularMovies.stopShimmer()
                binding.shimmerFrameLayoutPopularMovies.visibility = View.GONE
                binding.popularRecyclerview.visibility = View.VISIBLE

                moviesAdapter =
                    response.body()?.results?.let { MoviesAdapter(requireContext(), it) }!!
                binding.popularRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.popularRecyclerview.adapter = moviesAdapter
            }

            override fun onFailure(call: Call<PopularMoviesModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getTopRatedMovies() {
        val call =
            RetrofitIstance.apiService.getTopRatedMovies(RetrofitIstance.apiKey, 1)

        call.enqueue(object : Callback<TopRatedMoviesModel> {
            override fun onResponse(
                call: Call<TopRatedMoviesModel>,
                response: Response<TopRatedMoviesModel>
            ) {
                binding.shimmerFrameLayoutTopRatedMovies.stopShimmer()
                binding.shimmerFrameLayoutTopRatedMovies.visibility = View.GONE
                binding.topRatedMovieRecyclerview.visibility = View.VISIBLE

                moviesAdapter =
                    response.body()?.results?.let { MoviesAdapter(requireContext(), it) }!!
                binding.topRatedMovieRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.topRatedMovieRecyclerview.adapter = moviesAdapter
            }

            override fun onFailure(call: Call<TopRatedMoviesModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getTrendingMoviesOfWeek() {
        val call =
            RetrofitIstance.apiService.getTrendingMoviesOfWeek(RetrofitIstance.apiKey, 1)

        call.enqueue(object : Callback<TrendingMoviesModel> {
            override fun onResponse(
                call: Call<TrendingMoviesModel>,
                response: Response<TrendingMoviesModel>
            ) {
                binding.shimmerFrameLayoutTrendingMovies.stopShimmer()
                binding.shimmerFrameLayoutTrendingMovies.visibility = View.GONE
                binding.trendingMoviesRecyclerview.visibility = View.VISIBLE

                moviesAdapter =
                    response.body()?.results?.let { MoviesAdapter(requireContext(), it) }!!
                binding.trendingMoviesRecyclerview.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.trendingMoviesRecyclerview.adapter = moviesAdapter
            }

            override fun onFailure(call: Call<TrendingMoviesModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getNowPlayingTVShows() {
        val call =
            RetrofitIstance.apiService.getNowPlayingTVShows(RetrofitIstance.apiKey, 1)

        call.enqueue(object : Callback<NowPlayingTVModel> {
            override fun onResponse(
                call: Call<NowPlayingTVModel>,
                response: Response<NowPlayingTVModel>
            ) {
                binding.shimmerFrameLayoutNowPlayingTV.stopShimmer()
                binding.shimmerFrameLayoutNowPlayingTV.visibility = View.GONE
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
            RetrofitIstance.apiService.getOngoingTVShows(RetrofitIstance.apiKey, 1)

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
            RetrofitIstance.apiService.getPopularTVShows(RetrofitIstance.apiKey, 1)

        call.enqueue(object : Callback<PopularTVModel> {
            override fun onResponse(
                call: Call<PopularTVModel>,
                response: Response<PopularTVModel>
            ) {
                binding.shimmerFrameLayoutPopularTV.stopShimmer()
                binding.shimmerFrameLayoutPopularTV.visibility = View.GONE
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
            RetrofitIstance.apiService.getTopRatedTVShows(RetrofitIstance.apiKey, 1)

        call.enqueue(object : Callback<TopRatedTVModel> {
            override fun onResponse(
                call: Call<TopRatedTVModel>,
                response: Response<TopRatedTVModel>
            ) {
                binding.shimmerFrameLayoutTopRatedTV.stopShimmer()
                binding.shimmerFrameLayoutTopRatedTV.visibility = View.GONE
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