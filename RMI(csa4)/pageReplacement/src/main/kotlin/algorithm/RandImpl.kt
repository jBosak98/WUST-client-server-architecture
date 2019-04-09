package algorithm

import RemotePagingAlgorithm
import model.Page
import kotlin.random.Random

class RandImpl: RemotePagingAlgorithm(), Rand{
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
                    val r = Random(5677654676456576231)
                    eldest = r.nextInt(0, memory.size)
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