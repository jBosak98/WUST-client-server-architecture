import model.Page
import java.rmi.server.UnicastRemoteObject

abstract class RemotePagingAlgorithm : UnicastRemoteObject(){
    fun isInMemory(page: Page, memory: ArrayList<Page>): Boolean {
        for (m in memory){
            m.let {
                if (it.data == page.data) return true
            }
        }
        return false
    }
}