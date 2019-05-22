package com.example.beloo.architecture.presentation

interface BasePresenter<View : BaseView> {
	val isViewAttached: Boolean
	fun bindView(view: View)
	fun unbindView()

	val view: View
}
