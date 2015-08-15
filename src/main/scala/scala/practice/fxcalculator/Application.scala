package scala.practice.fxcalculator

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.Priority.Always
import scalafx.scene.paint.Color

object Application extends JFXApp {

  def calculatorButton(label: String, colspan: Int = 1): Button = {
    new Button {
      text = label
      hgrow = Always
      vgrow = Always
      prefHeight = 50
      prefWidth = 50 * colspan
    }
  }

  stage = new JFXApp.PrimaryStage {
    title.value = "Simple calculator"
    width = 200
    height = 300
    resizable = false
    scene = new Scene {
      content = new GridPane {
        add(new Label {
          prefWidth = 200
          prefHeight = 50
          fill = Color.White
        }, 0, 0, 4, 1)

        add(calculatorButton("7"), 0, 1)
        add(calculatorButton("8"), 1, 1)
        add(calculatorButton("9"), 2, 1)
        add(calculatorButton("-"), 3, 1)

        add(calculatorButton("4"), 0, 2)
        add(calculatorButton("5"), 1, 2)
        add(calculatorButton("6"), 2, 2)
        add(calculatorButton("+"), 3, 2)

        add(calculatorButton("1"), 0, 3)
        add(calculatorButton("2"), 1, 3)
        add(calculatorButton("3"), 2, 3)
        add(calculatorButton("="), 3, 3)

        add(calculatorButton("0", 2), 0, 4, 2, 1)
      }
    }
  }
}