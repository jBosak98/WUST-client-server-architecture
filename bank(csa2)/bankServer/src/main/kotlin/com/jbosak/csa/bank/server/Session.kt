package com.jbosak.csa.bank.server

import java.io.Serializable
import java.sql.Timestamp
import java.util.*

class Session(var user: User?): Serializable {

    var expirationTime: Timestamp? = null

    init {
        expirationTime = Timestamp(Date().time + 1000000)
    }
}
