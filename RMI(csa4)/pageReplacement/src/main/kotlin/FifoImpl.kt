
class FifoImpl : //UnicastRemoteObject(),
    Fifo {
    override fun run(sizeOfMemory: Int,  pages: ArrayList<Page>): Pair<Int, ArrayList<Page>> {
        var faultCounter = 0
        var memory: ArrayList<Page> = arrayListOf()
        var eldest = 0
        var pageCounter = 0
        while (pageCounter < pages.size){

            if (memory.isEmpty() || memory.size < sizeOfMemory){
                memory.add(pages[pageCounter])
                faultCounter++
            }
            else if (isInMemory(pages[pageCounter], memory).not()) {
                memory.mapIndexed { index, it ->
                    if (it.timeInMemory > memory[eldest].timeInMemory) eldest = index
                }
                faultCounter++
                memory[eldest] = pages[pageCounter]
            }

            pageCounter++
            memory.forEach { it.timeInMemory++ }
        }
        return Pair(faultCounter, memory)
    }

    fun isInMemory(page: Page, memory: ArrayList<Page>): Boolean {
        for (m in memory){
            m.let {
                if (it.data == page.data) return true
            }
        }
        return false
    }
}