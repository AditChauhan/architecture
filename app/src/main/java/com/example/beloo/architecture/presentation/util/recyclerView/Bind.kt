package com.example.beloo.architecture.presentation.util.recyclerView

internal interface Bind<in T> {
    fun bindItem(item : T, payloads: List<Any>)
    fun unbind()
}