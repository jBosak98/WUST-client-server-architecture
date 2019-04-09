import model.Page
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class OptTest{



    @Test
    fun testOpt() {
        val optAlgorithm = OptImpl()
        val size = 3
        var pages = arrayListOf<Page>().also{
            it.add(Page(1))
            it.add(Page(2))
            it.add(Page(3))
            it.add(Page(4))
            it.add(Page(5))
        }
        var numberOfFaults = optAlgorithm.run(size, pages = pages)
        Assertions.assertEquals(5, numberOfFaults)

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
        var numberOfFaults2 = optAlgorithm.run(3, pages)
        Assertions.assertEquals(8, numberOfFaults2)

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
        var numberOfFaults3  = optAlgorithm.run(4, pages)
        Assertions.assertEquals(7, numberOfFaults3)

    }

    @Test
    fun testGetFarthest(){
        val optAlgorithm = OptImpl()
        var pages = arrayListOf<Page>().also{
            it.add(Page(2))
            it.add(Page(1))
            it.add(Page(3))
            it.add(Page(4))
            it.add(Page(5))
        }
        var memory = arrayListOf<Page>().also {
            it.add(Page(6))
            it.add(Page(2))

        }
        var farthest = optAlgorithm.getFarthest(pages, memory)
        Assertions.assertEquals(0, farthest)

        pages = arrayListOf<Page>().also{
            it.add(Page(2))
            it.add(Page(1))
            it.add(Page(3))
            it.add(Page(4))
            it.add(Page(5))
        }
        memory = arrayListOf<Page>().also {
            it.add(Page(2))
            it.add(Page(6))

        }
        farthest = optAlgorithm.getFarthest(pages, memory)
        Assertions.assertEquals(1, farthest)


        pages = arrayListOf<Page>().also{
            it.add(Page(2))
            it.add(Page(1))
            it.add(Page(3))
            it.add(Page(4))
            it.add(Page(5))
        }
        memory = arrayListOf<Page>().also {
            it.add(Page(2))
            it.add(Page(3))

        }
        farthest = optAlgorithm.getFarthest(pages, memory)
        Assertions.assertEquals(1, farthest)



        pages = arrayListOf<Page>().also{
            it.add(Page(2))
            it.add(Page(1))
            it.add(Page(3))
            it.add(Page(4))
            it.add(Page(5))
        }
        memory = arrayListOf<Page>().also {
            it.add(Page(3))
            it.add(Page(2))

        }
        farthest = optAlgorithm.getFarthest(pages, memory)
        Assertions.assertEquals(0, farthest)

        pages = arrayListOf<Page>().also{
            it.add(Page(2))
            it.add(Page(1))
            it.add(Page(3))
            it.add(Page(4))
            it.add(Page(5))
        }
        memory = arrayListOf<Page>().also {
            it.add(Page(3))
            it.add(Page(2))
            it.add(Page(4))

        }
        farthest = optAlgorithm.getFarthest(pages, memory)
        Assertions.assertEquals(2, farthest)
    }

}