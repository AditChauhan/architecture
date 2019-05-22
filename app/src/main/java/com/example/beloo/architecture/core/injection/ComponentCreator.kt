package com.example.beloo.architecture.core.injection

import android.content.Context

open class ComponentCreator(override val appContext: Context) : ComponentFactory {

	override fun globalComponent(): GlobalComponent = DaggerGlobalComponent.builder()
		.globalModule(GlobalModule(appContext))
		.build()

}
