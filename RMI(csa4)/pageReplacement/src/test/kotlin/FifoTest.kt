import model.Page
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FifoTest {

    @Test
    fun testFifo() {
        val fifoAlgorithm = FifoImpl()
        val size = 3
        var pages = arrayListOf<Page>().also{
            it.add(Page(1))
            it.add(Page(2))
            it.add(Page(3))
            it.add(Page(4))
            it.add(Page(5))
        }
        var numberOfFaults = fifoAlgorithm.run(size, pages = pages)
        assertEquals(5, numberOfFaults)

        pages = arrayListOf<Page>().also {
            it.add(Page(3))
            it.add(Page(2))
            it.add(Page(1))
            it.add(Page(0))
            it.add(Page(3))
            it.add(Page(2))
            it.add(Page(4))
            it.add(Page(3))
            it.add(Page(2))
            it.add(Page(1))
            it.add(Page(0))
            it.add(Page(4))
        }
        var numberOfFaults2 = fifoAlgorithm.run(3, pages)
        assertEquals(9, numberOfFaults2)
        pages = arrayListOf<Page>().also {
            it.add(Page(3))
            it.add(Page(2))
            it.add(Page(1))
            it.add(Page(0))
            it.add(Page(3))
            it.add(Page(2))
            it.add(Page(4))
            it.add(Page(3))
            it.add(Page(2))
            it.add(Page(1))
            it.add(Page(0))
            it.add(Page(4))
        }
        var numberOfFaults3  = fifoAlgorithm.run(4, pages)
        assertEquals(10, numberOfFaults3)

    }

    @Test
    fun testInMemory(){
        val fifoAlgorithm = FifoImpl()
        var p1 = Page(2)
        var memory: ArrayList<Page> = arrayListOf(
            Page(1),
            p1,
            Page(3)
        )
        assertTrue(fifoAlgorithm.isInMemory(p1, memory))


        memory = ArrayList(3)
        assertFalse(fifoAlgorithm.isInMemory(p1, memory))
        memory = arrayListOf(Page(1, 1))

        assertTrue(fifoAlgorithm.isInMemory(Page(1), memory))
        assertFalse(fifoAlgorithm.isInMemory(Page(0), memory))
        assertFalse(fifoAlgorithm.isInMemory(Page(2), memory))
        assertFalse(fifoAlgorithm.isInMemory(Page(3), memory))
        memory = arrayListOf(
            Page(1),
            Page(2),
            Page(3)
        )
        assertTrue(fifoAlgorithm.isInMemory(Page(1), memory))
        assertTrue(fifoAlgorithm.isInMemory(Page(2), memory))
        assertTrue(fifoAlgorithm.isInMemory(Page(3), memory))
        assertFalse(fifoAlgorithm.isInMemory(Page(4), memory))
        assertFalse(fifoAlgorithm.isInMemory(Page(0), memory))
        assertFalse(fifoAlgorithm.isInMemory(Page(5), memory))
        assertFalse(fifoAlgorithm.isInMemory(Page(-1), memory))
    }

}