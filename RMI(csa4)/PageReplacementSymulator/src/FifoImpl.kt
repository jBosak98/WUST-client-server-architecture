import java.rmi.server.UnicastRemoteObject

class FifoImpl : UnicastRemoteObject(), Fifo{
    override fun run(): Int {
        return 0
    }

}