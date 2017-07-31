package com.a11eca.producthuntclient.data.datasource.posts

import com.a11eca.producthuntclient.data.api.json.JsonPost
import io.reactivex.Flowable

interface PostsDataSource {
  fun getFilteredPosts(category: String): Flowable<List<JsonPost>>
}