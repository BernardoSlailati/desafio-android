package com.picpay.desafio.android.ui.utils

import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemFavoriteUserBinding
import com.slailati.android.domain.model.User
import com.picpay.desafio.android.ui.extension.gone
import com.picpay.desafio.android.ui.extension.visible
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class FavoriteListItemViewHolder(
    private val itemBinding: ListItemFavoriteUserBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(user: User) {
        with(itemBinding) {
            tvName.text = user.name
            tvUserName.text = user.username
            pbLoadingProfileImage.visible()
            Picasso.get()
                .load(user.img)
                .error(R.drawable.ic_round_account_circle)
                .into(civProfile, object : Callback {
                    override fun onSuccess() {
                        pbLoadingProfileImage.gone()
                    }

                    override fun onError(e: Exception?) {
                        pbLoadingProfileImage.gone()
                    }
                })
        }

    }

}