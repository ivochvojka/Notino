package cz.ich.notinoapp.feature.product.di

import cz.ich.notinoapp.feature.product.data.DefaultProductRepository
import cz.ich.notinoapp.feature.product.domain.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCategoryRepository(impl: DefaultProductRepository): ProductRepository

}