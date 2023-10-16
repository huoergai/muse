package com.huoergai.muse.network.dola

/**
 * D&T: 2023-10-14 11:01
 * DES:
 */
class EmptyBodyException(
    val code: Int,
    override val message: String = "The server response with code ($code) and there is no content in the response body."
) : Throwable(message)