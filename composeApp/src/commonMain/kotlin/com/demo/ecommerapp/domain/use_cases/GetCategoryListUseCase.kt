package com.demo.ecommerapp.domain.use_cases

import com.demo.ecommerapp.data.utils.NetworkResult
import com.demo.ecommerapp.domain.repo.CategoryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCategoryListUseCase : KoinComponent {

    private val categoryRepo: CategoryRepo by inject()

    operator fun invoke() = flow {
        emit(NetworkResult.Loading())
        emit(NetworkResult.Success(data = categoryRepo.getCategories()))
    }.catch {
        emit(NetworkResult.Error(message = it.message.toString()))
    }.flowOn(context = Dispatchers.Default)
}