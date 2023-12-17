package com.nikolaej.volonyter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nikolaej.volonyter.data.MaksickViewModel
import com.nikolaej.volonyter.screen.Full
import com.nikolaej.volonyter.screen.Secondary
import com.nikolaej.volonyter.screen.primary


enum class Screen {
    Primary,
    Secondary,
    Full
}

@Composable
fun App(
    navController: NavHostController = rememberNavController(),
    viewModel: MaksickViewModel = viewModel(factory = MaksickViewModel.factory),
    gameviewModel: viewModel = viewModel()
) {

    val vid by viewModel.getAll().collectAsState(emptyList())
    val routelesson by viewModel.getAllvi(vid = gameviewModel.vid).collectAsState(emptyList())
    val name by viewModel.getNav(name = gameviewModel.name).collectAsState(emptyList())

    NavHost(navController = navController, startDestination = Screen.Primary.name) {

        composable(route = Screen.Primary.name) {
            primary(
                vid = vid,
                viewModel = gameviewModel,
                navController = navController
            )
        }

        composable(route = Screen.Secondary.name) {
            Secondary(
                name = routelesson,
                navController = navController,
                viewModel = gameviewModel
            )
        }

        composable(route = Screen.Full.name) {
            Full(
                name = name,
                viewModel = gameviewModel
            )
        }

    }
}