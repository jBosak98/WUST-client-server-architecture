import java.rmi.Remote
import java.rmi.RemoteException

interface Fifo : Remote {
    @Throws(RemoteException::class)
    fun run(sizeOfMemory: Int, pages: ArrayList<Page>): Pair<Int, ArrayList<Page>>
}