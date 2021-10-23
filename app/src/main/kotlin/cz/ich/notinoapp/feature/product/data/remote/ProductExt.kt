package cz.ich.notinoapp.feature.product.data.remote

import cz.ich.notinoapp.feature.product.model.Product

fun VpProductById.toModel(): Product = Product(
    id = id,
    brand = brand.name,
    name = name,
    annotation = annotation,
    imageUrl = imageUrl,
    score = reviewSummary.score,
    price = price.value
)