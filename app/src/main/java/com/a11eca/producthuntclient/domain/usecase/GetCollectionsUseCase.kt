package com.a11eca.producthuntclient.domain.usecase

import com.a11eca.producthuntclient.domain.entity.ProductCollection
import com.a11eca.producthuntclient.domain.repo.CollectionsRepo
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetCollectionsUseCase @Inject constructor(
    private val repo: CollectionsRepo
){
  fun getCollections(categoryId: Long): Flowable<List<ProductCollection>> {
    return repo.getCollections(categoryId)
  }
}