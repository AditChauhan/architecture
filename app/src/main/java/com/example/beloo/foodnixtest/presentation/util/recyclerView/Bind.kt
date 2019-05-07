package com.example.beloo.foodnixtest.presentation.util.recyclerView

internal interface Bind<in T> {
    fun bindItem(item : T, payloads: List<Any>)
    fun unbind()
}