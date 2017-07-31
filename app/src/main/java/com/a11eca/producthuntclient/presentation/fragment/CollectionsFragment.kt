package com.a11eca.producthuntclient.presentation.fragment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.SpinnerAdapter
import com.a11eca.producthuntclient.R
import com.a11eca.producthuntclient.databinding.FragmentCollectionsBinding
import com.a11eca.producthuntclient.presentation.PHCApplication
import com.a11eca.producthuntclient.presentation.entity.CategoriesData
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

    (activity.application as PHCApplication).applicationComponent.inject(this)

    categoriesViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(CategoriesViewModel::class.java)

    categoriesViewModel.getCategories().observe(this, this::showCategories, {}, {})
  }

  fun showCategories(data: CategoriesData) {
    binding.categories.adapter = createCategoriesAdapter(data)
  }

  fun createCategoriesAdapter(data: CategoriesData): SpinnerAdapter {
    val adapter = SimpleAdapter(context, data.data,
        android.R.layout.simple_spinner_item, data.from, intArrayOf(android.R.id.text1))
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    return adapter
  }
}