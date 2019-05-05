package com.example.beloo.foodnixtest.core.injection

class ComponentProvider(
	private val componentFactory: ComponentFactory) : ComponentCreator(componentFactory.appContext), ComponentFactory {

	private val globalComponent by lazy {
		componentFactory.globalComponent()
	}

	override fun globalComponent(): GlobalComponent = globalComponent

}
