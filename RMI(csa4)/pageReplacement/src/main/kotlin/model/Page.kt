package model

import java.io.Serializable

data class Page(
    val data: Int,
    var timeInMemory: Int = 0
): Serializable
