package com.arif.starwarscharacter.paging.starship

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arif.starwarscharacter.R
import com.arif.starwarscharacter.models.starship.StarshipResult


class StarshipPagingAdapter(private val Callback: (StarshipResult) -> Unit) :
    PagingDataAdapter<StarshipResult, StarshipPagingAdapter.StarshipViewHolder>(COMPARATOR) {

    class StarshipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quote = itemView.findViewById<TextView>(R.id.nameList)
        val cardView = itemView.findViewById<CardView>(R.id.cardViewId)
    }

    override fun onBindViewHolder(holder: StarshipViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.quote.text = item.name
            holder.cardView.setOnClickListener {
                Callback(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarshipViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_layout, parent, false)
        return StarshipViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<StarshipResult>() {
            override fun areItemsTheSame(oldItem: StarshipResult, newItem: StarshipResult): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: StarshipResult, newItem: StarshipResult): Boolean {
                return oldItem == newItem
            }
        }
    }
}





















