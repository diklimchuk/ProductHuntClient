package com.a11eca.producthuntclient.di.component

import com.a11eca.producthuntclient.di.module.ApplicationModule
import com.a11eca.producthuntclient.di.module.BindingModule
import com.a11eca.producthuntclient.di.module.ViewModelModule
import com.a11eca.producthuntclient.presentation.fragment.CollectionsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    ApplicationModule::class,
    BindingModule::class,
    ViewModelModule::class
))
interface ApplicationComponent {

  fun inject(fragment: CollectionsFragment)
}