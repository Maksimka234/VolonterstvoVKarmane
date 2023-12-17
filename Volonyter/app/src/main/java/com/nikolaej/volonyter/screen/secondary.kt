package com.nikolaej.volonyter.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nikolaej.volonyter.Screen
import com.nikolaej.volonyter.data.maksik
import com.nikolaej.volonyter.viewModel

@Composable
fun Secondary(
    name: List<maksik>,
    viewModel: viewModel,
    navController: NavHostController,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = viewModel.vid, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(4.dp))
        Divider()
        Spacer(modifier = Modifier.size(8.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = name,
                key = { name -> name.id }
            ) { name ->
                Card(
                    modifier = Modifier
                        .clickable {
                            viewModel.name = name.name
                            navController.navigate(Screen.Full.name)
                        }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(text = name.name, Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
            }
        }
    }
}