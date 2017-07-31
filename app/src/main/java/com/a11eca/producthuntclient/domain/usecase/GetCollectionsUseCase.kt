package com.a11eca.producthuntclient.domain.usecase

import com.a11eca.producthuntclient.domain.entity.ProductCollection
import com.a11eca.producthuntclient.domain.repo.CollectionsRepo
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class GetCollectionsUseCase @Inject constructor(
    private val repo: CollectionsRepo
){

  fun getFilter(): Flowable<Long> {
    return Flowable.just(0)
  }

  fun setFilter(categoryId: Long): Completable {
    return Completable.complete()
  }

  fun getFiltered(categoryId: Long): Flowable<List<ProductCollection>> {
    return repo.getCollections(categoryId)
  }
}