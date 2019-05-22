package com.example.beloo.foodnixtest.core.injection

import android.content.Context

/** the project might contain several dagger components
 * this factory is responsible of building components family
 * we may create factory implementation not only for production part, but for instrumentation
 * & integration testing also */
interface ComponentFactory {

    val appContext: Context

    fun globalComponent(): GlobalComponent

}
