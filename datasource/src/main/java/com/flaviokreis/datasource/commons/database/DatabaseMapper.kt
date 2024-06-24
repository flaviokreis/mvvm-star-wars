package com.flaviokreis.datasource.commons.database

internal interface DatabaseMapper<MODEL, ENTITY> {
    fun toModel(data: ENTITY): MODEL

    fun toModelList(data: List<ENTITY>): List<MODEL> {
        return data.map { toModel(it) }
    }

    fun toEntity(model: MODEL): ENTITY
}