package com.picpay.desafio.android.ui.extension

import android.view.View
import androidx.core.view.isVisible

fun View.visible() {
    if (this.visibility != View.VISIBLE) this.isVisible = true
}

fun View.gone() {
    if (this.visibility != View.GONE) this.isVisible = false
}
