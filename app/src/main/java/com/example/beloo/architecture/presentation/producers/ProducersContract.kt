package com.example.beloo.architecture.presentation.producers

import com.example.beloo.architecture.data.model.producer.Producer
import com.example.beloo.architecture.presentation.BasePresenter
import com.example.beloo.architecture.presentation.BaseView

interface ProducersContract {

    @JvmSuppressWildcards
    interface Presenter: BasePresenter<View> {
        fun onLoadNextPage()
    }

    interface View : BaseView {
        fun setData(producers: List<Producer>)
        fun setItemsLoadedCompletely()
    }

    interface Router
}