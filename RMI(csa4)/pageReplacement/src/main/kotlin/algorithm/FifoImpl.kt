package algorithm

import RemotePagingAlgorithm
import model.Page

class FifoImpl : RemotePagingAlgorithm(), Fifo {
    override fun run(sizeOfMemory: Int,  pages: ArrayList<Page>): Int{
        var faultCounter = 0
        var memory: ArrayList<Page> = arrayListOf()
        var eldest = 0
        var pageCounter = 0
        while (pageCounter < pages.size){
            if(memory.isEmpty()){
                memory.add(pages[pageCounter])
                faultCounter++
            }else if (isInMemory(pages[pageCounter], memory).not()) {
                if (memory.size < sizeOfMemory){
                    memory.add(pages[pageCounter])
                    faultCounter++
                }else {
                    memory.mapIndexed { index, it ->
                        if (it.timeInMemory > memory[eldest].timeInMemory) eldest = index
                    }
                    faultCounter++
                    memory[eldest] = pages[pageCounter]
                }

            }
            pageCounter++
            memory.forEach { it.timeInMemory++ }
        }
        return faultCounter
    }

}