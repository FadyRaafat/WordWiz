package com.fady.wordwiz.presentation.navgraph.main

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fady.wordwiz.presentation.navgraph.Route
import com.fady.wordwiz.presentation.home.HomeScreen
import com.fady.wordwiz.presentation.home.HomeViewModel

fun NavGraphBuilder.mainGraph(
) {
    composable(route = Route.HomeScreen.route) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        HomeScreen(
            homeViewModel.itemsState,
        ) { homeViewModel.OnEvent(it) }
    }

}
