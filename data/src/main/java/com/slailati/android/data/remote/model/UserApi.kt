package com.slailati.android.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserApi(
    @SerializedName("img") val img: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("username") val username: String?
) : Parcelable