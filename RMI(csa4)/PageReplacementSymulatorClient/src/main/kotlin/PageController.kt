import algorithm.*
import javafx.collections.FXCollections
import javafx.scene.chart.BarChart
import model.Page
import tornadofx.Controller
import tornadofx.MultiSeries
import tornadofx.observable
import java.rmi.registry.LocateRegistry
import java.rmi.registry.Registry

class PageController: Controller(){
    var fifo: Fifo? = null
    var opt: Opt? = null
    var lru : Lru? = null
    var alru: Alru? = null
    var rand: Rand? = null
    var pages = FXCollections.observableArrayList<Page>()
    var results = FXCollections.observableArrayList<Int>()


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

            var fifoFaults = 0
            var optFaults = 0
            var lruFaults = 0
            var alruFaults = 0
            var randFaults = 0
            fifo?.run(sizeOfMemory,  fifoPages)?.let { fifoFaults = it }
            opt?.run(sizeOfMemory,  fifoPages)?.let { optFaults = it }
            lru?.run(sizeOfMemory, fifoPages)?.let { lruFaults = it }
            alru?.run(sizeOfMemory, fifoPages)?.let { alruFaults = it }
            rand?.run(sizeOfMemory, fifoPages)?.let { randFaults = it }

            results = listOf(
                fifoFaults,
                optFaults,
                lruFaults,
                alruFaults,
                randFaults

            ).observable()

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
