# ClearScoreTestApp


## Summary
The app pulls credit score information content from ClearScore API. It is built according to the `Model-View-ViewModel`(MVVM) architecture.
## Architecture


For dependency injection and asynchronous programming, the project uses Dagger Hilt and Coroutines. Dagger Hilt is a fine abstraction over the vanilla dagger boilerplate, and is easy to setup. Coroutines brings kotlin's expressibility and conciseness to asynchronous programming, along with a fine suite of operators that make it a robust solution. 

#### UI
The ViewModel which is the presenter implementation is very lean. The reason for using the `Jetpack ViewModel` is that it survives configuration changes, and thus ensures that the view state is persisted across screen rotation.

MVVM is a good architecture to use when you don't want any surprises in user experience as state only comes from one source and is immutable.


## Features
* Clean Architecture with MVVM
* Kotlin Coroutines with LiveData
* Dagger Hilt
* Jetpack Compose
* Kotlin Gradle DSL

## Future updates
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- [Jetpack Compose State](https://developer.android.com/jetpack/compose/state#state-in-composables)


## Libraries
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Dagger Hilt](https://dagger.dev/hilt)
- [Kotlinx Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Retrofit](https://square.github.io/retrofit/)
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
- [Kotlin Gradle DSL](https://guides.gradle.org/migrating-build-logic-from-groovy-to-kotlin)
