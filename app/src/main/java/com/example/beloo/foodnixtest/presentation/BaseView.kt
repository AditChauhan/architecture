package com.example.beloo.foodnixtest.presentation

import androidx.annotation.StringRes

interface BaseView {
	fun showError(throwable: Throwable)
	fun showError(@StringRes stringResource: Int)
	fun showProgress()
	fun hideProgress()
}
