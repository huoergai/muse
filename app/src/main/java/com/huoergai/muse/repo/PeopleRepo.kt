package com.huoergai.muse.repo

import androidx.annotation.WorkerThread
import com.huoergai.muse.network.dola.ApiResponse
import com.huoergai.muse.network.dola.suspendOnSuccess
import com.huoergai.muse.network.model.network.PeopleResponse
import com.huoergai.muse.network.model.network.PersonDetail
import com.huoergai.muse.network.service.PeopleService
import com.huoergai.muse.persistence.PersonDao
import retrofit2.Response

/**
 * D&T: 2023-10-27 16:37
 * DES:
 */
class PeopleRepo(
    private val peopleService: PeopleService,
    private val personDao: PersonDao
) : Repository {

    @WorkerThread
    suspend fun loadPeople(page: Int): ApiResponse<PeopleResponse> {
        val people = personDao.getPeople(page)
        if (people.isEmpty()) {
            val peopleResponse = peopleService.getPeople(page)
            peopleResponse.suspendOnSuccess {
                val persons = this.data.results
                persons.forEach { it.page = page }
                personDao.insertAll(persons)
            }
            return peopleResponse
        }

        return ApiResponse.from { Response.success(PeopleResponse(page, people, 0)) }
    }

    @WorkerThread
    suspend fun loadPersonDetail(personID: Int): ApiResponse<PersonDetail> =
        peopleService.getPersonDetail(personID)

}