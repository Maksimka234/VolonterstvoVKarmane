package com.nikolaej.volonyter.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nikolaej.volonyter.data.maksik
import com.nikolaej.volonyter.viewModel

@Composable
fun Full(
    name: List<maksik>,
    viewModel: viewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = viewModel.name, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(4.dp))
        Divider()
        Spacer(modifier = Modifier.size(8.dp))
        LazyColumn() {
            items(
                items = name,
                key = { name -> name.id }
            ) { name ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(4.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Дата: ", fontWeight = FontWeight.Bold)
                            Text(text = name.date)
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Время: ", fontWeight = FontWeight.Bold)
                            Text(text = name.time)
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Адрес: ", fontWeight = FontWeight.Bold)
                            Text(text = name.mesto)
                        }

                    }

                }
            }
        }
    }
}