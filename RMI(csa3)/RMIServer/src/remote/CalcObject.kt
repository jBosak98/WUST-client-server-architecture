package remote

import java.rmi.Remote

interface CalcObject: Remote {
    fun calculate(a: Double, b: Double): Double
}