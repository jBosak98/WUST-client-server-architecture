package remote

import remote.CalcObject
import java.rmi.RemoteException
import java.rmi.server.UnicastRemoteObject

class CalcObjImpl: UnicastRemoteObject(), CalcObject {

    @Throws(RemoteException::class)
    override fun calculate(a: Double, b: Double): Double = a + b

}