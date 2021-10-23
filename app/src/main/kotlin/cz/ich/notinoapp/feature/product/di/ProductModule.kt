package cz.ich.notinoapp.feature.product.di

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import cz.ich.notinoapp.R
import cz.ich.notinoapp.feature.product.data.local.ProductDao
import cz.ich.notinoapp.feature.product.data.remote.ProductApi
import cz.ich.notinoapp.main.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ProductApi = retrofit.create(ProductApi::class.java)

    @Provides
    fun provideDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Qualifier
    annotation class EnabledHeartDrawable

    @Qualifier
    annotation class DisabledHeartDrawable

    @Provides
    @Singleton
    @EnabledHeartDrawable
    fun getEnabledHeartDrawable(@ApplicationContext appContext: Context) =
        AppCompatResources.getDrawable(appContext, R.drawable.ic_heart_fill)!!

    @Provides
    @Singleton
    @DisabledHeartDrawable
    fun getDisabledHeartDrawable(@ApplicationContext appContext: Context) =
        AppCompatResources.getDrawable(appContext, R.drawable.ic_heart_empty)!!

}