package com.ericalfonsoponce.rick_and_morty_app.helpers.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T> Any.callResult(
    call: suspend () -> Response<T>
): Result<T?> {
    return try {
        withContext(Dispatchers.IO) {
            val response = call()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(response.code().parseErrorCode())
            }
        }
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}