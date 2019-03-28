package io.skerna.commons.sansi

import io.skerna.commons.sansi.Format

fun String.underline() = Format.underline(this)

fun String.italyc() = Format.italyc(this)

fun String.enclosed() =  "($this)"