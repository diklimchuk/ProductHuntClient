package com.a11eca.producthuntclient.presentation.livedata

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Interface for RxJava-style [LiveData] implementations.
 */
interface LiveItems<T> {

  /**
   * Similar to [android.arch.lifecycle.LiveData.observe()]
   */
  fun observe(owner: LifecycleOwner, onNext: (T) -> Unit, onComplete: () -> Unit,
              onError: (t: Throwable) -> Unit)
}

/**
 * Adapter class for which makes [LiveData] easier to use.
 */
class LiveFlow<T : Any> : ResourceSubscriber<T>(), LiveItems<T> {

  private val liveData = MutableLiveData<Item<T>>()

  override fun onError(e: Throwable) {
    liveData.value = Item.error<T>(e)
  }

  override fun onComplete() {
    liveData.value = Item.complete<T>()
  }

  override fun onNext(t: T) {
    liveData.value = Item.next(t)
  }

  override fun observe(owner: LifecycleOwner, onNext: (T) -> Unit, onComplete: (() -> Unit),
                       onError: ((t: Throwable) -> Unit)) {
    liveData.observe(owner, Observer {
      data ->
      if (data != null) {
        if (!data.isSuccessful) {
          onError(data.throwable)
        } else if (data.isFinished) {
          onComplete()
        } else {
          onNext(data.item)
        }
      }
    })
  }
}


private class Item<T : Any> private constructor(
    val isSuccessful: Boolean,
    val isFinished: Boolean = false
) {

  lateinit var throwable: Throwable
    private set

  lateinit var item: T

  companion object {
    fun <T : Any> complete(): Item<T> {
      val result = Item<T>(true, true)
      return result
    }

    fun <T : Any> next(data: T): Item<T> {
      val result = Item<T>(true)
      result.item = data
      return result
    }

    fun <T : Any> error(t: Throwable): Item<T> {
      val result = Item<T>(false)
      result.throwable = t
      return result
    }
  }
}