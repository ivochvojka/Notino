package cz.ich.notinoapp.feature.product.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ich.notinoapp.feature.product.domain.ProductRepository
import cz.ich.notinoapp.feature.product.model.Product
import cz.ich.notinoapp.main.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<Result<List<Product>>>(Result.Running())
    val products = _products.asStateFlow()

    init {
        loadProducts()
    }

    /**
     * Callback for click on heart image.
     */
    fun onHeartClicked(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateHeartImage(product)
        }
    }

    /**
     * Load all products.
     */
    private fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val productsResult = repository.getAll()
            _products.value = productsResult
        }
    }

}