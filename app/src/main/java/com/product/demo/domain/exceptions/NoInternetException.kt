package com.product.demo.domain.exceptions

class NoInternetException(
    message: String = "No internet connection",
    cause: Throwable? = null
) : Exception(message, cause)