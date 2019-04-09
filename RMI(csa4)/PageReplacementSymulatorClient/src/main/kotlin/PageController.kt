import javafx.collections.FXCollections
import model.Page
import tornadofx.Controller
import java.rmi.registry.LocateRegistry
import java.rmi.registry.Registry

class PageController: Controller(){
    var fifo: Fifo? = null
    var pages = FXCollections.observableArrayList<Page>()


    init {
        val reg: Registry = LocateRegistry.getRegistry(1097)
        fifo =  reg.lookup("//localhost/fifo") as Fifo

    }
    fun save(inputValue: Int){
        println("Saving $inputValue")
        pages.add(Page(inputValue))

    }
    fun start(sizeOfMemory: Int){

        try {
            val fifoPages: ArrayList<Page> = arrayListOf()
            pages.forEach { fifoPages.add(Page(it.data)) }

             val faults = fifo?.run(sizeOfMemory,  fifoPages)

            println("faults: $faults")

        }catch (e: Exception){
            println("Remote call error")
            e.printStackTrace()

        }
    }



}
