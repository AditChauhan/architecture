package com.example.beloo.foodnixtest.presentation.util.recyclerView

internal interface Bind<in T> {
    fun bindItem(item : T)
    fun unbind()
}