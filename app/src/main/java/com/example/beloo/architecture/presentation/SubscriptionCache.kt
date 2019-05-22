package com.example.beloo.architecture.presentation

import androidx.annotation.MainThread
import android.util.Log
import com.example.beloo.architecture.core.rx.RxMainThread
import io.reactivex.*
import io.reactivex.disposables.Disposable
import javax.inject.Inject

interface SubscriptionCache {
	@MainThread
	fun <A> Observable<A>.subscribeManaged(
		subscriptionTag: String,
		onSuccess: (A) -> Unit,
		onError: (Throwable) -> Unit)

	@MainThread
	fun <A> Single<A>.subscribeManaged(
		subscriptionTag: String,
		onSuccess: (A) -> Unit = {},
		onError: (Throwable) -> Unit = {})

	@MainThread
	fun <A> Maybe<A>.subscribeManaged(
		subscriptionTag: String,
		onSuccess: (A) -> Unit,
		onError: (Throwable) -> Unit)

	@MainThread
	fun Completable.subscribeManaged(
		subscriptionTag: String,
		onComplete: () -> Unit = {},
		onError: (Throwable) -> Unit = {})

	fun isSubscribed(subscriptionTag: String): Boolean

	fun clear()
}

class SubscriptionCacheImpl @Inject constructor(
	@RxMainThread
	private val scheduler: Scheduler
) : SubscriptionCache {

	override fun isSubscribed(subscriptionTag: String): Boolean = subscribedMap.contains(subscriptionTag)

	private val subscribedMap: MutableMap<String, Disposable> = HashMap()

	override fun <T> Observable<T>.subscribeManaged(
		subscriptionTag: String,
		onSuccess: (T) -> Unit,
		onError: (Throwable) -> Unit
	) {
		if (isSubscribed(subscriptionTag)) {
			subscribedMap.remove(subscriptionTag)?.dispose()
		}

		val disposable = this
			.observeOn(scheduler)
			.doOnTerminate { subscribedMap.remove(subscriptionTag) }
			.subscribe(onSuccess, {
				Log.w("SubscriptionCache", "rx error", it)
				onError(it)
			})
		subscribedMap[subscriptionTag] = disposable
	}

	override fun <T> Single<T>.subscribeManaged(
		subscriptionTag: String,
		onSuccess: (T) -> Unit,
		onError: (Throwable) -> Unit
	) = this.toObservable().subscribeManaged(subscriptionTag, onSuccess, onError)

	override fun Completable.subscribeManaged(
		subscriptionTag: String,
		onComplete: () -> Unit,
		onError: (Throwable) -> Unit
	) = this.toObservable<Any>().subscribeManaged(subscriptionTag, { onComplete() }, onError)

	override fun <T> Maybe<T>.subscribeManaged(
		subscriptionTag: String,
		onSuccess: (T) -> Unit,
		onError: (Throwable) -> Unit
	) = this.toObservable().subscribeManaged(subscriptionTag, onSuccess, onError)

	override fun clear() {
		subscribedMap.values.forEach(Disposable::dispose)
		subscribedMap.clear()
	}

}