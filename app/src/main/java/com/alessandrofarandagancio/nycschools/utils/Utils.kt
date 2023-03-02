package com.alessandrofarandagancio.nycschools.utils

object Utils {
    /*
    Remove te brachets "(/)" that contains latitude and longitude in the location string provided from SODA services
     */
    fun removeBrachetsFromLocationString(string: String): String {
        val regex = "\\(.*?\\)".toRegex() // matches with every ( ... )
        return string.replace(regex, "")
    }

}