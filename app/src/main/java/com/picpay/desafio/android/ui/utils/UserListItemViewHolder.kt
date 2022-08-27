package com.picpay.desafio.android.ui.utils

import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.remote.model.UserApi
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    private val itemBinding: ListItemUserBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(user: User) {
        with(itemBinding) {
            tvName.text = user.name
            tvUserName.text = user.username
            pbLoadingProfileImage.visibility = android.view.View.VISIBLE
            Picasso.get()
                .load(user.img)
                .error(R.drawable.ic_round_account_circle)
                .into(civProfile, object : Callback {
                    override fun onSuccess() {
                        pbLoadingProfileImage.visibility = android.view.View.GONE
                    }

                    override fun onError(e: Exception?) {
                        pbLoadingProfileImage.visibility = android.view.View.GONE
                    }
                })
        }

    }
}