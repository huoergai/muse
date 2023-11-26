package com.huoergai.muse.network.dola

/**
 * D&T: 2023-10-16 11:13
 * DES:
 */

fun <T> ApiResponse<T>.getOrNull(): T? {
    return when (this) {
        is ApiResponse.Success -> data
        is ApiResponse.Failure -> null
    }
}

fun <T> ApiResponse<T>.getOrElse(defVal: T): T {
    return when (this) {
        is ApiResponse.Success -> data
        is ApiResponse.Failure -> defVal
    }
}

fun <T> ApiResponse<T>.onSuccess(onResult: ApiResponse.Success<T>.() -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success) {
        onResult(this)
    }
    return this
}


suspend fun <T> ApiResponse<T>.suspendOnSuccess(onResult: suspend ApiResponse.Success<T>.() -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success) {
        onResult(this)
    }
    return this
}