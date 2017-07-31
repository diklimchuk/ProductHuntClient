package com.a11eca.producthuntclient.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Base class for ViewModels.
 *
 * Disposes disposables when destroyed.
 */
open class BaseViewModel : ViewModel() {

  protected val disposables = CompositeDisposable()

  override fun onCleared() {
    super.onCleared()

    disposables.dispose()
  }
}