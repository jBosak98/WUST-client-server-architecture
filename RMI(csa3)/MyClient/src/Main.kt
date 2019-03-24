import remote.CalcObject
import java.lang.Exception
import java.rmi.Remote

fun main(args: Array<String>){
//    args.ifEmpty {
//        println("You have to enter RMI object address in the form //host_address/service_name")
//        return
//    }
    val port = 5455
    val address = "//localhost:$port/calc"
//        args[0]

    val rObject: Remote

    try {
        rObject = java.rmi.Naming.lookup(address) as CalcObject
    } catch (e: Exception){
        println("Reference to $address can not be retrieved!")
        e.printStackTrace()
        return
    }
    println(System.out.println("Reference to $address is retrieved"))
    val result: Double

    try {
        result = rObject.calculate(1.1,2.2)
    } catch (e: Exception){
        println("Remote call error!")
        e.printStackTrace()
        return
    }

    println("Result: $result")
    return
}