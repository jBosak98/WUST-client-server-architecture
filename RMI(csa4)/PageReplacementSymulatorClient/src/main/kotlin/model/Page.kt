package model

import java.io.Serializable

data class Page(
    var data: Int,
    var timeInMemory: Int = 0
): Serializable
