package com.huoergai.muse.ui.people

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huoergai.muse.network.dola.onSuccess
import com.huoergai.muse.network.model.network.PersonDetail
import com.huoergai.muse.repo.PeopleRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * D&T: 2023-10-27 18:33
 * DES:
 */
@HiltViewModel
class PeopleDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val peopleRepo: PeopleRepo
) : ViewModel() {

    private val _personDetail = MutableStateFlow<PersonDetail?>(null)
    val personDetail: StateFlow<PersonDetail?> = _personDetail

    fun loadPersonDetail(personID: Int) {
        viewModelScope.launch {
            peopleRepo.loadPersonDetail(personID).onSuccess {
                _personDetail.value = this.data
            }
        }
    }

}