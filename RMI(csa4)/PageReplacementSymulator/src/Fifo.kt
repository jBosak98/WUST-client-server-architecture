import java.rmi.Remote
import java.rmi.RemoteException

interface Fifo : Remote {
    @Throws(RemoteException::class)
    fun run(memory: Array<Page?>, pages: Array<Page>):Int
}
