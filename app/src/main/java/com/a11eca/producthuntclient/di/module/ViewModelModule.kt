package com.a11eca.producthuntclient.di.module

import android.arch.lifecycle.ViewModel
import com.a11eca.producthuntclient.di.ViewModelKey
import com.a11eca.producthuntclient.presentation.viewmodel.CategoriesViewModel
import com.a11eca.producthuntclient.presentation.viewmodel.PostViewModel
import com.a11eca.producthuntclient.presentation.viewmodel.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(PostsViewModel::class)
  abstract fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(CategoriesViewModel::class)
  abstract fun bindCategoriesViewModel(viewModel: CategoriesViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(PostViewModel::class)
  abstract fun bindPostViewModel(viewModel: PostViewModel): ViewModel
}