package com.example.githubprofilesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubprofilesapp.data.repository.GithubRepo

class GitHubViewModelFactory(private val repository: GithubRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GithubViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GithubViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}