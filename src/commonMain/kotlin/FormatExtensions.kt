package io.skerna.ansicolor

fun String.underline() = Format.underline(this)

fun String.italyc() = Format.italyc(this)

fun String.enclosed() =  "($this)"