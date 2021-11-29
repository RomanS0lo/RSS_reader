package com.dts.retrofit.data.entity.mapper

interface Mapper<S, O> {

    fun map(source: S): O

    fun list(source: List<S>): List<O> {
        return source.map { map(it) }
    }
}
