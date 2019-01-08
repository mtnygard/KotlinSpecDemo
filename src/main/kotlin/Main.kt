package com.cognitect.demo

import clojure.java.api.Clojure
import clojure.lang.IFn
import clojure.lang.Keyword
import clojure.lang.Symbol
import com.cognitect.demo.ClojureHelper.kw
import com.cognitect.demo.ClojureHelper.load
import com.cognitect.demo.ClojureHelper.map
import com.cognitect.demo.ClojureHelper.v

object Main {
    init{
        load("demo.messages")
    }

    private val USERNAME = kw("username")
    private val DAYS_AGO = kw("days-ago")
    private val ACCEPTED_TOS = kw("accepted-tos?")
    private val ENTITY_SPEC = kw("demo.messages", "entity")

    @JvmStatic
    fun main(args: Array<String>) {
        validate(ENTITY_SPEC, 10)
        validate(ENTITY_SPEC, map(mapOf(Pair(USERNAME, "bob"))))
        validate(ENTITY_SPEC, map(mapOf(Pair(USERNAME, "bob"), Pair(DAYS_AGO, 10))))
        validate(ENTITY_SPEC, map(mapOf(Pair(USERNAME, "bob"), Pair(DAYS_AGO, 10), Pair(ACCEPTED_TOS, "bla bla bla"))))
        validate(ENTITY_SPEC, map(mapOf(Pair(USERNAME, "bob"), Pair(DAYS_AGO, 10), Pair(ACCEPTED_TOS, false))))
    }

    private fun validate(spec: Keyword, value: Any?) {
        println("Is $value a $spec according to Clojure? ${Spec.valid(spec, value)}")
    }
}

object ClojureHelper {
    private val load: IFn = v("clojure.core", "require")
    private val symbol: IFn = v("clojure.core", "symbol")
    private val keyword: IFn = v("clojure.core","keyword")

    init {
        load("demo.kotlin-bridge")
    }

    private val clojureMap: IFn = v("demo.kotlin-bridge", "clojure-map")

    fun v(ns: String, s: String): IFn = Clojure.`var`(ns, s)

    private fun sym(s: String): Symbol {
        return symbol.invoke(s) as Symbol
    }

    fun load(namespace: String) {
        load.invoke(sym(namespace))
    }

    fun map(m: Map<Any, Any>): Any? {
        return clojureMap.invoke(m)
    }

    fun kw(s: String): Keyword {
        return keyword.invoke(s) as Keyword
    }

    fun kw(ns: String, s: String): Keyword {
        return keyword.invoke(ns, s) as Keyword
    }
}

object Spec {
    private val valid: IFn = v("clojure.spec.alpha", "valid?")

    fun valid(spec: Keyword, value: Any?): Any {
        return valid.invoke(spec, value)
    }
}