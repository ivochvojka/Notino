package cz.ich.notinoapp.feature.product.data.remote

import retrofit2.http.GET

interface ProductApi {

    @GET("michals92/notino-mobile-test/db")
    suspend fun getProducts(): ProductResponse

}