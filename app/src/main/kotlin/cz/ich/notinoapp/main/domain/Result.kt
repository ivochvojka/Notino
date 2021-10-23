package cz.ich.notinoapp.main.domain

/**
 * Result of background operations.
 */
sealed class Result<out T : Any> {
    class Running<out T : Any> : Result<T>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error<out T : Any>(val errorMessage: String) : Result<T>()
}
