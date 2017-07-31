package com.a11eca.producthuntclient.presentation

import android.app.Application
import com.a11eca.producthuntclient.di.component.ApplicationComponent
import com.a11eca.producthuntclient.di.component.DaggerApplicationComponent

class PHCApplication : Application() {

  lateinit var applicationComponent: ApplicationComponent

  override fun onCreate() {
    super.onCreate()

    initializeInjector()
  }

  private fun initializeInjector() {
    this.applicationComponent = DaggerApplicationComponent.builder()
        //.applicationModule(ApplicationModule(this))
        .build()
  }
}