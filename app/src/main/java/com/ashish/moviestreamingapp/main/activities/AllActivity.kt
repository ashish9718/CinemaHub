package com.ashish.moviestreamingapp.main.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashish.moviestreamingapp.data.api.RetrofitIstance
import com.ashish.moviestreamingapp.data.models.movies.*
import com.ashish.moviestreamingapp.data.models.tvs.*
import com.ashish.moviestreamingapp.databinding.ActivityAllBinding
import com.ashish.moviestreamingapp.main.adapters.MoviesAdapter
import com.ashish.moviestreamingapp.main.adapters.TVsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var tvsAdapter: TVsAdapter
    var page = 1
    var scroll = false
    private lateinit var manager: GridLayoutManager
    private var listofMovie = ArrayList<com.ashish.moviestreamingapp.data.models.movies.Result>()
    private var listofTv = ArrayList<com.ashish.moviestreamingapp.data.models.tvs.Result>()
    var currentItems = 0
    var totalItems = 0
    var scrollOutItems = 0
    private lateinit var type: String
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        type = intent.getStringExtra("type")!!
        name = intent.getStringExtra("name")!!

        binding.name.text = name

        loadData(page)

        manager =
            GridLayoutManager(this@AllActivity, 2)
        binding.rv.layoutManager = manager

        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    scroll = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = manager.childCount //totalItems on screen
                totalItems = manager.itemCount    //total item count
                scrollOutItems = manager.findFirstVisibleItemPosition()  //gone out

                if (scroll && (scrollOutItems + currentItems == totalItems)) {
                    scroll = false
                    loadData(page++)
                    Log.d(
                        "TAG",
                        "onScrolled: $page , $scrollOutItems + $currentItems == $totalItems"
                    )
                }
            }

        })
    }

    private fun loadData(page: Int) {
        binding.progressBar.visibility = View.VISIBLE
        when (name) {
            "Trending Movies" -> {
                getTrendingMoviesOfWeek(page)
            }
            "Trending TV Shows" -> {
                getTrendingTVShowOfWeek(page)
            }
            "Top Rated Movies" -> {
                getTopRatedMovies(page)

            }
            "Top Rated TV Shows" -> {
                getTopRatedTVShows(page)

            }
            "Popular Movies" -> {
                getPopularMovies(page)

            }
            "Popular TV Shows" -> {
                getPopularTVShows(page)

            }
            "Upcoming Movies" -> {
                getUpcomingMovies(page)

            }
            "Ongoing TV Shows" -> {
                getOngoingTVShows(page)

            }
            "Now Playing Movies" -> {
                getNowPlayingMovies(page)

            }
            "Now Playing TV Shows" -> {
                getNowPlayingTVShows(page)

            }
        }
    }


    private fun getNowPlayingMovies(page: Int) {
        val call =
            RetrofitIstance.apiService.getNowPlayingMovies(RetrofitIstance.apiKey, page)

        call.enqueue(object : Callback<NowPlayingMoviesModel> {
            override fun onResponse(
                call: Call<NowPlayingMoviesModel>,
                response: Response<NowPlayingMoviesModel>
            ) {
                response.body()?.results?.let {
                    listofMovie.addAll(it)
                    binding.progressBar.visibility = View.GONE

                    if (page == 1) {
                        moviesAdapter = MoviesAdapter(this@AllActivity, listofMovie)
                        binding.rv.adapter = moviesAdapter
                    } else {
                        moviesAdapter = MoviesAdapter(this@AllActivity, listofMovie)
                        moviesAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<NowPlayingMoviesModel>, t: Throwable) {
            }

        })
    }

    private fun getUpcomingMovies(page: Int) {
        val call =
            RetrofitIstance.apiService.getUpcomingMovies(RetrofitIstance.apiKey, page)

        call.enqueue(object : Callback<UpcomingMoviesModel> {
            override fun onResponse(
                call: Call<UpcomingMoviesModel>,
                response: Response<UpcomingMoviesModel>
            ) {
                response.body()?.results?.let {
                    listofMovie.addAll(it)
                    binding.progressBar.visibility = View.GONE

                    if (page == 1) {
                        moviesAdapter = MoviesAdapter(this@AllActivity, listofMovie)
                        binding.rv.adapter = moviesAdapter
                    } else {
                        moviesAdapter = MoviesAdapter(this@AllActivity, listofMovie)
                        moviesAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<UpcomingMoviesModel>, t: Throwable) {
            }

        })
    }

    private fun getPopularMovies(page: Int) {
        val call =
            RetrofitIstance.apiService.getPopularMovies(RetrofitIstance.apiKey, page)

        call.enqueue(object : Callback<PopularMoviesModel> {
            override fun onResponse(
                call: Call<PopularMoviesModel>,
                response: Response<PopularMoviesModel>
            ) {
                response.body()?.results?.let {
                    listofMovie.addAll(it)
                    binding.progressBar.visibility = View.GONE

                    if (page == 1) {
                        moviesAdapter = MoviesAdapter(this@AllActivity, listofMovie)
                        binding.rv.adapter = moviesAdapter
                    } else {
                        moviesAdapter = MoviesAdapter(this@AllActivity, listofMovie)
                        moviesAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<PopularMoviesModel>, t: Throwable) {
            }

        })
    }

    private fun getTopRatedMovies(page: Int) {
        val call =
            RetrofitIstance.apiService.getTopRatedMovies(RetrofitIstance.apiKey, page)

        call.enqueue(object : Callback<TopRatedMoviesModel> {
            override fun onResponse(
                call: Call<TopRatedMoviesModel>,
                response: Response<TopRatedMoviesModel>
            ) {
                response.body()?.results?.let {
                    listofMovie.addAll(it)
                    binding.progressBar.visibility = View.GONE

                    if (page == 1) {
                        moviesAdapter = MoviesAdapter(this@AllActivity, listofMovie)
                        binding.rv.adapter = moviesAdapter
                    } else {
                        moviesAdapter = MoviesAdapter(this@AllActivity, listofMovie)
                        moviesAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<TopRatedMoviesModel>, t: Throwable) {
            }

        })
    }

    private fun getTrendingMoviesOfWeek(page: Int) {
        val call =
            RetrofitIstance.apiService.getTrendingMoviesOfWeek(RetrofitIstance.apiKey, page)

        call.enqueue(object : Callback<TrendingMoviesModel> {
            override fun onResponse(
                call: Call<TrendingMoviesModel>,
                response: Response<TrendingMoviesModel>
            ) {
                response.body()?.results?.let {
                    listofMovie.addAll(it)
                    binding.progressBar.visibility = View.GONE

                    if (page == 1) {
                        moviesAdapter = MoviesAdapter(this@AllActivity, listofMovie)
                        binding.rv.adapter = moviesAdapter
                    } else {
                        moviesAdapter = MoviesAdapter(this@AllActivity, listofMovie)
                        moviesAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<TrendingMoviesModel>, t: Throwable) {
            }

        })
    }

    private fun getTrendingTVShowOfWeek(page: Int) {

        val call =
            RetrofitIstance.apiService.getTrendingTVShowOfWeek(RetrofitIstance.apiKey, page)

        call.enqueue(object : Callback<TrendingTVModel> {
            override fun onResponse(
                call: Call<TrendingTVModel>,
                response: Response<TrendingTVModel>
            ) {
                response.body()?.results?.let {
                    listofTv.addAll(it)
                    binding.progressBar.visibility = View.GONE

                    if (page == 1) {
                        tvsAdapter = TVsAdapter(this@AllActivity, listofTv)
                        binding.rv.adapter = tvsAdapter
                    } else {
                        tvsAdapter = TVsAdapter(this@AllActivity, listofTv)
                        tvsAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<TrendingTVModel>, t: Throwable) {
            }

        })
    }

    private fun getNowPlayingTVShows(page: Int) {
        val call =
            RetrofitIstance.apiService.getNowPlayingTVShows(RetrofitIstance.apiKey, page)

        call.enqueue(object : Callback<NowPlayingTVModel> {
            override fun onResponse(
                call: Call<NowPlayingTVModel>,
                response: Response<NowPlayingTVModel>
            ) {
                response.body()?.results?.let {
                    listofTv.addAll(it)
                    binding.progressBar.visibility = View.GONE

                    if (page == 1) {
                        tvsAdapter = TVsAdapter(this@AllActivity, listofTv)
                        binding.rv.adapter = tvsAdapter
                    } else {
                        tvsAdapter = TVsAdapter(this@AllActivity, listofTv)
                        tvsAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<NowPlayingTVModel>, t: Throwable) {
            }

        })
    }

    private fun getOngoingTVShows(page: Int) {
        val call =
            RetrofitIstance.apiService.getOngoingTVShows(RetrofitIstance.apiKey, page)

        call.enqueue(object : Callback<OngoingTVModel> {
            override fun onResponse(
                call: Call<OngoingTVModel>,
                response: Response<OngoingTVModel>
            ) {
                response.body()?.results?.let {
                    listofTv.addAll(it)
                    binding.progressBar.visibility = View.GONE

                    if (page == 1) {
                        tvsAdapter = TVsAdapter(this@AllActivity, listofTv)
                        binding.rv.adapter = tvsAdapter
                    } else {
                        tvsAdapter = TVsAdapter(this@AllActivity, listofTv)
                        tvsAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<OngoingTVModel>, t: Throwable) {
            }

        })
    }

    private fun getPopularTVShows(page: Int) {
        val call =
            RetrofitIstance.apiService.getPopularTVShows(RetrofitIstance.apiKey, page)

        call.enqueue(object : Callback<PopularTVModel> {
            override fun onResponse(
                call: Call<PopularTVModel>,
                response: Response<PopularTVModel>
            ) {
                response.body()?.results?.let {
                    listofTv.addAll(it)
                    binding.progressBar.visibility = View.GONE

                    if (page == 1) {
                        tvsAdapter = TVsAdapter(this@AllActivity, listofTv)
                        binding.rv.adapter = tvsAdapter
                    } else {
                        tvsAdapter = TVsAdapter(this@AllActivity, listofTv)
                        tvsAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<PopularTVModel>, t: Throwable) {
            }

        })
    }

    private fun getTopRatedTVShows(page: Int) {
        val call =
            RetrofitIstance.apiService.getTopRatedTVShows(RetrofitIstance.apiKey, page)

        call.enqueue(object : Callback<TopRatedTVModel> {
            override fun onResponse(
                call: Call<TopRatedTVModel>,
                response: Response<TopRatedTVModel>
            ) {
                response.body()?.results?.let {
                    listofTv.addAll(it)
                    binding.progressBar.visibility = View.GONE

                    if (page == 1) {
                        tvsAdapter = TVsAdapter(this@AllActivity, listofTv)
                        binding.rv.adapter = tvsAdapter
                    } else {
                        tvsAdapter = TVsAdapter(this@AllActivity, listofTv)
                        tvsAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<TopRatedTVModel>, t: Throwable) {
            }

        })
    }

}