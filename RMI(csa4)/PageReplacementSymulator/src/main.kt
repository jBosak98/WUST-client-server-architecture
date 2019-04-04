import java.lang.Exception
import java.rmi.registry.LocateRegistry
import java.rmi.registry.Registry
import kotlin.concurrent.fixedRateTimer


fun main() {
    println("xd")
    val x = FifoImpl()
    val size = 3
    x.run(kotlin.arrayOfNulls(size), arrayOf(Page(1,2,3)))
//    val reg: Registry
//    try{
//        reg = LocateRegistry.createRegistry(1099)
//        java.rmi.Naming.rebind("//localhost/fifo", FifoImpl())
//        println("server registered")
//        println("Ctrl + C to stop")
//    }catch (e: Exception){
//        println("error!!")
//        e.printStackTrace()
//        return
//    }
}

//Page replacement algorithms
//simulation of algorithms: FIFO, OPT, LRU, ARLU, RAND.
//Compare their numbers of generated page faults.
//Please remember, that the sequence of the operations on memory pages is NOT random
//(it has properties of locality and symmetry).