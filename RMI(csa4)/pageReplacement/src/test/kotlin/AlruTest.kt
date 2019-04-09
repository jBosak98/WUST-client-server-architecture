import algorithm.AlruImpl
import algorithm.LruImpl
import model.Page
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AlruTest{


    @Test
    fun alruTest(){
        val alru = AlruImpl()
        val size = 3
        var pages = arrayListOf<Page>().also{
            it.add(Page(1))
            it.add(Page(2))
            it.add(Page(3))
            it.add(Page(4))
            it.add(Page(5))
            it.add(Page(3))
            it.add(Page(1))
            it.add(Page(5))
            it.add(Page(2))
        }
        var numberOfFaults = alru.run(4, pages = pages)
        Assertions.assertEquals(7, numberOfFaults)

    }
}