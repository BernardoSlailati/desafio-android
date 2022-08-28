package com.picpay.desafio.android.ui.utils

import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.slailati.android.domain.model.User
import com.picpay.desafio.android.ui.extension.gone
import com.picpay.desafio.android.ui.extension.visible
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    private val itemBinding: ListItemUserBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(user: User, onIsFavoriteClickListener: (user: User, isFavorite: Boolean) -> Unit) {
        with(itemBinding) {
            tvName.text = user.name
            tvUserName.text = user.username
            ivIsFavorite.setImageResource(
                if (user.isFavorite) {
                    ivIsFavorite.tag = "favorited"
                    R.drawable.ic_star
                } else {
                    ivIsFavorite.tag = null
                    R.drawable.ic_star_outline
                }
            )
            ivIsFavorite.setOnClickListener {
                setIsFavoriteView()
                onIsFavoriteClickListener(user, it.tag != null)
            }
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

    private fun ListItemUserBinding.setIsFavoriteView() {
        when (ivIsFavorite.tag) {
            null -> {
                ivIsFavorite.tag = "favorited"
                ivIsFavorite.setImageResource(R.drawable.ic_star)
            }
            "favorited" -> {
                ivIsFavorite.tag = null
                ivIsFavorite.setImageResource(R.drawable.ic_star_outline)
            }
        }
    }
}