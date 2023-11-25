package com.myproject.domain.usecase

interface BaseUseCase<T> {
    fun execute(): T
}