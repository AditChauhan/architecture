package com.example.beloo.foodnixtest.presentation.util.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup

/** simple adapter for using with ViewHolderFactory. It helps to not create concrete adapter in every case
 * @see [ViewHolderFactory] */
class FactoryAdapter<T>(
        private val itemFactory: ViewHolderFactory) : BaseRecyclerViewAdapter<T>() {

    override fun getItemViewType(item: T): Int = itemFactory.getItemViewType(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder<T> =
        itemFactory.createViewHolder(LayoutInflater.from(parent.context), parent, viewType) as BindViewHolder<T>

}