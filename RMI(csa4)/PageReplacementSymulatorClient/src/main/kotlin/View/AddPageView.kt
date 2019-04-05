package View

import PageController
import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Pos
import tornadofx.*


class AddPageView : View() {
    val controller: PageController by inject()

    private val data = SimpleIntegerProperty()
    private val sizeOfMemory = SimpleIntegerProperty()


    override val root = vbox {
        add(pageField())
        add(buttons())
    }

    private fun buttons() = hbox(spacing = 20, alignment = Pos.CENTER) {
        button("Add") {
            action { controller.save(data.value) }
        }

        button("Start") {
            action { controller.start(sizeOfMemory.value) }
        }
    }

    private fun pageField() = form {
        fieldset {
            field("data") {
                textfield(data) {
                    if (data.value != null)
                        controller.save(data.value.toInt())
                }

            }
            field("size of memory") {
                textfield(sizeOfMemory) {}
            }
        }
    }
}

