package com.masai.sainath.itunesapi.repositories

import androidx.lifecycle.LiveData
import com.application.itunesapplication.di.Module
import com.masai.sainath.itunesapi.remote.Resource
import com.masai.sainath.itunesapi.remote.ResponseHandler
import com.masai.sainath.itunesapi.remote.db.AppDao
import com.masai.sainath.itunesapi.remote.db.ItunesDbTable
import com.masai.sainath.itunesapi.remote.interfaces.APIClient
import com.masai.sainath.itunesapi.remote.responses.ItunesResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppRepository @Inject constructor(private val appDao: AppDao, val apiClient: APIClient) {

    private val responseHandler: ResponseHandler = ResponseHandler()

    suspend fun getDataFromAPI(query: String): Resource<ItunesResponseModel> {
        return try {
            val itunesResponseModel: ItunesResponseModel =
                Module.provideApiService().getResponse(query)
            responseHandler.handleSuccess(itunesResponseModel)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    fun insertDataInDb(itunesDbTable: ItunesDbTable) {
        CoroutineScope(Dispatchers.IO).launch {
            appDao.deleteAllDataFromDB()
            appDao.addDataFromAPI(itunesDbTable)
        }
    }

    fun getDataFromDb(): LiveData<ItunesDbTable> {
        return appDao.getResponseFromDb()
    }

    fun deleteDataFromDb(){
        appDao.deleteAllDataFromDB()
    }
}