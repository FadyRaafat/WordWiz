package com.fady.wordwiz.presentation.navgraph

sealed class Route(val route: String) {

    data object SplashScreen : Route("splash")
    data object HomeScreen : Route("home")

}
