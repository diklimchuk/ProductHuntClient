package com.a11eca.producthuntclient.presentation.livedata

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import io.reactivex.Flowable
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Adapter class which subscribes to [Flowable] which emits lists and collects them into single one.
 *
 * [clear] is a way to drop collected data.
 */
class LiveCollector<T> : ResourceSubscriber<List<T>>() {

  private val liveData = MutableLiveData<List<T>>()

  override fun onError(t: Throwable?) {}

  override fun onComplete() {}

  override fun onNext(t: List<T>) {
    if (liveData.value == null) {
      liveData.value = t
    } else {
      liveData.value = liveData.value!! + t
    }
  }

  /**
   * Same as [LiveData.observe].
   */
  fun observe(owner: LifecycleOwner, onNext: (List<T>) -> Unit) {
    liveData.observe(owner, Observer {
      data ->
      if (data != null) {
        onNext(data)
      }
    })
  }

  /**
   * Drops collected data.
   */
  fun clear() {
    if (liveData.value != null) {
      liveData.value = listOf<T>()
    }
  }
}