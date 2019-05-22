package com.example.beloo.architecture.presentation.util.recyclerView

import android.view.View
import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BindViewHolder<T>(view : View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view), Bind<T> {
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    var item: T? = null
        private set

    @CallSuper
    override fun bindItem(item: T, payloads: List<Any>) {
        this.item = item
    }

    protected fun manageSubscription(vararg disposable: Disposable) {
        compositeDisposable.addAll(*disposable)
    }

    override fun unbind() {
        item = null
        compositeDisposable.clear()
    }
}
