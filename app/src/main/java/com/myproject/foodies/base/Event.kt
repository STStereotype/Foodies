package com.myproject.foodies.base

interface Event<T> {
    fun send(event: T)
}
