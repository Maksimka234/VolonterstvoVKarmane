package com.nikolaej.volonyter.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nikolaej.volonyter.Screen
import com.nikolaej.volonyter.data.maksik
import com.nikolaej.volonyter.viewModel

@Composable
fun primary(
    vid: List<maksik>,
    viewModel: viewModel,
    navController: NavHostController
) {

    var sosto: String? = ""


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Выберите вид волонтёрства, которым хотите заняться", modifier = Modifier.padding(8.dp), fontWeight = FontWeight.Bold)
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .weight(1f)){

            items(
                items = vid,
                key = { vid -> vid.id }
            ) { vid ->
                if (sosto != vid.vid) { //исключаем повторения
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.vid = vid.vid
                                navController.navigate(Screen.Secondary.name)
                            }
                            .padding(horizontal = 8.dp, vertical = 5.dp)
                    ) {
                        Text(text = vid.vid, Modifier.padding(4.dp))
                    }
                    sosto = vid.vid
                }
            }
        }
    }
}