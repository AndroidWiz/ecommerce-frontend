package com.demo.ecommerapp.ui.screens.category_details

import com.demo.ecommerapp.data.utils.NetworkResult
import com.demo.ecommerapp.domain.model.Products
import com.demo.ecommerapp.domain.use_cases.GetProductsListByCategoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class CategoryDetailsStateHolder(
    val isLoading: Boolean = false,
    val data: List<Products>? = null,
    val error: String = "",
)

class CategoryDetailsViewModel(
    private val getProductsListByCategoryUseCase: GetProductsListByCategoryUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryDetailsStateHolder())
    val uiState: StateFlow<CategoryDetailsStateHolder> = _uiState.asStateFlow()

    fun getProductsListByCategory(categoryId: Long) =
        getProductsListByCategoryUseCase(categoryId = categoryId).onEach { result ->
            when (result) {
                is NetworkResult.Loading -> _uiState.update { CategoryDetailsStateHolder(isLoading = true) }
                is NetworkResult.Success -> _uiState.update { CategoryDetailsStateHolder(data = result.data) }
                is NetworkResult.Error -> _uiState.update { CategoryDetailsStateHolder(error = result.message) }
            }
        }.launchIn(viewModelScope)
}