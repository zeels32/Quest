package com.quests.demo.presentation.util.exceptions

class NoInternetException(
    message: String = "No internet connection",
    cause: Throwable? = null
) : Exception(message, cause)