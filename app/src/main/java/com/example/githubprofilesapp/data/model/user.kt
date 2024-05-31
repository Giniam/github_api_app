package com.example.githubprofilesapp.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("followers_url") val followersUrl: String,
    @SerializedName("following_url") val followingUrl: String,
    @SerializedName("gists_url") val gistsUrl: String,
    @SerializedName("starred_url") val starredUrl: String,
    @SerializedName ("subscriptions") val subscriptionsUrl: String,
    @SerializedName ("organizations") val organizationsUrl: String,
    @SerializedName ("repos_url") val reposUrl: String,
    @SerializedName ("events_url") val eventsUrl: String,
    @SerializedName ("received_event")val receivedEventsUrl: String,
    @SerializedName("type") val type: String,
    @SerializedName("site_admin")val siteAdmin: Boolean
)
