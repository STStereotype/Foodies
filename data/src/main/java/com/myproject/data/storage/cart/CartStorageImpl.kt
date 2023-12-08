package com.myproject.data.storage.cart

import com.myproject.data.storage.cart.models.CartItem

class CartStorageImpl : CartStorage {

    private val _cart: ArrayList<CartItem> = arrayListOf()

    override val cart: List<CartItem> = _cart
    override suspend fun addCartDish(dish: CartItem) {
        _cart.forEach {
            if (it.id == dish.id) {
                it.count++
                return
            }
        }
        _cart.add(dish)
    }

    override suspend fun removeCartDish(id: Int) {
        _cart.forEach {
            if (it.id == id) {
                if (it.count > 1) it.count--
                else _cart.remove(it)
                return
            }
        }
    }

    override suspend fun placeAnOrder(dishes: List<CartItem>): Boolean {
        return true
    }
}
