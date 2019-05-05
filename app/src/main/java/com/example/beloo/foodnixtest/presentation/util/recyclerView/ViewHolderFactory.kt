package com.example.beloo.foodnixtest.presentation.util.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup

interface ViewHolderFactory {
    fun <T> getItemViewType(item: T): Int = 0
    fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup?, viewType: Int = 0): BindViewHolder<*>
}
