import java.rmi.server.UnicastRemoteObject
import java.util.*

class FifoImpl : //UnicastRemoteObject(),
    Fifo {P
    override fun run(memory: Array<Page?>, pages: Array<Page>): Int {
        pages.sortBy { it.arrivalTime }
        for (page in pages){
            var x = 0

            memory
                .filter {
                    if(it == null) return@filter true
                    it!!.data == page.data
                }
                .forEachIndexed{ index, it->
                if (it === null){
                    x = index
                    return@forEachIndexed
                }
                if (it.timeInMemory > memory[x]!!.timeInMemory){
                    x = index
                }

            }
            memory[x] = page
        }
        return 0
    }





}