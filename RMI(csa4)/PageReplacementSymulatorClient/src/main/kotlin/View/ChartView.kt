package View

import javafx.scene.Parent
import javafx.scene.layout.GridPane
import tornadofx.*
import PageController
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis

class ChartView: View(){
    override val root: Parent = GridPane()
    val controller: PageController by inject()

    init {
        with(root) {
            val items = controller.results.observable()
            this.gridpane {
                barchart("algorithms", CategoryAxis(), NumberAxis()){
                    series("page faults"){
                        data("FIFO", items[0])
                        data("OPT", items[1])
                        data("LRU", items[2])
                        data("ALRU", items[3])
                        data("RAND", items[4])
                    }
                }
            }
        }
    }

}


