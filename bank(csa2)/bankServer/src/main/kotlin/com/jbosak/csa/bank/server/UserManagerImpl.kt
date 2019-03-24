package com.jbosak.csa.bank.server

import com.jbosak.csa.bank.server.model.User
import java.util.*

var users = arrayListOf<User>().also {
    it.add(User("mateusz", "m", 43))
    it.add(User("michal", "m", 654))
    it.add(User("john", "j", 560))
    it.add(User("woody", "w", 23))
    it.add(User("davis", "d", 765))
}

val methods: HashMap<String, Boolean> = hashMapOf<String, Boolean>().also{
    it["UserManager.options"] = false
    it["UserManager.register"] = false
    it["UserManager.login"] = false
    it["UserManager.accountBalance"] = true
    it["UserManager.transfer"] = true
    it["UserManager.logout"] = true
}


class UserManagerImpl : UserManager {



    override fun transfer(username: String, destinationUsername: String, amountOfMoney: Int): Boolean {
        if (userExist(destinationUsername)){
            val user = users.filter { username == it.username }[0]
            val destinationUser = users.filter { destinationUsername == it.username }[0]
            user.money-= amountOfMoney
            destinationUser.money += amountOfMoney
            return true
        }
        return false
    }



    override fun logout(): Boolean = true


    override fun register(user: User): Boolean {
        for (it in users){
            if (user.username == it.username){
                return false
            }
        }
        println(user)
        users.add(user)
        return true
    }

    override fun accountBalance(username: String): String? {
        println("ACCOUNT BALNCE")

            val user = users.filter { it.username == username }[0]
            println(user.toString())
            println(user.money)
            return user.money.toString()

    }


    override fun login(username: String, password: String): User? {
        if(isAuthenticated(username, password, "")){
            val user = User(username, password,0)
            for (it in users) {
                if(it.username == user.username && it.password == user.password) {
                    return it
                }
            }
        }
        return null
    }


    fun userExist(username: String): Boolean {
        users.forEach { if(it.username == username) return true }
        return false
    }
    private fun isAuthenticated(basicUserName: String?, basicPassword: String?, methodName: String): Boolean {
        if (methods.containsKey(methodName) && !methods[methodName]!!){
            return true
        }
        users.forEach {
            if (it.password == basicPassword && it.username == basicUserName){
                return  true
            }
        }
        return false
    }


}