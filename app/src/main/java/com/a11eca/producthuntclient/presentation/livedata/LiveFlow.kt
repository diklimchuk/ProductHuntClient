package com.a11eca.producthuntclient.presentation.livedata

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Adapter class for which makes [LiveData] easier to use.
 */
class LiveFlow<T : Any> : ResourceSubscriber<T>(), LiveItems<T> {

  private val liveData = MutableLiveData<T>()

  override fun onError(t: Throwable?) {}

  override fun onComplete() {}

  override fun onNext(t: T) {
    liveData.value = t
  }

  override fun observe(owner: LifecycleOwner, onNext: (T) -> Unit) {
    liveData.observe(owner, Observer {
      data ->
      if (data != null) {
        onNext(data)
      }
    })
  }
}

class LiveCollector<T> : ResourceSubscriber<List<T>>(), LiveItems<List<T>> {

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

  override fun observe(owner: LifecycleOwner, onNext: (List<T>) -> Unit) {
    liveData.observe(owner, Observer {
      data ->
      if (data != null) {
        onNext(data)
      }
    })
  }

  fun clear() {
    if (liveData.value != null) {
      liveData.value = listOf<T>()
    }
  }
}