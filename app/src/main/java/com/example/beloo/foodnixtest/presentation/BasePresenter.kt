package com.example.beloo.foodnixtest.presentation

interface BasePresenter<View : BaseView> {
	val isViewAttached: Boolean
	fun bindView(view: View)
	fun unbindView()

	val view: View
}
