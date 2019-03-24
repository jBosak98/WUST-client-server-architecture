package remote

import java.rmi.Remote
import java.rmi.RemoteException


interface CalcObject: Remote {

    @Throws(RemoteException::class)
    fun calculate(a: Double, b: Double): Double
}