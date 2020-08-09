//package com.example.deepfaceapplication
//
//import models.Prediction
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//
//class RestApiService {
//    fun analyzePhoto(userData: UserInfo, onResult: (UserInfo?) -> Unit){
//        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
//        retrofit.addUser(userData).enqueue(
//            object : Callback<Prediction> {
//                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
//                    onResult(null)
//                }
//                override fun onResponse( call: Call<UserInfo>, response: Response<Prediction>) {
//                    val addedUser = response.body()
//                    onResult(addedUser)
//                }
//            }
//        )
//    }
//}