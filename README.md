# Notino interview project

The application shows list of Notino products.

The setup of application is written in Gradle Kotlin DSL. The application is based on *MVVM* design pattern with *Hilt DI*, *Kotlin Coroutines*, *ViewBinding*, *StateFlow*, *Room DB*, *Retrofit* and *Glide*.

Notino products are downloaded from remote API and visible on screen which has different portrait and landscape layout.

Product ratings are displayed with standard [RatingBar](https://developer.android.com/reference/android/widget/RatingBar) because provided images for stars are the same as default RatingBar and also they are in vector graphic which cannot be used with standard [RatingBar](https://developer.android.com/reference/android/widget/RatingBar). There are two possibilities how solve rating bar with vector drawables:
1. Convert vector image to raster
2. Write custom RatingBar component (which takes more time than requested in interview email)

The heart is clickable with animation and also the status of heart is saved into local DB.

There is also basic error handling and there are no tests because of requested time to coding the application.
