package com.application.itunesapplication.di

import android.content.Context
import androidx.room.Room
import com.application.itunesapplication.di.Constants.BASE_URL
import com.masai.sainath.itunesapi.remote.interfaces.APIClient
import com.masai.sainath.itunesapi.remote.db.AppDao
import com.masai.sainath.itunesapi.remote.db.ItunesRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun provideApiService(): APIClient {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(APIClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): ItunesRoomDatabase {
        val builder = Room.databaseBuilder(
            context, ItunesRoomDatabase::class.java, "_itunesDb"
        )
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideDataToDao(db: ItunesRoomDatabase): AppDao {
        return db.getResponseFromDao()
    }

}

object Constants {

    const val BASE_URL = "https://itunes.apple.com/"


}