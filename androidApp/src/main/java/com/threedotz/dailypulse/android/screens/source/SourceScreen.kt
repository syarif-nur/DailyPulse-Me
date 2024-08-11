package com.threedotz.dailypulse.android.screens.source

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun SourceScreen(
    onUpButtonClick: () -> Unit,
    articlesViewModel: ArticlesViewModel = getViewModel()
) {
    val articleState = articlesViewModel.articlesState.collectAsState()

    Column {
        Toolbar(onUpButtonClick)
        if (articleState.value.error != null)
            ErrorMessage(articleState.value.error!!)
        if (articleState.value.articles.isNotEmpty())
            SourcesListView(articlesViewModel)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    onUpButtonClick: () -> Unit
) {
    TopAppBar(title = { Text(text = "Sources") }, navigationIcon = {
        IconButton(onClick = onUpButtonClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = "Up Button"
            )
        }
    })
}



@Composable
fun SourcesListView(viewModel: ArticlesViewModel) {

    SwipeRefresh(
        state = SwipeRefreshState(viewModel.articlesState.value.loading),
        onRefresh = { viewModel.getArticles(true) }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.articlesState.value.articles) { article ->
                SourceItemView(article = article)
            }
        }
    }
}

@Composable
fun SourceItemView(article: Article) {
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