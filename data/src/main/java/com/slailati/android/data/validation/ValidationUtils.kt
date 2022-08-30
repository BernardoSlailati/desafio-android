package com.slailati.android.data.validation

import com.slailati.android.data.remote.model.UserApi

fun List<UserApi?>?.validate(): List<UserApi> =
    this?.filterNotNull() ?: emptyList()