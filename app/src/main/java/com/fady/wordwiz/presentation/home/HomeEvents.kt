package com.fady.wordwiz.presentation.home


sealed class HomeEvents {

    data class OnSearch(val word: String) : HomeEvents()

    data object RefreshHistory : HomeEvents()

}
