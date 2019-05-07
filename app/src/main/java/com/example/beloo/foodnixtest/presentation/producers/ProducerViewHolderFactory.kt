package com.example.beloo.foodnixtest.presentation.producers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.beloo.foodnixtest.R
import com.example.beloo.foodnixtest.data.model.producer.Producer
import com.example.beloo.foodnixtest.data.model.producer.firstImage
import com.example.beloo.foodnixtest.presentation.util.recyclerView.BindViewHolder
import com.example.beloo.foodnixtest.presentation.util.recyclerView.ViewHolderFactory
import kotlinx.android.synthetic.main.item_producer.view.*

class ProducerViewHolderFactory : ViewHolderFactory {

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        viewType: Int
    ): BindViewHolder<*> =
        ProducerItem(inflater.inflate(R.layout.item_producer, parent, false))

    fun createDiffUtilCallback(): DiffUtil.ItemCallback<Producer> =
        object : DiffUtil.ItemCallback<Producer>() {
            override fun areItemsTheSame(oldItem: Producer, newItem: Producer): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Producer, newItem: Producer): Boolean =
                oldItem == newItem

            override fun getChangePayload(oldItem: Producer, newItem: Producer): Any? {
                val payloads = mutableListOf<Any>()

                if (oldItem.name != newItem.name) {
                    payloads += Payload.NamePayload
                }

                if (oldItem.shortDescription != newItem.shortDescription) {
                    payloads += Payload.ShortDescriptionPayload
                }

                if (oldItem.firstImage != newItem.firstImage) {
                    payloads += Payload.ImagePayload
                }

                return payloads
            }
        }
}

sealed class Payload {
    object NamePayload : Payload()
    object ShortDescriptionPayload : Payload()
    object ImagePayload : Payload()
}

private class ProducerItem(view: View) : BindViewHolder<Producer>(view) {
    private val tvName = view.tvName
    private val tvShortDescription = view.tvShortDescription
    private val ivLogo = view.ivLogo

    private fun setShortDescription(item: Producer) {
        tvShortDescription.apply {
            text = item.shortDescription
            visibility =
                if (tvShortDescription.text.isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun setImage(item: Producer) {
        item.firstImage?.path.let {
            Glide.with(ivLogo.context)
                .load(it)
                .crossFade()
                .into(ivLogo)
        }
    }

    override fun bindItem(item: Producer, payloads: List<Any>) {
        super.bindItem(item, payloads)
        if (payloads.isEmpty()) {
            setImage(item)
            tvName.text = item.name
            setShortDescription(item)
        } else {
            payloads
                .mapNotNull { it as? Payload }
                .forEach {
                    when (it) {
                        is Payload.NamePayload -> {
                            tvName.text = item.name
                        }
                        is Payload.ShortDescriptionPayload -> {
                            setShortDescription(item)
                        }
                        is Payload.ImagePayload -> {
                            setImage(item)
                        }
                    }
                }

        }
    }

}