package cz.ich.notinoapp.feature.product.data

import cz.ich.notinoapp.feature.product.data.local.ProductDao
import cz.ich.notinoapp.feature.product.data.local.toEntity
import cz.ich.notinoapp.feature.product.data.remote.ProductApi
import cz.ich.notinoapp.feature.product.data.remote.toModel
import cz.ich.notinoapp.feature.product.domain.ProductRepository
import cz.ich.notinoapp.feature.product.model.Product
import cz.ich.notinoapp.main.domain.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultProductRepository @Inject constructor(
    private val api: ProductApi,
    private val dao: ProductDao
) : ProductRepository {

    override suspend fun getAll(): Result<List<Product>> {
        return try {
            val entitiesMap = dao.getAll().associateBy { it.id }
            val products = api.getProducts().vpProductByIds.map { it.toModel() }
            val productsWithHearts = products.map {
                if (entitiesMap[it.id]?.isHeartEnabled == true) {
                    it.copy(isHeartEnabled = true)
                } else {
                    it
                }
            }
            Result.Success(productsWithHearts)
        } catch (e: Exception) {
            Result.Error("Could not refresh products, ${e.message}")
        }
    }

    override suspend fun updateHeartImage(product: Product) {
        if (product.isHeartEnabled) {
            dao.insertOrUpdate(product.toEntity())
        } else {
            dao.delete(product.toEntity())
        }
    }

}