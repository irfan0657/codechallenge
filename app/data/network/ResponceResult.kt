package com.example.codechallenge.data.network

/**
 * Api result sealed interface created to handle all three states
 */
sealed class ResponceResult<T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T? = null) : ResponceResult<T>(data = data)
    class Error<T>(errorMessage: String) : ResponceResult<T>(errorMessage = errorMessage)
    class Empty<T>(errorMessage: String) : ResponceResult<T>(errorMessage = errorMessage)
}
