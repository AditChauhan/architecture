package com.example.beloo.foodnixtest.core.injection

import android.content.Context

interface ComponentFactory {

    val appContext: Context

    fun globalComponent(): GlobalComponent

}
