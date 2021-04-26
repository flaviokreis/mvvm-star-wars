package com.flaviokreis.datasource.commons.remote

internal interface RemoteMapper<MODEL, DTO> {
    fun toModel(data: DTO): MODEL

    fun toModelList(data: List<DTO>): List<MODEL> {
        return data.map { toModel(it) }
    }

    fun toDto(model: MODEL): DTO
}