package com.flaviokreis.datasource.commons

import kotlinx.coroutines.flow.*

fun <T> networkBoundResource(
    fetchFromLocal: suspend () -> Flow<T>,
    fetchFromRemote: suspend () -> Flow<T>,
    shouldFetchFromRemote: suspend (T?) -> Boolean = { true },
    saveRemoteData: suspend (T) -> Unit = { Unit }
): Flow<T> = flow {

    val localData = fetchFromLocal().first()

    when(shouldFetchFromRemote(localData)) {
        true -> {
            emit(localData)

            fetchFromRemote().collect {
                saveRemoteData(it)

                emitAll(fetchFromLocal())
            }
        }
        else -> emitAll(fetchFromLocal())
    }
}