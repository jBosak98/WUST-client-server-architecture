package com.jbosak.csa.bank.server

import com.jbosak.csa.bank.server.model.User

interface UserManager {

    fun logout(): Boolean

    fun transfer(username: String, destinationUsername: String, amountOfMoney: Int): Boolean

    fun register(user: User): Boolean

    fun accountBalance(username: String): String?

    fun login(username: String, password: String): User?


}