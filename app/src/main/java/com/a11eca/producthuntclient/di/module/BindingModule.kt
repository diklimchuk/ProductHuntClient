package com.a11eca.producthuntclient.di.module

import android.arch.lifecycle.ViewModelProvider
import com.a11eca.producthuntclient.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class BindingModule {

  @Binds
  @Singleton
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}