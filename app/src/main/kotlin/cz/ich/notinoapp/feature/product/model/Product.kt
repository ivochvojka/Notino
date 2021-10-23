package cz.ich.notinoapp.feature.product.model

/**
 * Product model contains only necessary properties.
 */
data class Product(
    val id: String,
    val brand: String,
    val name: String,
    val score: Int,
    val annotation: String,
    val imageUrl: String,
    val price: Int,
    var isHeartEnabled: Boolean = false
)
