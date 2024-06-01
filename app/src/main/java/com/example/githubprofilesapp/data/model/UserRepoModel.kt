package com.example.githubprofilesapp.data.model

import com.google.gson.annotations.SerializedName

data class UserRepoModel(
    @SerializedName("name") val name:String,
    @SerializedName("description") val description:String?,
    @SerializedName("html_url") val htmlUrl: String
)
