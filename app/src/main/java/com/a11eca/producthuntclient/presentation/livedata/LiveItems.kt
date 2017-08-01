package com.a11eca.producthuntclient.presentation.livedata

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData

/**
 * Interface for RxJava-style [LiveData] implementations.
 */
interface LiveItems<T> {

  /**
   * Similar to [android.arch.lifecycle.LiveData.observe()]
   */
  fun observe(owner: LifecycleOwner, onNext: (T) -> Unit)
}