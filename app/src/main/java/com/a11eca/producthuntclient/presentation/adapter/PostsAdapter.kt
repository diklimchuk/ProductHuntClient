package com.a11eca.producthuntclient.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.a11eca.producthuntclient.R
import com.a11eca.producthuntclient.domain.entity.Post

class PostsAdapter : RecyclerView.Adapter<PostViewHolder>() {

  private var items = listOf<Post>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
    val viewHolder = PostViewHolder(view)
    return viewHolder
  }

  override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
    val item = items[position]
    holder.name.text = item.name
    holder.title.text = item.description
    holder.upvoteNumber.text = item.votesCount.toString()
  }

  override fun getItemCount(): Int {
    return items.size
  }

  fun updateItems(items: List<Post>) {
    this.items = items
    notifyDataSetChanged()
  }
}

class PostViewHolder constructor(
    itemView: View
): RecyclerView.ViewHolder(itemView) {
  val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
  val name: TextView = itemView.findViewById(R.id.name)
  val title: TextView = itemView.findViewById(R.id.title)
  val upvoteNumber: TextView = itemView.findViewById(R.id.upvote_number)
}