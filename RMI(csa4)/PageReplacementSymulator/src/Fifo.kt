import java.rmi.Remote
import java.rmi.RemoteException

interface Fifo : Remote {
    @Throws(RemoteException::class)
    fun run():Int
}
