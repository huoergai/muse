package com.huoergai.muse.network.service

import com.huoergai.muse.network.dola.ApiResponse
import com.huoergai.muse.network.model.network.PeopleResponse
import com.huoergai.muse.network.model.network.PersonDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * D&T: 2023-07-20 17:41
 * DES:
 */
interface PeopleService {
    @GET("person/popular")
    suspend fun getPopularPeople(
        @Query("page") page: Int = 1,
        @Query("language") lang: String = "en-US"
    ): ApiResponse<PeopleResponse>

    @GET("person/{person_id}")
    suspend fun getPersonDetail(@Path("person_id") personID: Int): ApiResponse<PersonDetail>
}