package com.a11eca.producthuntclient.di.module

import android.arch.lifecycle.ViewModelProvider
import com.a11eca.producthuntclient.data.repo.CategoryRepoImpl
import com.a11eca.producthuntclient.domain.repo.CategoryRepo
import com.a11eca.producthuntclient.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class BindingModule {

  @Binds
  abstract fun bindCategoryRepo(repo: CategoryRepoImpl): CategoryRepo

  @Binds
  @Singleton
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}