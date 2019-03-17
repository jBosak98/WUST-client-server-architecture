package com.jbosak.csa.bank.server

import org.omg.CORBA.Object
import java.sql.Timestamp
import java.util.*

var users = arrayListOf<User>().also {
    it.add(User("mateusz", "m", 43))
    it.add(User("michal", "m", 654))
    it.add(User("john", "j", 560))
    it.add(User("woody", "w", 23))
    it.add(User("davis", "d", 765))
}


class SessionManager : BankServer{
//    override fun doSomething(user:Object): String {
//        print(user)
//        return  ""
////        return User("ds","fdsds")
//    }

    var sessions = arrayListOf<Session>()
    val methods: HashMap<String, Boolean> = HashMap()
    init {
        methods["options"] = false
        methods["register"] = false
        methods["login"] = false
        methods["accountBalance"] = true
        methods["transfer"] = true
        methods["logout"] = true
    }


    override fun logout(username: String, password: String): Boolean {
        if (isAuthenticated(username)){
            val session = sessions.filter { it.user.username == username }[0]
            sessions.remove(session)
            return true
        }
        return true
    }

    override fun transfer(username: String, password: String, destinationUsername: String, amountOfMoney: Int): Boolean{
        if (isAuthenticated(username) && userExist(destinationUsername)){
            val user = users.filter { username == it.username }[0]
            val destinationUser = users.filter { destinationUsername == it.username }[0]
            user.money-= amountOfMoney
            destinationUser.money += amountOfMoney
            return true
        }
        return false
    }

    override fun options(): String {
        return methods.filter{ !it.value }.map { it.key }.toString()
    }

    override fun options(username: String, password: String): String {
        return methods.map { it.key }.toString()
    }


    override fun register(username: String, password: String, money: Int): Boolean {
        val user = User(username,password,money)

        for (it in users){
            if (user.username == it.username){
                return false
            }
        }
        println(user)
        users.add(user)
        return true
    }

    override fun accountBalance(username: String, password: String): String? {
        if (isAuthenticated(username)){
            val user = users.filter { it.username == username }[0]
            println(user.toString())
            println(user.money)
            return user.money.toString()
        }
        return "not logged in"

    }


    override fun login(username: String, password: String): Boolean {
        val user = User(username,password)
        for (it in users) {
            if(it.username == user.username && it.password == user.password){
                val session = Session(it)
                sessions.add(session)
                print(session.expirationTime)
                return true
            }
        }
        return false
    }

    override fun isAuthenticated(username: String): Boolean {
        print(username)
        if (userExist(username).not()) return false
        var expirationDate:Timestamp? = null
        sessions.forEach {
            if (it.user.username == username){
                expirationDate = it.expirationTime
            }
        }

        if (expirationDate == null) return false
        return expirationDate!! > Timestamp(Date().time)
    }


    fun userExist(username: String): Boolean {
        users.forEach { if(it.username == username) return true }
        return false
    }


}