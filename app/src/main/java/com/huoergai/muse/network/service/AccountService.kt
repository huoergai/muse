package com.huoergai.muse.network.service

import com.huoergai.muse.model.network.AccountDetailResponse
import com.huoergai.muse.network.dola.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * D&T: 2023-07-07 17:12
 * DES:
 */
interface AccountService {

    @GET("account/{accountID}")
    suspend fun detail(@Path("accountID") accountID: Long): ApiResponse<AccountDetailResponse>

}