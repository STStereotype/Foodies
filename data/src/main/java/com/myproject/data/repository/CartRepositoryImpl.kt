package com.myproject.data.repository

import com.myproject.data.storage.cart.CartStorage
import com.myproject.data.storage.cart.models.CartItem
import com.myproject.domain.models.cart.Cart
import com.myproject.domain.models.dish.DishCard
import com.myproject.domain.repository.CartRepository

class CartRepositoryImpl(
    private val cartStorage: CartStorage
) : CartRepository {
    override suspend fun getCartDishes(): List<Cart> =
        cartStorage.cart.map { cartItem ->
            convertCartItemToCart(cartItem)
        }

    override suspend fun addCartDish(cart: Cart) {
        cartStorage.addCartDish(
            convertCartToCartItem(cart)
        )
    }

    override suspend fun removeCartDish(id: Int) {
        cartStorage.removeCartDish(id)
    }

    override suspend fun placeAnOrder(dishes: List<Cart>): Boolean {
        if (dishes.isEmpty()) return false

        val cartItems = dishes.map { cart ->
            convertCartToCartItem(cart)
        }
        return cartStorage.placeAnOrder(cartItems)
    }

    private fun convertCartToCartItem(cart: Cart): CartItem {
        val dish = cart.dish
        return CartItem(
            id = dish.id,
            name = dish.name,
            image = dish.image,
            priceCurrent = dish.price_current,
            priceOld = dish.price_old,
            count = cart.count
        )
    }

    private fun convertCartItemToCart(cartItem: CartItem): Cart {
        return Cart(
            DishCard(
                id = cartItem.id,
                name = cartItem.name,
                image = cartItem.image,
                price_current = cartItem.priceCurrent,
                price_old = cartItem.priceOld
            ),
            count = cartItem.count
        )
    }
}
