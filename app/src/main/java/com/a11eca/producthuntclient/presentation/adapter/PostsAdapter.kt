package com.a11eca.producthuntclient.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.a11eca.producthuntclient.databinding.ItemPostBinding
import com.a11eca.producthuntclient.domain.entity.Post

class PostsAdapter constructor(
    private val layoutManager: RecyclerView.LayoutManager,
    private val listener: OnPostSelectedListener
): RecyclerView.Adapter<PostViewHolder>() {

  private var items = listOf<Post>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = ItemPostBinding.inflate(layoutInflater, parent, false)

    binding.root.setOnClickListener {
      view ->
      val position = layoutManager.getPosition(view)
      listener.onPostSelected(items[position].id)
    }

    val viewHolder = PostViewHolder(binding)
    return viewHolder
  }

  override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
    val item = items[position]
    holder.binding.post = item
    holder.binding.executePendingBindings()
  }

  override fun getItemCount(): Int {
    return items.size
  }

  fun updateItems(items: List<Post>) {
    this.items = items
    notifyDataSetChanged()
  }
}

interface OnPostSelectedListener {
  fun onPostSelected(postId: Long)
}

class PostViewHolder constructor(
    val binding: ItemPostBinding
): RecyclerView.ViewHolder(binding.root)