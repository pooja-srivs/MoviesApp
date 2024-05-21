package com.example.moviesapp.common.mapper

import javax.inject.Inject

class DataMapperImpl @Inject constructor() : DataMapper {
    override fun <T, R> map(entity: T, mapper: (T) -> R): R = mapper(entity)

    override fun <T, R> listMap(entity: List<T>, mapper: (T) -> R): List<R> = entity.map { mapper(it) }
}