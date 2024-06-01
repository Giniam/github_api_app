package com.example.githubprofilesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubprofilesapp.data.model.UserRepoModel
import com.example.githubprofilesapp.data.model.User
import com.example.githubprofilesapp.data.repository.GithubRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GithubViewModel(private val repository: GithubRepo): ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users

    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: StateFlow<User?> get() = _selectedUser

    private val _userRepos = MutableStateFlow<List<UserRepoModel>>(emptyList())
    val userRepos: StateFlow<List<UserRepoModel>> get() = _userRepos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _users.value = repository.getUsers()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchUser(username: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _selectedUser.value = repository.getUser(username)
                _userRepos.value = repository.getUserRepos(username)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}