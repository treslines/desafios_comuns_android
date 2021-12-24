package com.progdeelite.dca.endpoints

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import com.progdeelite.dca.endpoints.ApiEndpoints.*

/** HTTP Client to fetch data from or communicate with the backend. */
object Retrofit {

    private const val BASE_URL_LOGIN_SERVICE = "http://your-endpoint/any-path/your-login-service/"
    private const val BASE_URL_ALARM_SERVICE = "http://your-endpoint/any-path/your-service/"

    private fun getRetrofit(baseURL: String): Retrofit {
        val gsonBuilder = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .build()
    }

    val loginApi: LoginService = getRetrofit(BASE_URL_LOGIN_SERVICE).create(LoginService::class.java)
    val alarmsApi: AlarmService = getRetrofit(BASE_URL_ALARM_SERVICE).create(AlarmService::class.java)
}