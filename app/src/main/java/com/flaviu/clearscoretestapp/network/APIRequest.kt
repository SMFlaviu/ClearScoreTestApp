package com.flaviu.clearscoretestapp.network

import retrofit2.Response
import retrofit2.http.GET

interface APIRequest {
    @GET("endpoint.json")
    suspend fun getDataResponse(): Response<DataResponse>
}