package com.jbosak.csa.bank.server.model

import java.io.Serializable
import java.sql.Timestamp
import java.util.Date

class Session(var user: User?): Serializable {

    var expirationTime: Timestamp? = null

    init {
        expirationTime = Timestamp(Date().time + 1000000)
    }
}
