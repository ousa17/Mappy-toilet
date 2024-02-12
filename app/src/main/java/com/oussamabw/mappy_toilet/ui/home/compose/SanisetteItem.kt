package com.oussamabw.mappy_toilet.ui.home.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oussamabw.mappy_toilet.data.network.SanisetteRecord


@Composable
fun SanisetteItem(sanisette: SanisetteRecord) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Address: ${sanisette.fields?.adresse}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Arrondissement: ${sanisette.fields?.arrondissement}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Coordinates: (${sanisette.geometry?.coordinates?.get(0)}, ${
                    sanisette.geometry?.coordinates?.get(
                        1
                    )
                })",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
