package com.bootcamp.kotlin.networking

import retrofit2.HttpException
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1),
}
object Error{
    const val unauthorized = "Unauthorized"
    const val notFound = "Not found"
    const val timeOut = "Time Out"
    const val generic = "Something went wrong"
}
object CodError{
    const val UNAUTHORIZED = 401
    const val NOT_FOUND = 404
}

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Resource.error(getErrorMessage(ErrorCodes.SocketTimeOut.code), null)
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> Error.timeOut
            CodError.UNAUTHORIZED -> Error.unauthorized
            CodError.NOT_FOUND -> Error.notFound
            else -> Error.generic
        }
    }
}
