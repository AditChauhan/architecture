package com.example.beloo.foodnixtest.presentation

abstract class AbstractPresenter<View : BaseView>(
	private val s: SubscriptionCache
) : BasePresenter<View>, SubscriptionCache by s {
	private var _view: View? = null

	override val view: View
		get() {
			checkViewAttachedAndThrow()
			return _view!!
		}

	override val isViewAttached: Boolean
		get() = _view != null

	protected abstract fun onReady()

	override fun bindView(view: View) {
		this._view = view
		onReady()
	}

	override fun unbindView() {
		this._view = null

		s.clear()
	}

	private fun checkViewAttachedAndThrow() {
		if (!isViewAttached) throw MVPViewNotAttachedException()
	}

	internal fun onError(throwable: Throwable) {
		_view?.showError(throwable)
	}

	private class MVPViewNotAttachedException internal constructor() :
		RuntimeException("Presenter's #bindView() have to be called before")
}