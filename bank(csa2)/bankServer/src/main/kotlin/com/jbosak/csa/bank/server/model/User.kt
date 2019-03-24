package com.jbosak.csa.bank.server.model

import java.io.Serializable


data class User(val username: String, val password: String, var money: Int): Serializable