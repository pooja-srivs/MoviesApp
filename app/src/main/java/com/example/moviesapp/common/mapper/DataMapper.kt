package com.example.moviesapp.common.mapper

interface DataMapper {

    fun <T, R> map(entity: T, mapper: (T) -> R) : R
    fun <T, R> listMap(entity: List<T>, mapper: (T) -> R) : List<R>
}