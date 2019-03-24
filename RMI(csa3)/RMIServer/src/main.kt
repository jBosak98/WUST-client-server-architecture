import remote.CalcObjImpl
import java.lang.Exception

fun main(args: Array<String>){
//    if(args.isEmpty()){
//        println("You have to enter RMI object address in the form: //host_address/service_name")
//        return
//    }
    val port = 5455
    if (System.getSecurityManager() == null){
        System.setSecurityManager(SecurityManager())
        return
    }
    try {
        val mySrvObj = CalcObjImpl()
        java.rmi.registry.LocateRegistry.createRegistry(port)
        java.rmi.Naming.rebind("//localhost:${port}/calc", mySrvObj)

        println("Server is registered now")
    } catch (e: Exception){
        println("Server can not be registered!")
        e.printStackTrace()
        return
    }
}