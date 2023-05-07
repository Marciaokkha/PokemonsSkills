package com.example.marcioyukio_rm86662

object Theme {
    var currentTheme = R.style.Theme_MarcioYukio_RM86662

    private const val ACTUAL = R.style.Theme_MarcioYukio_RM86662
    private const val NEW = R.style.Alternative

    fun switchTheme() {
        Theme.currentTheme = when (Theme.currentTheme) {
            ACTUAL -> NEW
            NEW -> ACTUAL
            else -> -1
        }
    }
}