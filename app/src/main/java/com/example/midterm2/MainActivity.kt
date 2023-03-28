package com.example.midterm2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.midterm2.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val postsResult = viewModel.posts.observeAsState(Result.loading()).value
                    Column {
                        Button(onClick = { viewModel.getPosts() }) {
                            Text(text = "Get Posts")
                        }
                        PostList(postsResult)
                    }
                }
            }
        }
    }
}

@Composable
fun PostList(postsResult: Result<Results>) {
    when (postsResult) {
        is Result.Success -> {
            val posts = postsResult.data.results
            LazyColumn {
                items(posts) { post ->
                    Card(
                        Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            Modifier.padding(8.dp)
                        ) {
                            Text(text = post.name.title+" " + post.name.first+" " +post.name.last , style = MaterialTheme.typography.h5)
                            Text(text = post.email, style = MaterialTheme.typography.body1)
                        }
                    }
                }
            }
        }
        is Result.Error -> {
            Text(text = "Error: ${postsResult.exception.message}")
        }
        else -> {

        }
    }
}