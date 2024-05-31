package com.example.githubprofilesapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.githubprofilesapp.data.model.Repo
import com.example.githubprofilesapp.ui.viewmodel.GithubViewModel


@Composable
fun UserDetailScreen(viewModel: GithubViewModel, username: String) {
    val user by viewModel.selectedUser.collectAsState()
    val repos by viewModel.userRepos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(username) {
        viewModel.fetchUser(username)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (errorMessage != null) {
            Text(text = errorMessage!!, modifier = Modifier.align(Alignment.Center))
        } else {
            user?.let {
                Column(modifier = Modifier.align(Alignment.TopCenter).padding(8.dp)) {
                    Text(text = it.login)
                    LazyColumn {
                        items(repos) { repo ->
                            RepoItem(repo)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RepoItem(repo: Repo) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = repo.name)
        repo.description?.let { Text(text = it) }
    }
}