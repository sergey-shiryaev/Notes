package ru.netology
data class Note(
    val id:Int = 0,
    val title:String = "",
    val text:String = "",
    var comments:Int = 0,
)