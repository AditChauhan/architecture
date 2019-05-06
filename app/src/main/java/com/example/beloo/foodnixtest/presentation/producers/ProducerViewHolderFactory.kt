package com.example.beloo.foodnixtest.presentation.producers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.beloo.foodnixtest.R
import com.example.beloo.foodnixtest.data.model.producer.Producer
import com.example.beloo.foodnixtest.data.model.producer.firstImage
import com.example.beloo.foodnixtest.presentation.util.recyclerView.BindViewHolder
import com.example.beloo.foodnixtest.presentation.util.recyclerView.ViewHolderFactory
import kotlinx.android.synthetic.main.item_producer.view.*

class ProducerViewHolderFactory: ViewHolderFactory {

    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup?, viewType: Int): BindViewHolder<*> =
        ProducerItem(inflater.inflate(R.layout.item_producer, parent, false))

}

private class ProducerItem(view: View): BindViewHolder<Producer>(view) {
    private val tvName = view.tvName
    private val tvShortDescription = view.tvShortDescription
    private val ivLogo = view.ivLogo

    override fun bindItem(item: Producer) {
        super.bindItem(item)
        item.firstImage?.path?.let {
            Glide.with(ivLogo.context)
                .load(it)
                .crossFade()
                .into(ivLogo)
        }
        tvName.text = item.name
        tvShortDescription.text = item.shortDescription
    }

}