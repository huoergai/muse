package com.huoergai.muse.network.service

import com.huoergai.muse.model.network.AccountDetailResponse
import com.huoergai.muse.network.ApiResponse
import com.huoergai.muse.network.TMDB_V3
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * D&T: 2023-07-07 17:12
 * DES:
 */
interface AccountService {

    @GET("$TMDB_V3/account/{accountID}")
    suspend fun detail(@Path("accountID") accountID: Long): ApiResponse<AccountDetailResponse>

}