package com.myproject.foodies.di

import com.myproject.data.repository.CartRepositoryImpl
import com.myproject.data.repository.CategoryRepositoryImpl
import com.myproject.data.repository.FilterRepositoryImpl
import com.myproject.data.repository.FoodiesRepositoryImpl
import com.myproject.data.storage.cart.CartStorage
import com.myproject.data.storage.cart.CartStorageImpl
import com.myproject.data.storage.category.CategoryStorage
import com.myproject.data.storage.category.CategoryStorageImpl
import com.myproject.data.storage.filter.FilterStorage
import com.myproject.data.storage.filter.FilterStorageImpl
import com.myproject.data.storage.foodies.FoodiesStorage
import com.myproject.data.storage.foodies.FoodiesStorageImpl
import com.myproject.domain.repository.CartRepository
import com.myproject.domain.repository.CategoryRepository
import com.myproject.domain.repository.FilterRepository
import com.myproject.domain.repository.FoodiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideCartStorage() : CartStorage =
        CartStorageImpl()

    @Provides
    @Singleton
    fun provideCartRepository(cartStorage: CartStorage) : CartRepository =
        CartRepositoryImpl(cartStorage)

    @Provides
    @Singleton
    fun provideCategoryStorage() : CategoryStorage = CategoryStorageImpl()

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryStorage: CategoryStorage) : CategoryRepository =
        CategoryRepositoryImpl(categoryStorage)

    @Provides
    @Singleton
    fun provideFilterStorage() : FilterStorage = FilterStorageImpl()

    @Provides
    @Singleton
    fun provideFilterRepository(filterStorage: FilterStorage) : FilterRepository =
        FilterRepositoryImpl(filterStorage)

    @Provides
    @Singleton
    fun provideFoodiesStorage() : FoodiesStorage = FoodiesStorageImpl()

    @Provides
    @Singleton
    fun provideFoodiesRepository(foodiesStorage: FoodiesStorage) : FoodiesRepository =
        FoodiesRepositoryImpl(foodiesStorage)
}
