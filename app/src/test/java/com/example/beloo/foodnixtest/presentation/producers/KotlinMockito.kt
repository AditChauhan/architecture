package com.example.beloo.foodnixtest.presentation.producers

import org.mockito.Mockito

fun <T> eq (obj : T) : T {
    return Mockito.eq(obj) ?: obj
}

fun <T> any(): T {
    Mockito.any<T>()
    return uninitialized()
}

fun <T> any(dump : T): T {
    Mockito.any  <T>()
    return dump
}

@Suppress("UNCHECKED_CAST")
private fun <T> uninitialized(): T = null as T
