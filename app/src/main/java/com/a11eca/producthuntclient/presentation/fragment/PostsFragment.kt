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
import com.a11eca.producthuntclient.presentation.adapter.OnPostSelectedListener
import com.a11eca.producthuntclient.presentation.adapter.PostsAdapter
import com.a11eca.producthuntclient.presentation.entity.CategoriesData
import com.a11eca.producthuntclient.presentation.viewmodel.CategoriesViewModel
import javax.inject.Inject

class PostsFragment : BaseFragment(), AdapterView.OnItemSelectedListener, OnPostSelectedListener {

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory
  internal lateinit var categoriesViewModel: CategoriesViewModel
  internal lateinit var binding: FragmentPostsBinding

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false)

    binding.isListEmpty = true

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    (activity.application as PHCApplication).applicationComponent.inject(this)

    categoriesViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(CategoriesViewModel::class.java)

    categoriesViewModel.categories.observe(this, this::showCategories)
    categoriesViewModel.posts.observe(this, this::showPosts)
    setupPostsList()
  }

  private fun setupPostsList() {
    binding.posts.setHasFixedSize(true)

    val layoutManager = LinearLayoutManager(context)
    binding.posts.layoutManager = layoutManager

    val adapter = PostsAdapter(layoutManager, this, categoriesViewModel)
    binding.posts.adapter = adapter
  }

  fun showPosts(data: List<Post>) {
    (binding.posts.adapter as PostsAdapter).updateItems(data)
    binding.isListEmpty = data.isEmpty()
  }

  fun showCategories(data: CategoriesData) {
    binding.categories.adapter = createCategoriesAdapter(data)
    binding.categories.setSelection(0, false)
    binding.categories.onItemSelectedListener = this
  }

  fun createCategoriesAdapter(data: CategoriesData): SpinnerAdapter {
    val adapter = SimpleAdapter(context, data.data,
        android.R.layout.simple_spinner_item, data.from, intArrayOf(android.R.id.text1))
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    return adapter
  }

  override fun onPostSelected(postId: Long) {
    fragmentManager.beginTransaction()
        .replace(R.id.content_root, PostFragment.newInstance(postId))
        .addToBackStack(null)
        .commit()
  }

  override fun onNothingSelected(parent: AdapterView<*>) {}

  override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
    categoriesViewModel.posts.clear()
    val itemData = binding.categories.adapter.getItem(pos)
    @Suppress("UNCHECKED_CAST")
    val category = extractCategorySlug(itemData as Map<String, String>)
    categoriesViewModel.setPostsFilter(category)
  }

  private fun extractCategorySlug(itemData: Map<String, String>): String {
    return itemData[CategoriesData.KEY_SLUG]!!
  }
}