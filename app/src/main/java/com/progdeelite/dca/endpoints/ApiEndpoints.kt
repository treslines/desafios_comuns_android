package com.progdeelite.dca.endpoints

import com.progdeelite.dca.filter.Alarm
import retrofit2.Response
import retrofit2.http.*

// 0) Quais dependencias preciso para usar Retrofit (manifest + build.gradle)
// 1) Como definir Endpoints com Retrofit
// 2) Como criar/instanciar Endpoints com Retrofit
// 3) Como usar na prática

interface ApiEndpoints {

    interface LoginService {
        @POST("authenticate")
        suspend fun login(@Body body: RequestBody.LoginBody): Response<LoginResponse>
    }

    interface AlarmService {
        @GET("v1/alarms/occurrences")
        suspend fun getAlarmOccurences(@Header("Authorization") jwtToken: String): Response<List<Alarm>>

        @PUT("/v1/alarms/occurrences/{alarmOccurrenceId}/acknowledge")
        suspend fun acknowledgeAlarm(@Path("alarmOccurrenceId") alarmOccurrenceId: Int): Response<Unit>

        @PUT("/v1/alarms/occurrences/{alarmOccurrenceId}/comment/{text}")
        suspend fun commentAlarm(
            @Path("alarmOccurrenceId") alarmOccurrenceId: Int,
            @Path("text") text: String,
        ): Response<Unit>

        @GET("/v1/alarms/occurrences/{alarmOccurrenceId}")
        suspend fun fetchAlarmOccurences(@Path("alarmOccurrenceId") alarmId: Int): Response<Alarm>
    }
}