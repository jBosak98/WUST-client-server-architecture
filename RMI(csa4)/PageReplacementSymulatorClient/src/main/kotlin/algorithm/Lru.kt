package algorithm

import model.Page
import java.rmi.Remote
import java.rmi.RemoteException

interface Lru : Remote {
    @Throws(RemoteException::class)
    fun run(sizeOfMemory: Int,  pages: ArrayList<Page>): Int
}
