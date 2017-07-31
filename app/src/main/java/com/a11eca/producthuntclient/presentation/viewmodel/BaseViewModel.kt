package com.a11eca.producthuntclient.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Base class for ViewModels.
 *
 * Disposes disposables in [onCleared].
 */
open class BaseViewModel : ViewModel() {

  private val disposables: CompositeDisposable = CompositeDisposable()

  protected fun <T : Disposable> addLocalScopeDisposable(disposable: T): T {
    disposables.add(disposable)
    return disposable
  }

  override fun onCleared() {
    super.onCleared()

    disposables.clear()
  }
}