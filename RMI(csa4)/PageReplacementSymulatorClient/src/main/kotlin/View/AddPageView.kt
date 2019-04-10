package View

import PageController
import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Pos
import tornadofx.*
import kotlin.random.Random


class AddPageView : View() {
    val controller: PageController by inject()

    private val data = SimpleIntegerProperty()
    private val sizeOfMemory = SimpleIntegerProperty()


    override val root = vbox {
        add(pageField())
        add(buttons())
    }

    private fun buttons() = vbox (spacing = 20, alignment = Pos.CENTER) {
        button("Add") {
            action { controller.save(data.value) }
        }
        hbox (spacing = 20, alignment = Pos.CENTER){
            val random = Random(543645645645534)
            button("add Random") {
                action { controller.save(random.nextInt(0,5)) }
            }
            button("Start") {
                action {
                    controller.start(sizeOfMemory.value)
                    replaceWith(ChartView())
                }
            }
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

