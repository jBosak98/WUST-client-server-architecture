import algorithm.Fifo
import algorithm.Lru
import algorithm.Opt
import javafx.collections.FXCollections
import model.Page
import tornadofx.Controller
import java.rmi.registry.LocateRegistry
import java.rmi.registry.Registry

class PageController: Controller(){
    var fifo: Fifo? = null
    var opt: Opt? = null
    var lru : Lru? = null
    var pages = FXCollections.observableArrayList<Page>()


    init {
        val reg: Registry = LocateRegistry.getRegistry(1097)
        fifo =  reg.lookup("//localhost/fifo") as Fifo
        opt = reg.lookup("//localhost/opt") as Opt
        lru = reg.lookup("//localhost/lru") as Lru


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

            println("FIFO faults: $fifoFaults")
            println("OPT faults: $optFaults")
            println("LRU faults: $lruFaults")

        }catch (e: Exception){
            println("Remote call error")
            e.printStackTrace()

        }
    }



}
