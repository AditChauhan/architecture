package com.example.beloo.foodnixtest.presentation

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class PresentationFragment : androidx.fragment.app.Fragment(), BaseView {

	var rootView: View? = null
		private set

	@Inject
	internal lateinit var presenter: BasePresenter<BaseView>

	@get:LayoutRes
	abstract val layoutResId: Int

	override fun onAttach(context: Context?) {
		AndroidSupportInjection.inject(this)
		super.onAttach(context)
	}

	final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		rootView = LayoutInflater.from(context).inflate(layoutResId, container, false)
		return rootView
	}

	override fun onStart() {
		super.onStart()
		presenter.bindView(this)
	}

	override fun onStop() {
		super.onStop()
		presenter.unbindView()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		presenter.unbindView()
	}

	override fun showError(throwable: Throwable) {
		Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
	}

	override fun showError(@StringRes stringResource: Int) {
		Toast.makeText(context, stringResource, Toast.LENGTH_LONG).show()
	}
}
