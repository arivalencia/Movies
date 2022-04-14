package com.ari.movies.ui.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("textNumber")
fun setNumber(tv: TextView, number: Number?) {
    tv.text = "${number ?: "-"}"
}