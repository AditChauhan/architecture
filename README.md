This sample project is created to show my view on building an architecture of android app

Features: 
* KISS principle. Implemented in most easy for read style. 
* Android Clean Architecture approach: separated core, data, network, domain, presentation layers
* Reactive app, based on RxJava2
* Room is used as database
* Dagger 2 is used as DI framework, Dagger-Android for injections to android framework components & presenters
* Retrofit is used for making requests, Jackson for parsing json on network layer
* Mockk is used for mocking, embedded androidX junit for assertions
* Data class types: PoJo's and entities, implements same interface
* Contract-based presentation layer
* Pagination for producers 
* Provided unit and integration test
* Constraint layout is used for building ui

Features to be done:
* Multi-module application. Layer-based or feature-based approach
* Robolectric integration test of use-cases with provided json mocks on network layer
* Lifecycle management based on LifeData or custom implementation
* Add several other screens
* Use navigation component to route between screens
* Requests is based on real server, migrate to MockWebServer
* Multi-activity, multi-fragment app. Activity is responsible of holding shareable memory for several screens with result at the end
* kotlin-coroutines variant
* shared transition animations between screens

Estimated time of same setup: **8 hours**
