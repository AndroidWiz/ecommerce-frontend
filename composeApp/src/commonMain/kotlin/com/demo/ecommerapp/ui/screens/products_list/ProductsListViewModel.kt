package com.demo.ecommerapp.ui.screens.products_list

import com.demo.ecommerapp.data.utils.NetworkResult
import com.demo.ecommerapp.domain.model.Products
import com.demo.ecommerapp.domain.use_cases.GetProductsListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class ProductsListStateHolder(
    val isLoading: Boolean = false,
    val data: List<Products>? = null,
    val error: String = "",
)

class ProductsListViewModel(
    private val getProductsListUseCase: GetProductsListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsListStateHolder())
    val uiState: StateFlow<ProductsListStateHolder> = _uiState.asStateFlow()

    init {
        getProductsList()
    }

    fun getProductsList() = getProductsListUseCase().onEach { result ->
        when (result) {
            is NetworkResult.Loading -> _uiState.update { ProductsListStateHolder(isLoading = true) }
            is NetworkResult.Success -> _uiState.update { ProductsListStateHolder(data = result.data) }
            is NetworkResult.Error -> _uiState.update { ProductsListStateHolder(error = result.message) }
        }
    }.launchIn(viewModelScope)
}