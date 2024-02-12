package com.oussamabw.mappy_toilet.ui.home.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.oussamabw.mappy_toilet.data.network.SanisetteRecord
import com.oussamabw.mappy_toilet.ui.home.SanisetteViewModel

@Composable
fun SanisetteList(viewModel: SanisetteViewModel) {

    val lazySanisetteItems: LazyPagingItems<SanisetteRecord> =
        viewModel.sanisettes.collectAsLazyPagingItems()


    Column {

        Inputs(viewModel = viewModel)

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(lazySanisetteItems) { item ->
                item?.let { sanisette ->
                    SanisetteItem(sanisette)
                }
            }
        }

    }
}