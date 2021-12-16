package com.masai.sainath.itunesapi.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.masai.sainath.itunesapi.remote.Resource
import com.masai.sainath.itunesapi.remote.db.ItunesDbTable
import com.masai.sainath.itunesapi.remote.responses.ItunesResponseModel
import com.masai.sainath.itunesapi.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    fun getDataFromAPI(query: String): LiveData<Resource<ItunesResponseModel>> {
        return liveData(Dispatchers.IO) {
            val data = appRepository.getDataFromAPI(query)
            emit(data)
        }
    }

    fun insertDataInDb(itunesDbTable: ItunesDbTable) {
        appRepository.insertDataInDb(itunesDbTable)
    }

    fun deleteDataFromDb() {
        appRepository.deleteDataFromDb()
    }

    fun getDataFromDb() {
        appRepository.getDataFromDb()
    }

}