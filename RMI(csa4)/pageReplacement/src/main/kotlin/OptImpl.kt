import model.Page

class OptImpl: Opt, RemotePagingAlgorithm() {
    override fun run(sizeOfMemory: Int, pages: ArrayList<Page>): Int {
        var faultCounter = 0
        var memory: ArrayList<Page> = arrayListOf()
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
                    faultCounter++
                    memory[getFarthest(pages,memory)] = pages[pageCounter]
                }

            }
            pageCounter++
            memory.forEach { it.timeInMemory++ }
        }
        return faultCounter
    }

    fun getFarthest(pages: ArrayList<Page>, memory: ArrayList<Page>): Int{
        var isInPages = false
        var farthest = 0
        memory.forEachIndexed { index, memoryPage ->
            isInPages = false
            pages.forEachIndexed { pageIndex, page ->
                if (memoryPage.data == page.data){
                    isInPages = true
                    if (pageIndex > farthest){
                        farthest = index
                    }
                }

            }
            if (isInPages.not()) return index
        }
        return farthest
    }


}