import algorithm.LruImpl
import algorithm.OptImpl
import model.Page
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LruTest{

    @Test
    fun LruTest(){
        val optAlgorithm = LruImpl()
        val size = 3
        var pages = arrayListOf<Page>().also{
            it.add(Page(1))
            it.add(Page(2))
            it.add(Page(3))
            it.add(Page(4))
            it.add(Page(1))
            it.add(Page(2))
            it.add(Page(5))
            it.add(Page(1))
            it.add(Page(2))
            it.add(Page(3))
            it.add(Page(4))
            it.add(Page(5))
        }
        var numberOfFaults = optAlgorithm.run(size, pages = pages)
        Assertions.assertEquals(8, numberOfFaults)


    }

}