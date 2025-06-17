package com.quests.demo.presentation.util

import com.google.gson.Gson


// Extension function to convert a JSON string to an object of a specific type
inline fun <reified T> String.fromJson(json: String): T = Gson().fromJson(json, T::class.java)

fun <T> T.toJson(): String = Gson().toJson(this)