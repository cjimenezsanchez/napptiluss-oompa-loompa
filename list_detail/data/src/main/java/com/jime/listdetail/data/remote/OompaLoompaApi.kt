package com.jime.listdetail.data.remote

import com.jime.listdetail.data.remote.model.OompaLoompaDetailDto
import com.jime.listdetail.data.remote.model.OompaLoompaResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OompaLoompaApi {

    companion object {
        const val BASE_URL = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/"
    }

    @GET("/napptilus/oompa-loompas")
    fun getOompaLoompaByPage(@Query("page") page: Int = 1): OompaLoompaResponseDto

    @GET("/napptilus/oompa-loompas/{id}")
    fun getOompaLoompaById(@Path("id") id: String): OompaLoompaDetailDto

}