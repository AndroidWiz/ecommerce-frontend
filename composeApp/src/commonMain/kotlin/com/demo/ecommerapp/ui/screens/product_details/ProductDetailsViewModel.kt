package com.demo.ecommerapp.ui.screens.product_details

import com.demo.ecommerapp.data.utils.NetworkResult
import com.demo.ecommerapp.domain.model.ProductDetails
import com.demo.ecommerapp.domain.use_cases.GetProductDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class ProductDetailsStateHolder(
    val isLoading: Boolean = false,
    val data: ProductDetails? = null,
    val error: String = "",
)

class ProductDetailsViewModel(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailsStateHolder())
    val uiState: StateFlow<ProductDetailsStateHolder> = _uiState.asStateFlow()

    fun getProductDetails(id: Long) = getProductDetailsUseCase(id = id).onEach { result ->
        when (result) {
            is NetworkResult.Loading -> _uiState.update { ProductDetailsStateHolder(isLoading = true) }
            is NetworkResult.Success -> _uiState.update { ProductDetailsStateHolder(data = result.data) }
            is NetworkResult.Error -> _uiState.update { ProductDetailsStateHolder(error = result.message) }
        }
    }.launchIn(viewModelScope)
}