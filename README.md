# UlessonTakeHome

[![kotlin](https://img.shields.io/badge/Kotlin-1.4.xx-blue)](https://kotlinlang.org/) [![coroutines](https://img.shields.io/badge/Kotlin-Coroutines-orange)](https://developer.android.com/kotlin/coroutines) [![Dagger](https://img.shields.io/badge/Dagger-Hilt-orange)](https://dagger.dev/hilt)

The UlessonTakeHome App displays a list of courses, taken in a class, and video tutorials on details of the course.

I made a couple of decisions while writing it, e.g making use of a dagger hilt to provide dependencies to needed classes.

Implementing a bit of clean architecture, by having separated data layers and cache layers, with their separate models.

I made use of Kotlin flow for the asynchronous aspect over RXJava, because it's been looking like the easier and better library to use of recent.


## Features
* Kotlin Coroutines with Flow, for moving data to the various app layers.
* Clean Architecture in bits with a sprinkle of Uni-directional data flow and proper state management for stateful fragments.
* Jetpack Navigation used to navigate between fragments.
* Video streaming with Exoplayer.
* Dagger Hilt for dependencies.



## Libraries
*   [GSON](https://sites.google.com/site/gson/)
*   [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
*   [Kotlin Flow](https://developer.android.com/kotlin/flow)
*   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
*   [Navigation Component](https://developer.android.com/guide/navigation)
*   [Coil](https://github.com/coil-kt/coil)
*   [Exoplayer](https://github.com/google/ExoPlayer)
*   [Dagger Hilt](https://dagger.dev/hilt)


## Author
Momoh Great

## License
This project is licensed under the Apache License 2.0 - See: http://www.apache.org/licenses/LICENSE-2.0.txt

