package com.demo.ecommerapp.domain.use_cases

import com.demo.ecommerapp.data.utils.NetworkResult
import com.demo.ecommerapp.domain.repo.ProductRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetProductsListByCategoryUseCase : KoinComponent {

    private val productRepo: ProductRepo by inject()

    operator fun invoke(categoryId: Long) = flow {
        emit(NetworkResult.Loading())
        emit(NetworkResult.Success(data = productRepo.getProductsByCategoryId(categoryId = categoryId)))
    }.catch {
        emit(NetworkResult.Error(message = it.message.toString()))
    }.flowOn(context = Dispatchers.Default)
}