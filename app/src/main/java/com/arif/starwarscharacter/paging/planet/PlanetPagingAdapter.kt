package com.arif.starwarscharacter.paging.planet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arif.starwarscharacter.R
import com.arif.starwarscharacter.models.planet.PlanetResult


class PlanetPagingAdapter(private val Callback: (PlanetResult) -> Unit) :
    PagingDataAdapter<PlanetResult, PlanetPagingAdapter.PlanetViewHolder>(COMPARATOR) {

    class PlanetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quote = itemView.findViewById<TextView>(R.id.nameList)
        val cardView = itemView.findViewById<CardView>(R.id.cardViewId)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.quote.text = item.name
            holder.cardView.setOnClickListener {
                Callback(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_layout, parent, false)
        return PlanetViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PlanetResult>() {
            override fun areItemsTheSame(oldItem: PlanetResult, newItem: PlanetResult): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PlanetResult, newItem: PlanetResult): Boolean {
                return oldItem == newItem
            }
        }
    }
}





















