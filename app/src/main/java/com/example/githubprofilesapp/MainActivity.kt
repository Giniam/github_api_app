package com.example.githubprofilesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubprofilesapp.data.remote.RetrofitClient
import com.example.githubprofilesapp.data.repository.GithubRepo
import com.example.githubprofilesapp.ui.screen.UserDetailScreen
import com.example.githubprofilesapp.ui.screen.UserListScreen
import com.example.githubprofilesapp.ui.viewmodel.GitHubViewModelFactory
import com.example.githubprofilesapp.ui.viewmodel.GithubViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: GithubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService = RetrofitClient.apiService
        val repository = GithubRepo(apiService)
        val viewModelFactory = GitHubViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GithubViewModel::class.java)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "user_list") {
                composable("user_list") {
                    UserListScreen(viewModel) { username ->
                        navController.navigate("user_detail/$username")
                    }
                }
                composable("user_detail/{username}") { backStackEntry ->
                    val username = backStackEntry.arguments?.getString("username") ?: return@composable
                    UserDetailScreen(viewModel, username)
                }
            }
        }
    }
}