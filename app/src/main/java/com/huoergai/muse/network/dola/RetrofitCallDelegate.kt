package com.huoergai.muse.network.dola

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * D&T: 2023-10-15 17:02
 * DES:
 */
abstract class RetrofitCallDelegate<TIn, TOut>(protected val proxy: Call<TIn>) : Call<TOut> {
    abstract fun executeImpl(): Response<TOut>

    abstract fun enqueueImpl(callback: Callback<TOut>)

    abstract fun cloneImpl(): Call<TOut>

    /* ---------------------------- divider ---------------------------- */

    override fun clone(): Call<TOut> = cloneImpl()

    override fun execute(): Response<TOut> = executeImpl()

    override fun enqueue(callback: Callback<TOut>) = enqueueImpl(callback)

    override fun isExecuted(): Boolean = proxy.isExecuted

    override fun cancel() = proxy.cancel()

    override fun isCanceled(): Boolean = proxy.isCanceled

    override fun request(): Request = proxy.request()

    override fun timeout(): Timeout = proxy.timeout()
}