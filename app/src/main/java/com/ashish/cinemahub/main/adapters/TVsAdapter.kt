package com.ashish.cinemahub.main.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashish.cinemahub.R
import com.ashish.cinemahub.data.models.tvs.Result
import com.ashish.cinemahub.databinding.TileItemBinding
import com.ashish.cinemahub.main.activities.TVShowDetailsActivity
import com.squareup.picasso.Picasso

class TVsAdapter(
    var context: Context,
    private var list: List<Result>
) :
    RecyclerView.Adapter<TVsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val tileItemBinding = TileItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(tileItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context,list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private var tileItemBinding: TileItemBinding) :
        RecyclerView.ViewHolder(tileItemBinding.root) {
        fun bind(context: Context, result: Result) {
            val imgurl = "https://image.tmdb.org/t/p/w500${result.poster_path}"
            Picasso.get().load(imgurl).fit().placeholder(R.drawable.pic)
                .into(tileItemBinding.image)
            if (result.name.length>15){
                tileItemBinding.title.text = "${result.name.subSequence(0,12)} ..."
            }else {
                tileItemBinding.title.text = result.name
            }
            itemView.setOnClickListener {
                context.startActivity(
                    Intent(context, TVShowDetailsActivity::class.java).putExtra("id", result.id)
                )
            }
        }

    }
}