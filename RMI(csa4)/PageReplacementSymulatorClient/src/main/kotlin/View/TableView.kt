package View

import model.Page
import tornadofx.*
import PageController


class TableView : View(){


    val controller: PageController by inject()

    override val root = tableview(controller.pages) {
        isEditable = true
        columnResizePolicy = SmartResize.POLICY
        column("data", Page::data).makeEditable()

    }
}
