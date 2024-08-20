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
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.threedotz.dailypulse.android.helper.ErrorMessage
import com.threedotz.dailypulse.sources.application.Source
import com.threedotz.dailypulse.sources.presentation.SourcesViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun SourceScreen(
    onUpButtonClick: () -> Unit,
    sourcesViewModel: SourcesViewModel = getViewModel()
) {
    val articleState = sourcesViewModel.sourceState.collectAsState()

    Column {
        Toolbar(onUpButtonClick)
        if (articleState.value.error != null)
            ErrorMessage(articleState.value.error!!)
        if (articleState.value.sources.isNotEmpty())
            SourcesListView(sourcesViewModel)
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
fun SourcesListView(viewModel: SourcesViewModel) {

    SwipeRefresh(
        state = SwipeRefreshState(viewModel.sourceState.value.loading),
        onRefresh = { viewModel.getSources(true) }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.sourceState.value.sources) { sources ->
                SourceItemView(source = sources)
            }
        }
    }
}

@Composable
fun SourceItemView(source: Source) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
//        AsyncImage(model = article.imageUrl, contentDescription = null)
//        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = source.name,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = source.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = source.langCountry,
            style = TextStyle(Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )

    }
}