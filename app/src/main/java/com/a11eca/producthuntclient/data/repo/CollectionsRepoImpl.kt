package com.a11eca.producthuntclient.data.repo

import com.a11eca.producthuntclient.domain.entity.ProductCollection
import com.a11eca.producthuntclient.domain.repo.CollectionsRepo
import io.reactivex.Flowable
import javax.inject.Inject

class CollectionsRepoImpl @Inject constructor(

) : CollectionsRepo {
  override fun getCollections(categoryId: Long): Flowable<List<ProductCollection>> {
    return Flowable.empty()
  }
}