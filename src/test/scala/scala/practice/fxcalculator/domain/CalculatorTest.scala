package scala.practice.fxcalculator.domain

import org.scalatest.{FunSuite, Matchers}

class CalculatorTest extends FunSuite with Matchers {

  test("Basic addition") {
    Calculator.calculate("1+2") should be(3)
  }

  test("Basic substraction") {
    Calculator.calculate("12-3") should be(9)
  }

  test("Basic multiplication") {
    Calculator.calculate("4*3") should be(12)
  }

  test("Basic division") {
    Calculator.calculate("12/2") should be(6)
  }

  test("Mixed operators") {
    Calculator.calculate("2+4*5/10") should be(4)
  }
}
