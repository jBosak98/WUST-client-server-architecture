import algorithm.*
import javafx.collections.FXCollections
import model.Page
import tornadofx.Controller
import java.rmi.registry.LocateRegistry
import java.rmi.registry.Registry

class PageController: Controller(){
    var fifo: Fifo? = null
    var opt: Opt? = null
    var lru : Lru? = null
    var alru: Alru? = null
    var rand: Rand? = null
    var pages = FXCollections.observableArrayList<Page>()


    init {
        val reg: Registry = LocateRegistry.getRegistry(1097)
        fifo =  reg.lookup("//localhost/fifo") as Fifo
        opt = reg.lookup("//localhost/opt") as Opt
        lru = reg.lookup("//localhost/lru") as Lru
        alru = reg.lookup("//localhost/alru") as Alru
        rand = reg.lookup("//localhost/rand") as Rand


    }
    fun save(inputValue: Int){
        println("Saving $inputValue")
        pages.add(Page(inputValue))

    }
    fun start(sizeOfMemory: Int){

        try {
            val fifoPages: ArrayList<Page> = arrayListOf()
            pages.forEach { fifoPages.add(Page(it.data)) }

            val fifoFaults = fifo?.run(sizeOfMemory,  fifoPages)
            val optFaults = opt?.run(sizeOfMemory,  fifoPages)
            val lruFaults = lru?.run(sizeOfMemory, fifoPages)
            val alruFaults = alru?.run(sizeOfMemory, fifoPages)
            val randFaults = rand?.run(sizeOfMemory, fifoPages)

            println("FIFO faults: $fifoFaults")
            println("OPT faults: $optFaults")
            println("LRU faults: $lruFaults")
            println("ALRU faults: $alruFaults")
            println("RAND faults: $randFaults")

        }catch (e: Exception){
            println("Remote call error")
            e.printStackTrace()

        }
    }



}
