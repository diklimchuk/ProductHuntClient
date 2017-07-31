package com.a11eca.producthuntclient.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.a11eca.producthuntclient.R
import com.a11eca.producthuntclient.domain.entity.ProductCollection

class CollectionsAdapter : RecyclerView.Adapter<CollectionViewHolder>() {

  private var items = listOf<ProductCollection>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_collection, parent, false)
    val viewHolder = CollectionViewHolder(view)
    return viewHolder
  }

  override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
    val item = items[position]
    holder.name.text = item.name
    holder.title.text = if (item.title != ProductCollection.NO_TITLE) item.title else ""
    holder.upvoteNumber.text = item.upvoteNumber.toString()
  }

  override fun getItemCount(): Int {
    return items.size
  }

  fun updateItems(items: List<ProductCollection>) {
    this.items = items
    notifyDataSetChanged()
  }
}

class CollectionViewHolder constructor(
    itemView: View
): RecyclerView.ViewHolder(itemView) {
  val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
  val name: TextView = itemView.findViewById(R.id.name)
  val title: TextView = itemView.findViewById(R.id.title)
  val upvoteNumber: TextView = itemView.findViewById(R.id.upvote_number)
}