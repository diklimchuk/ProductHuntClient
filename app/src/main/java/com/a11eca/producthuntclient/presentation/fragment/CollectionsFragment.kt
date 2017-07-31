package com.a11eca.producthuntclient.presentation.fragment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SimpleAdapter
import android.widget.SpinnerAdapter
import com.a11eca.producthuntclient.R
import com.a11eca.producthuntclient.databinding.FragmentCollectionsBinding
import com.a11eca.producthuntclient.domain.entity.ProductCollection
import com.a11eca.producthuntclient.presentation.PHCApplication
import com.a11eca.producthuntclient.presentation.entity.CategoriesData
import com.a11eca.producthuntclient.presentation.livedata.LiveFlow
import com.a11eca.producthuntclient.presentation.viewmodel.CategoriesViewModel
import javax.inject.Inject

class CollectionsFragment : BaseFragment(), AdapterView.OnItemSelectedListener {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory
  internal lateinit var categoriesViewModel: CategoriesViewModel
  internal lateinit var binding: FragmentCollectionsBinding

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_collections, container, false)

    binding.categories.onItemSelectedListener = this

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    (activity.application as PHCApplication).applicationComponent.inject(this)

    categoriesViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(CategoriesViewModel::class.java)

    categoriesViewModel.getCategories().observe(this, this::showCategories, {}, {})
    categoriesViewModel.getCollections().observe(this, this::showCollections, {}, {})
  }

  fun showCollections(data: List<ProductCollection>) {
    binding.text.text = data.fold("", { acc, collection -> acc + collection + "\n"})
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

  override fun onNothingSelected(parent: AdapterView<*>) {}

  override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
    val itemData = binding.categories.adapter.getItem(pos)
    val categoryId = extractCategoryId(itemData as Map<String, Any>)
    categoriesViewModel.setCollectionsFilter(categoryId)
  }

  private fun extractCategoryId(itemData: Map<String, Any>): Long {
    return itemData[CategoriesData.KEY_ID] as Long
  }
}