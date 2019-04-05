package View
import tornadofx.View
import tornadofx.hbox


class MainView: View(){

    override val root = hbox{
        add<AddPageView>()
        add<TableView>()

    }
}

