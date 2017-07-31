package com.a11eca.producthuntclient.presentation.fragment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SimpleAdapter
import android.widget.SpinnerAdapter
import com.a11eca.producthuntclient.R
import com.a11eca.producthuntclient.databinding.FragmentPostsBinding
import com.a11eca.producthuntclient.domain.entity.Post
import com.a11eca.producthuntclient.presentation.PHCApplication
import com.a11eca.producthuntclient.presentation.adapter.PostsAdapter
import com.a11eca.producthuntclient.presentation.entity.CategoriesData
import com.a11eca.producthuntclient.presentation.viewmodel.CategoriesViewModel
import javax.inject.Inject

class PostsFragment : BaseFragment(), AdapterView.OnItemSelectedListener {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory
  internal lateinit var categoriesViewModel: CategoriesViewModel
  internal lateinit var binding: FragmentPostsBinding

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false)

    setupPostsList()

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    (activity.application as PHCApplication).applicationComponent.inject(this)

    categoriesViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(CategoriesViewModel::class.java)

    categoriesViewModel.getCategories().observe(this, this::showCategories, {}, {})
    categoriesViewModel.getPosts().observe(this, this::showPosts, {}, {})
  }

  override fun onStart() {
    super.onStart()

    binding.categories.onItemSelectedListener = this
  }

  private fun setupPostsList() {
    binding.posts.setHasFixedSize(true)

    val layoutManager = LinearLayoutManager(context)
    binding.posts.layoutManager = layoutManager

    val adapter = PostsAdapter()
    binding.posts.adapter = adapter
  }

  fun showPosts(data: List<Post>) {
    (binding.posts.adapter as PostsAdapter).updateItems(data)
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
    val category = extractCategorySlug(itemData as Map<String, String>)
    categoriesViewModel.setPostsFilter(category)
  }

  private fun extractCategorySlug(itemData: Map<String, String>): String {
    return itemData[CategoriesData.KEY_SLUG]!!
  }
}