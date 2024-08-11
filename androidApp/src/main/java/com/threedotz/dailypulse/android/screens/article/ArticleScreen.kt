package com.threedotz.dailypulse.android.screens.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.threedotz.dailypulse.android.helper.ErrorMessage
import com.threedotz.dailypulse.articles.application.Article
import com.threedotz.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticlesScreen(
    onAboutButtonClick: () -> Unit,
    onSourcesButtonClick: () -> Unit,
    articlesViewModel: ArticlesViewModel = getViewModel()
) {
    val articleState = articlesViewModel.articlesState.collectAsState()

    Column {
        AppBar(onAboutButtonClick, onSourcesButtonClick)
//        if (articleState.value.loading)
//            Loader()
        if (articleState.value.error != null)
            ErrorMessage(articleState.value.error!!)
        if (articleState.value.articles.isNotEmpty())
            ArticlesListView(articlesViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onAboutButtonClick: () -> Unit,
    onSourcesButtonClick: () -> Unit
) {
    TopAppBar(title = { Text(text = "Articles") }, actions = {
        IconButton(onClick = onSourcesButtonClick) {
            Icon(imageVector = Icons.Outlined.List, contentDescription = "Sources Button")
        }
        IconButton(onClick = onAboutButtonClick) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "About Device Button"
            )
        }
    })
}


@Composable
fun ArticlesListView(viewModel: ArticlesViewModel) {

    SwipeRefresh(
        state = SwipeRefreshState(viewModel.articlesState.value.loading),
        onRefresh = { viewModel.getArticles(true) }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.articlesState.value.articles) { article ->
                ArticleItemView(article = article)
            }
        }
    }
}

@Composable
fun ArticleItemView(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(model = article.imageUrl, contentDescription = null)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )

    }
}