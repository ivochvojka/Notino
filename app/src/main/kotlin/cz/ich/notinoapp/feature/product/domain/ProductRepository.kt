package cz.ich.notinoapp.feature.product.domain

import cz.ich.notinoapp.feature.product.model.Product
import cz.ich.notinoapp.main.domain.Result

/**
 * Repository for Notino products.
 */
interface ProductRepository {

    /**
     * Get all products from remote API and combine with favourite products from local DB.
     */
    suspend fun getAll(): Result<List<Product>>

    /**
     * Update heart flag in local DB.
     */
    suspend fun updateHeartImage(product: Product)

}