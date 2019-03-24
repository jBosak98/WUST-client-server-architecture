package com.jbosak.csa.bank.server


const val SERVER_PORT = 5059

fun main(){

    val bankServer = BankServer(5059)
    bankServer.setConfig(true,true)
    bankServer.start()


    System.out.println("Server started successfully");
    System.out.println("Listening on port: $SERVER_PORT")

}

