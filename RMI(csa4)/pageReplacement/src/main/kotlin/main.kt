import algorithm.*
import java.rmi.registry.LocateRegistry
import java.rmi.registry.Registry

fun main() {
    println("xd")
    val reg: Registry
    try{
        reg = LocateRegistry.createRegistry(1097)
        reg.rebind("//localhost/fifo", FifoImpl())
        reg.rebind("//localhost/opt", OptImpl())
        reg.rebind("//localhost/lru", LruImpl())
        reg.rebind("//localhost/alru", AlruImpl())
        reg.rebind("//localhost/rand", RandImpl())
        println("server registered")
        println("Ctrl + C to stop")
    }catch (e: Exception){
        println("error!!")
        e.printStackTrace()
        return
    }
}

//model.Page replacement algorithms
//simulation of algorithms: FIFO, OPT, LRU, ARLU, RAND.
//Compare their numbers of generated page faults.
//Please remember, that the sequence of the operations on memory pages is NOT random
//(it has properties of locality and symmetry).