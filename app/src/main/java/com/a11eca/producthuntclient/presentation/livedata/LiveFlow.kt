package com.a11eca.producthuntclient.presentation.livedata

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import io.reactivex.Flowable
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Adapter class which subscribes to [Flowable] and notifies observers of the last element
 * emitted with onNext.
 */
class LiveFlow<T : Any> : ResourceSubscriber<T>() {


  private val liveData = MutableLiveData<T>()

  override fun onError(t: Throwable?) {}

  override fun onComplete() {}

  override fun onNext(t: T) {
    liveData.value = t
  }

  /**
   * Same as [LiveData.observe].
   */
  fun observe(owner: LifecycleOwner, onNext: (T) -> Unit) {
    liveData.observe(owner, Observer {
      data ->
      if (data != null) {
        onNext(data)
      }
    })
  }
}