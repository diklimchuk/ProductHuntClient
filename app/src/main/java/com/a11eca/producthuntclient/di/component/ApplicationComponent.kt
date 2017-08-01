package com.a11eca.producthuntclient.di.component

import android.arch.lifecycle.ViewModelProvider
import com.a11eca.producthuntclient.di.module.ApplicationModule
import com.a11eca.producthuntclient.di.module.BindingModule
import com.a11eca.producthuntclient.di.module.ViewModelModule
import com.a11eca.producthuntclient.presentation.fragment.PostFragment
import com.a11eca.producthuntclient.presentation.fragment.PostsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    ApplicationModule::class,
    BindingModule::class,
    ViewModelModule::class
))
interface ApplicationComponent {

  fun inject(fragment: PostsFragment)

  fun inject(fragment: PostFragment)

  fun viewModelFactory(viewModelFactory: ViewModelProvider.Factory): ViewModelProvider.Factory
}