package com.ashish.cinemahub.main.activities

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.ashish.cinemahub.data.api.RetrofitIstance
import com.ashish.cinemahub.data.models.SearchModel
import com.ashish.cinemahub.databinding.ActivitySearchBinding
import com.ashish.cinemahub.main.adapters.SearchAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "SearchActivity"

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    getSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    getSearch(newText)
                }
                return true
            }
        })

    }

    private fun getSearch(query: String) {

        val call = RetrofitIstance.apiService.getSearch(RetrofitIstance.apiKey, query)

        call.enqueue(object : Callback<SearchModel> {
            override fun onResponse(call: Call<SearchModel>, response: Response<SearchModel>) {
                if (response.body() != null) {
                    adapter =
                        response.body()?.results?.let { SearchAdapter(this@SearchActivity, it) }!!
                    binding.searchRv.layoutManager =
                        GridLayoutManager(this@SearchActivity, 3)
                    binding.searchRv.adapter = adapter
                }
            }

            override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }
}