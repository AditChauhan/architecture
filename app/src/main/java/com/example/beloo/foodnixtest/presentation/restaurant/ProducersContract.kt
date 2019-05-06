package com.example.beloo.foodnixtest.presentation.restaurant

import com.example.beloo.foodnixtest.data.model.producer.Producer
import com.example.beloo.foodnixtest.presentation.BasePresenter
import com.example.beloo.foodnixtest.presentation.BaseView

interface ProducersContract {

    @JvmSuppressWildcards
    interface Presenter: BasePresenter<View> {
        fun onLoadNextPage()
    }

    interface View : BaseView {
        fun setData(producers: List<Producer>)
        fun setPostCode(postCode: String)
    }

    interface Router
}