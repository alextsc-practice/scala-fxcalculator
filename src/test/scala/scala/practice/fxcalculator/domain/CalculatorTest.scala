package scala.practice.fxcalculator.domain

import org.scalatest.{FunSuite, Matchers}

class CalculatorTest extends FunSuite with Matchers {

  test("Basic addition") {
    assertResult(3) {
      Calculator.calculate("1+2")
    }
  }

  test("Basic substraction") {
    assertResult(9) {
      Calculator.calculate("12-3")
    }
  }

  test("Basic multiplication") {
    assertResult(12) {
      Calculator.calculate("4*3")
    }
  }

  test("Basic division") {
    assertResult(6) {
      Calculator.calculate("12/2")
    }
  }

  test("Mixed operators") {
    assertResult(4) {
      Calculator.calculate("2+4*5/10")
    }
  }
}
