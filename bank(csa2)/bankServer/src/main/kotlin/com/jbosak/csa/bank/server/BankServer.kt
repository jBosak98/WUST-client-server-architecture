package com.jbosak.csa.bank.server

interface BankServer {

    fun logout(): Boolean

    fun transfer(username: String, destinationUsername: String, amountOfMoney: Int): Boolean

    fun options(): String

    fun options(username: String, password: String): String

    fun register(username: String, password: String, money: Int): Boolean

    fun accountBalance(username: String): String?

    fun login(username: String, password: String): User?

//    fun isAuthenticated(username: String): Boolean

//    fun doSomething(): Session
}