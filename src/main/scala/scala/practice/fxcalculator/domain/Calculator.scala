package scala.practice.fxcalculator.domain

object Calculator {

  def calculate(input: String): Double = {
    var parts: Array[String] = Array[String]()

    parts = input.split("\\+", 2)
    if (parts.length == 2) return calculate(parts(0)) + calculate(parts(1))

    parts = input.split("\\-", 2)
    if (parts.length == 2) return calculate(parts(0)) - calculate(parts(1))

    parts = input.split("\\*", 2)
    if (parts.length == 2) return calculate(parts(0)) * calculate(parts(1))

    parts = input.split("\\/", 2)
    if (parts.length == 2) return calculate(parts(0)) / calculate(parts(1))

    input.toDouble
  }
}
