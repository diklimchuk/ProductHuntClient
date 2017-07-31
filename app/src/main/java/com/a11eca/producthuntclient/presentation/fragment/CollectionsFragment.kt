package com.a11eca.producthuntclient.presentation.fragment

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a11eca.producthuntclient.R
import com.a11eca.producthuntclient.databinding.FragmentCollectionsBinding
import com.a11eca.producthuntclient.presentation.PHCApplication
import com.a11eca.producthuntclient.presentation.viewmodel.CategoriesViewModel
import javax.inject.Inject

class CollectionsFragment : BaseFragment() {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory
  internal lateinit var categoriesViewModel: CategoriesViewModel
  internal lateinit var binding: FragmentCollectionsBinding

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_collections, container, false)

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    categoriesViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(CategoriesViewModel::class.java)

    (activity.application as PHCApplication).applicationComponent.inject(this)
  }
}