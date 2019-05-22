package com.example.beloo.architecture.core.injection

class ComponentProvider(
	private val componentFactory: ComponentFactory) : ComponentCreator(componentFactory.appContext), ComponentFactory {

	private val globalComponent by lazy {
		componentFactory.globalComponent()
	}

	override fun globalComponent(): GlobalComponent = globalComponent

}
