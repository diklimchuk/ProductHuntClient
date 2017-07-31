package com.a11eca.producthuntclient.presentation.fragment

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a11eca.producthuntclient.presentation.PHCApplication
import com.a11eca.producthuntclient.presentation.viewmodel.CategoriesViewModel
import javax.inject.Inject

class CollectionsFragment : BaseFragment() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  internal lateinit var categoriesViewModel: CategoriesViewModel

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    (activity.application as PHCApplication).applicationComponent.inject(this)

    categoriesViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(CategoriesViewModel::class.java)

    return null
  }
}