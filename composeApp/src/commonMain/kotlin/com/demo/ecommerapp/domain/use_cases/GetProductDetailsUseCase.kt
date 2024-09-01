package com.demo.ecommerapp.domain.use_cases

import com.demo.ecommerapp.data.utils.NetworkResult
import com.demo.ecommerapp.domain.model.ProductDetails
import com.demo.ecommerapp.domain.repo.ProductRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetProductDetailsUseCase : KoinComponent {

    private val productRepo: ProductRepo by inject()
    operator fun invoke(id: Long) = flow<NetworkResult<ProductDetails>> {
        emit(NetworkResult.Loading())
        emit(NetworkResult.Success(data = productRepo.getProductDetails(id = id)))
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.Default)
}