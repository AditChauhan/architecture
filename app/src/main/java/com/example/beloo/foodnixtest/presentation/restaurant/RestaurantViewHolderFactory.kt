package com.example.beloo.foodnixtest.presentation.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.beloo.foodnixtest.R
import com.example.beloo.foodnixtest.data.model.producer.Producer
import com.example.beloo.foodnixtest.presentation.util.recyclerView.BindViewHolder
import com.example.beloo.foodnixtest.presentation.util.recyclerView.ViewHolderFactory
import kotlinx.android.synthetic.main.item_restaurant.view.*

class RestaurantViewHolderFactory: ViewHolderFactory {

    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup?, viewType: Int): BindViewHolder<*> =
        RestaurantItem(inflater.inflate(R.layout.item_restaurant, parent, false))

}

private class RestaurantItem(view: View): BindViewHolder<Producer>(view) {

    private val ratingBar = view.ratingBar
    private val tvName = view.tvName
    private val tvFoodTypes = view.tvFoodTypes
    private val ivLogo = view.ivLogo

    override fun bindItem(item: Producer) {
        super.bindItem(item)
//        item.logo?.standardUrl?.let {
//            Glide.with(ivLogo.context)
//                .load(it)
//                .crossFade()
//                .into(ivLogo)
//        }
//
//        tvName.text = item.name
//        ratingBar.rating = item.ratingAverage
//        var foodTypes = ""
//        item.cuisineTypes.forEach {
//            foodTypes += "${it.name},"
//        }
//        foodTypes = foodTypes.removeSuffix(",")
//        tvFoodTypes.text = foodTypes
    }


}