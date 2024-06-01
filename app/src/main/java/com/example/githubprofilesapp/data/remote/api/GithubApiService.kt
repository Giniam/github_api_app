package com.example.githubprofilesapp.data.remote.api

import com.example.githubprofilesapp.data.model.UserRepoModel
import com.example.githubprofilesapp.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): User

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): List<UserRepoModel>
}