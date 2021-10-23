package cz.ich.notinoapp.feature.product.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.ich.notinoapp.feature.product.model.Product

@Entity(
    tableName = "product",
)
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val isHeartEnabled: Boolean
)

fun Product.toEntity() = ProductEntity(
    id = id,
    isHeartEnabled = isHeartEnabled
)