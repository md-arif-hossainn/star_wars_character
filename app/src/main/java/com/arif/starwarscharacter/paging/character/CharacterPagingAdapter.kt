package com.arif.starwarscharacter.paging.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arif.starwarscharacter.R
import com.arif.starwarscharacter.models.character.CharacterResult


class CharacterPagingAdapter(private val Callback: (CharacterResult) -> Unit) :
    PagingDataAdapter<CharacterResult, CharacterPagingAdapter.CharacterViewHolder>(COMPARATOR) {

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quote = itemView.findViewById<TextView>(R.id.nameList)
        val cardView = itemView.findViewById<CardView>(R.id.cardViewId)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.quote.text = item.name
            holder.cardView.setOnClickListener {
                Callback(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_layout, parent, false)
        return CharacterViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CharacterResult>() {
            override fun areItemsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {
                return oldItem == newItem
            }
        }
    }
}





















