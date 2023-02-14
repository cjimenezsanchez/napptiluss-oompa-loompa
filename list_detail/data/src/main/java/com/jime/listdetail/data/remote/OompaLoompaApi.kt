package com.jime.listdetail.data.remote

import com.jime.listdetail.data.remote.model.OompaLoompaDetailDto
import com.jime.listdetail.data.remote.model.OompaLoompaPagingDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OompaLoompaApi {

    companion object {
        const val BASE_URL = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/"
    }

    @GET("/napptilus/oompa-loompas")
    suspend fun getOompaLoompaByPage(@Query("page") page: Int = 1): OompaLoompaPagingDto

    @GET("/napptilus/oompa-loompas/{id}")
    suspend fun getOompaLoompaById(@Path("id") id: Int): OompaLoompaDetailDto

}