package com.myproject.domain.repository

interface CartRepository {

    fun placeAnOrder(): Boolean
}