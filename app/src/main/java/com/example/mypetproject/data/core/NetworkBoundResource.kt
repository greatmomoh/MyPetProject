package com.example.mypetproject.data.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

fun <ResultType, RequestType> networkBoundResource(
    query: () -> Flow<ResultType>,
    fetch: suspend () -> RequestType,
    saveFetchResult: suspend (RequestType) -> Unit,
    shouldFetch: (ResultType) -> Boolean = { true },
    onFetchFailed: (Throwable) -> Unit = { Unit },
): Flow<ResultType> = flow {
    val cachedData = query().first()
    val flow = if (shouldFetch(cachedData)) {
        try {
            if((cachedData as? List<*>)?.isNotEmpty() == true){
                emit(cachedData)
            }
            val fetchResponse = fetch()
            saveFetchResult(fetchResponse)
            query()
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query()
        }
    } else {
        query()
    }
    emitAll(flow)
}