package com.demo.ecommerapp.ui.screens.category_list

import com.demo.ecommerapp.data.utils.NetworkResult
import com.demo.ecommerapp.domain.model.ProductCategory
import com.demo.ecommerapp.domain.use_cases.GetCategoryListUseCase
import kotlinx.coroutines.flow.*
import moe.tlaster.precompose.viewmodel.*

data class CategoriesListStateHolder(
    val isLoading: Boolean = false,
    val data: List<ProductCategory>? = null,
    val error: String = "",
)

class CategoriesListViewModel(
    private val getCategoryListUseCase: GetCategoryListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoriesListStateHolder())
    val uiState: StateFlow<CategoriesListStateHolder> = _uiState.asStateFlow()

    init {
        getCategoriesList()
    }

    fun getCategoriesList() = getCategoryListUseCase().onEach { result ->
        when (result) {
            is NetworkResult.Loading -> _uiState.update { CategoriesListStateHolder(isLoading = true) }
            is NetworkResult.Success -> _uiState.update { CategoriesListStateHolder(data = result.data) }
            is NetworkResult.Error -> _uiState.update { CategoriesListStateHolder(error = result.message) }
        }
    }.launchIn(viewModelScope)
}