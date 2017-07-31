package com.a11eca.producthuntclient.di.component

import com.a11eca.producthuntclient.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
}