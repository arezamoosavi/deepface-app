package interfaces

import models.Analyze
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface RestAPI {
    @Multipart
    @POST("analyze/")
    fun analyze(@Part file: MultipartBody.Part?): Call<Analyze>
}