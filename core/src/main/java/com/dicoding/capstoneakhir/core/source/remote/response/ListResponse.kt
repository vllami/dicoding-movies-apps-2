package com.dicoding.capstoneakhir.core.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListResponse(
    @SerializedName("results")
    val results: List<Response>
)