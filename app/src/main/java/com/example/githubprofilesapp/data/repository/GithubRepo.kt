package com.example.githubprofilesapp.data.repository

import com.example.githubprofilesapp.data.model.Repo
import com.example.githubprofilesapp.data.model.User
import com.example.githubprofilesapp.data.remote.api.GithubApiService

class GithubRepo(private val apiService: GithubApiService) {
    suspend fun getUsers(): List<User> = apiService.getUsers()
    suspend fun getUser(username: String): User = apiService.getUser(username)
    suspend fun getUserRepos(username: String): List<Repo> = apiService.getUserRepos(username)
}