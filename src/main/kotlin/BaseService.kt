package ru.netology
abstract class BaseService<T> {
    private val items = mutableListOf<T>()
    private var lastId = 0

    fun getLastId() = ++lastId
    fun getItems() = items


    abstract fun add(item: T): Int
    abstract fun delete(item: T): Boolean
    abstract fun edit(item: T): Boolean


    fun printAll() {
        for (item in items) {
            println(item)
        }
    }

    fun clearAll() {
        items.clear()
        lastId = 0
    }


}