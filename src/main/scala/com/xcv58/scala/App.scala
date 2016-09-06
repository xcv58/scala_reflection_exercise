package com.xcv58.scala

case class Person(id: Int, name: String, age: Int, salary: Double)

object Main extends App {
    val me = new Person(1, "Yihong", 28, 13000.0)
    DebugMacros.getPerson(me, 1)
    DebugMacros.getPerson(me)
}
