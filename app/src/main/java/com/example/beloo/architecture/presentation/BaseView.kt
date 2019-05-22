package com.example.beloo.architecture.presentation

import androidx.annotation.StringRes

interface BaseView {
	fun showError(throwable: Throwable)
	fun showError(@StringRes stringResource: Int)
	fun showProgress()
	fun hideProgress()
}
