package com.picpay.desafio.android.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemFavoriteUserBinding
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.User

class FavoriteListAdapter() : RecyclerView.Adapter<FavoriteListItemViewHolder>() {

    var favorites = emptyList<User>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(
                    field,
                    value
                )
            )
            notifyDataSetChanged()
            result.dispatchUpdatesTo(this)
            field = value
        }

    fun clear() {
        favorites = emptyList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListItemViewHolder {
        val binding =
            ListItemFavoriteUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteListItemViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    override fun getItemCount(): Int = favorites.size
}