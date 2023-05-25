package com.example.factsapp.models

import com.google.gson.annotations.SerializedName

data class Fact (
    @SerializedName("text")
    val text: String?,
    @SerializedName("year")
    val year: Int?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("found")
    val found: Boolean?,
    @SerializedName("type")
    val type: String?
)