package com.a11eca.producthuntclient.presentation.fragment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a11eca.producthuntclient.R
import com.a11eca.producthuntclient.databinding.FragmentPostBinding
import com.a11eca.producthuntclient.domain.entity.DetailedPost
import com.a11eca.producthuntclient.presentation.PHCApplication
import com.a11eca.producthuntclient.presentation.viewmodel.PostViewModel
import javax.inject.Inject


class PostFragment : BaseFragment() {

  companion object {
    private const val ARG_POST_ID = "PostId"

    fun newInstance(postId: Long): PostFragment {
      val fragment = PostFragment()

      val bundle = Bundle()
      bundle.putLong(ARG_POST_ID, postId)
      fragment.arguments = bundle

      return fragment
    }
  }

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory
  internal lateinit var postViewModel: PostViewModel
  internal lateinit var binding: FragmentPostBinding

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false)

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    (activity.application as PHCApplication).applicationComponent.inject(this)

    postViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(PostViewModel::class.java)

    val postId = arguments.getLong(ARG_POST_ID)
    postViewModel.setPostId(postId)

    postViewModel.post.observe(this, this::showPost, {}, {})
  }

  fun showPost(post: DetailedPost) {
    binding.post = post
    binding.getIdButton.setOnClickListener({
      val url: String
      if (!post.description.startsWith("http://") && !post.description.startsWith("https://")) {
        url = "http://" + post.description
      } else {
        url = post.description
      }
      val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
      startActivity(browserIntent)
    })
  }
}