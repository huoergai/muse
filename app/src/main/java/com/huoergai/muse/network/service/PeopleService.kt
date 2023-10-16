package com.huoergai.muse.network.service

import com.huoergai.muse.model.network.PeopleListResponse
import com.huoergai.muse.model.network.PersonDetailResponse
import com.huoergai.muse.network.dola.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * D&T: 2023-07-20 17:41
 * DES:
 */
interface PeopleService {
    @GET("person/{filter}")
    suspend fun peopleList(
        @Query("page") page: Int = 1,
        @Path("filter") filter: String = "popular"
    ): ApiResponse<PeopleListResponse>

    @GET("person/{person_id}")
    suspend fun peopleDetail(@Path("person_id") personID: Int): ApiResponse<PersonDetailResponse>
}