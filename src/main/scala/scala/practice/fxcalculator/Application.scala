package scala.practice.fxcalculator

import javafx.event.EventHandler
import javafx.scene.control.{Button => JFXButton}
import javafx.scene.input.{KeyCode, KeyEvent}

import scala.practice.fxcalculator.domain.Calculator
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.GridPane

object Application extends JFXApp {
  val numberRe = "([0-9])".r
  val operators = List("*", "/", "+", "-")
  val keyInputs = Map(
    KeyCode.DIGIT0 -> "0", KeyCode.NUMPAD0 -> "0",
    KeyCode.DIGIT1 -> "1", KeyCode.NUMPAD1 -> "1",
    KeyCode.DIGIT2 -> "2", KeyCode.NUMPAD2 -> "2",
    KeyCode.DIGIT3 -> "3", KeyCode.NUMPAD3 -> "3",
    KeyCode.DIGIT4 -> "4", KeyCode.NUMPAD4 -> "4",
    KeyCode.DIGIT5 -> "5", KeyCode.NUMPAD5 -> "5",
    KeyCode.DIGIT6 -> "6", KeyCode.NUMPAD6 -> "6",
    KeyCode.DIGIT7 -> "7", KeyCode.NUMPAD7 -> "7",
    KeyCode.DIGIT8 -> "8", KeyCode.NUMPAD8 -> "8",
    KeyCode.DIGIT9 -> "9", KeyCode.NUMPAD9 -> "9",
    KeyCode.EQUALS -> "=",
    KeyCode.PLUS -> "+",
    KeyCode.MINUS -> "-",
    KeyCode.MULTIPLY -> "*",
    KeyCode.DIVIDE -> "/")

  val label = new Label {
    prefWidth = 200
    prefHeight = 50
  }

  object Operation extends Enumeration {
    type Operation = Value
    val NONE, VALUE, OPERATOR, RESULT = Value
  }

  var lastOperation = Operation.NONE

  val buttonClickListener = {
    (e: ActionEvent) => onInput(e.getSource.asInstanceOf[JFXButton].getText)
  }

  val keyEventListener = new EventHandler[KeyEvent] {
    override def handle(event: KeyEvent): Unit =
      keyInputs.get(event.getCode).foreach(operation => onInput(operation))
  }

  def onInput(input: String): Unit = {
    input match {
      case numberRe(t) =>
        lastOperation = Operation.VALUE
        label.text = label.text.value + t
      case t if operators.contains(t) && lastOperation != Operation.OPERATOR =>
        lastOperation = Operation.OPERATOR
        label.text = label.text.value + " " + t + " "
      case "=" =>
        lastOperation = Operation.RESULT
        try {
          val result: Double = Calculator.calculate(label.text.value)
          label.text = f"$result%.2g"
        } catch {
          case e: NumberFormatException => label.text = "ERROR"
        }
      case _ => ()
    }
  }

  stage = new JFXApp.PrimaryStage {
    title.value = "Simple calculator"
    width = 200
    height = 300
    resizable = false
    scene = new Scene {
      onKeyPressed = keyEventListener

      content = new GridPane {
        add(label, 0, 0, 4, 1)

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

  def calculatorButton(label: String, colspan: Int = 1) = new Button {
    text = label
    prefHeight = 50
    prefWidth = 50 * colspan
    onAction = buttonClickListener
  }
}