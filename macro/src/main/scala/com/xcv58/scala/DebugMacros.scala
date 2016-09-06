package com.xcv58.scala

import language.experimental.macros
import reflect.macros.whitebox.Context

import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe._

object DebugMacros {
    def getPerson[T: TypeTag](i: T): Any = {

        val m = ru.runtimeMirror(getClass.getClassLoader)
        val classPerson = ru.typeOf[T].typeSymbol.asClass
        val cm = m.reflectClass(classPerson)
        val ctor = ru.typeOf[T].declaration(ru.nme.CONSTRUCTOR).asMethod
        val ctorm = cm.reflectConstructor(ctor)
        ctor.paramss.head.map(println)
        val p = ctorm(1, "Mike", 1, 1)
        println(p)
        p
    }

    def getPerson[T](i: T, m: Int): Unit = macro get_impl

    def get_impl(c: Context)(i: c.Expr[Any], m: c.Expr[Int]): c.Expr[Unit] = {
        import c.universe._

        reify {
            println(i.splice)
            println(m.splice)
        }
    }
}
