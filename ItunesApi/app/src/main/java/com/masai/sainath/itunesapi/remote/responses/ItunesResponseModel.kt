package com.masai.sainath.itunesapi.remote.responses


import com.google.gson.annotations.SerializedName

data class ItunesResponseModel(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val resultModels: List<ResultModel>
)