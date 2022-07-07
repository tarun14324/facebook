package com.example.facebook.api

import com.example.facebook.dataclasses.ChangePasswordRequest
import com.example.facebook.dataclasses.LoginRequest
import com.example.facebook.dataclasses.RegisterRequest
import com.example.facebook.util.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/api/v1/login")
    suspend fun signin(@Body info: LoginRequest): ApiResponse<Any>

    @POST("/api/v1/register")
    fun registerUser(
        @Body info: RegisterRequest
    ): ApiResponse<RegisterRequest>

    @POST("/api/v1/changePassword/:userId")
    fun changePassword(
        @Body details: ChangePasswordRequest
    ): ApiResponse<ChangePasswordRequest>

}