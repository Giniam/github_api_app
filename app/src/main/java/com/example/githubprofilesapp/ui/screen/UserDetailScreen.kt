package com.example.githubprofilesapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.githubprofilesapp.data.model.UserRepoModel
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(8.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .width(200.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Image(
                                painter = rememberImagePainter(it.avatarUrl),
                                contentDescription = null,
                                modifier = Modifier.size(100.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = it.login,
                                fontWeight = FontWeight.Bold,
                                fontSize = 32.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = it.name)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

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
fun RepoItem(userRepoModel: UserRepoModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF000000)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        ) {
            Text(text = userRepoModel.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            Spacer(modifier = Modifier.width(4.dp))
            userRepoModel.description?.let { Text(text = it,
                color = Color.Gray
                )
            }
        }
    }
}