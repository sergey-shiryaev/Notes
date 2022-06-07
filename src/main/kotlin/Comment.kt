package ru.netology
data class Comment(
    val commentId:Int = 0,
    val noteId:Int = 0,
    val message:String = "",
    val wasDeleted:Boolean = false
)
