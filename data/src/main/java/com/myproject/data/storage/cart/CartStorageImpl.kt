package com.myproject.data.storage.cart

import android.util.Log
import com.myproject.data.App
import com.myproject.data.storage.cart.models.CartItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartStorageImpl : CartStorage {

    private val _cart: ArrayList<CartItem> = arrayListOf()

    override val cart: List<CartItem> = _cart
    override suspend fun addCartDish(dish: CartItem) {
        _cart.forEach {
            if (it.id == dish.id) {
                it.count++
                Log.d(App().TAG, _cart.toString())
                return
            }
        }
        _cart.add(dish)
        Log.d(App().TAG, _cart.toString())
    }

    override suspend fun removeCartDish(id: Int) {
        _cart.forEach {
            if (it.id == id) {
                if (it.count > 1) it.count--
                else _cart.remove(it)
                Log.d(App().TAG, _cart.toString())
                return
            }
        }
    }

    override suspend fun placeAnOrder(dishes: List<CartItem>): Boolean {
        delay(500)
        return true
    }
}
