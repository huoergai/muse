package com.huoergai.muse.repo

import androidx.annotation.WorkerThread
import com.huoergai.muse.network.dola.ApiResponse
import com.huoergai.muse.network.model.network.PeopleResponse
import com.huoergai.muse.network.model.network.PersonDetail
import com.huoergai.muse.network.service.PeopleService

/**
 * D&T: 2023-10-27 16:37
 * DES:
 */
class PeopleRepo(private val peopleService: PeopleService) : Repository {

    @WorkerThread
    suspend fun loadPopularPeople(): ApiResponse<PeopleResponse> = peopleService.getPopularPeople()

    suspend fun loadPersonDetail(personID: Int): ApiResponse<PersonDetail> =
        peopleService.getPersonDetail(personID)

}