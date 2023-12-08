package com.myproject.foodies.di

import com.myproject.domain.repository.CartRepository
import com.myproject.domain.repository.CategoryRepository
import com.myproject.domain.repository.FilterRepository
import com.myproject.domain.repository.FoodiesRepository
import com.myproject.domain.usecase.cart.GetCartUseCase
import com.myproject.domain.usecase.cart.PlaceAnOrderUseCase
import com.myproject.domain.usecase.cart.RemoveItemUseCase
import com.myproject.domain.usecase.cart.SetItemUseCase
import com.myproject.domain.usecase.category.GetCategoriesUseCase
import com.myproject.domain.usecase.filter.GetFiltersUseCase
import com.myproject.domain.usecase.foodies.GetDishesByCategoryIdUseCase
import com.myproject.domain.usecase.foodies.GetDishesByFiltersUseCase
import com.myproject.domain.usecase.foodies.GetDishesByIdUseCase
import com.myproject.domain.usecase.foodies.GetDishesByNameUseCase
import com.myproject.domain.usecase.foodies.GetDishesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun providePlaceAnOrderUseCase(cartRepository: CartRepository)  =
        PlaceAnOrderUseCase(cartRepository)

    @Provides
    @Singleton
    fun provideSetItemUseCase(cartRepository: CartRepository) =
        SetItemUseCase(cartRepository)

    @Provides
    @Singleton
    fun provideRemoveItemUseCase(cartRepository: CartRepository) =
        RemoveItemUseCase(cartRepository)

    @Provides
    @Singleton
    fun provideGetCartUseCase(cartRepository: CartRepository) =
        GetCartUseCase(cartRepository)

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(categoryRepository: CategoryRepository) =
        GetCategoriesUseCase(categoryRepository)


    @Provides
    @Singleton
    fun provideGetFilterUseCase(filterRepository: FilterRepository) =
        GetFiltersUseCase(filterRepository)

    @Provides
    @Singleton
    fun provideGetDishesByCategoryIdUseCase(foodiesRepository: FoodiesRepository) =
        GetDishesByCategoryIdUseCase(foodiesRepository)

    @Provides
    @Singleton
    fun provideGetDishesByIdUseCase(foodiesRepository: FoodiesRepository) =
        GetDishesByIdUseCase(foodiesRepository)

    @Provides
    @Singleton
    fun provideGetDishesByNameUseCase(foodiesRepository: FoodiesRepository) =
        GetDishesByNameUseCase(foodiesRepository)

    @Provides
    @Singleton
    fun provideGetDishesByFilters(foodiesRepository: FoodiesRepository) =
        GetDishesByFiltersUseCase(foodiesRepository)

    @Provides
    @Singleton
    fun provideGetDishesUseCase(foodiesRepository: FoodiesRepository) =
        GetDishesUseCase(foodiesRepository)
}
