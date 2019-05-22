package com.example.beloo.architecture.presentation

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class PresentationActivity : AppCompatActivity(), BaseView {

	@Inject
	internal lateinit var basePresenter: BasePresenter<BaseView>

	@CallSuper
	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidInjection.inject(this)
		super.onCreate(savedInstanceState)
	}

	override fun onStart() {
		super.onStart()
		basePresenter.bindView(this)
	}

	override fun onStop() {
		super.onStop()
		basePresenter.unbindView()
	}

	override fun onDestroy() {
		super.onDestroy()
		basePresenter.unbindView()
	}

	override fun showError(throwable: Throwable) {
		Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
	}

	override fun showError(@StringRes stringResource: Int) {
		Toast.makeText(this, stringResource, Toast.LENGTH_LONG).show()
	}
}
