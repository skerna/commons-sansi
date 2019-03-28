package io.skerna.commons.sansi

import kotlin.jvm.JvmStatic

object Format {
    val ANSI_RESET = "\u001B[0m"
    val ANSI_UNDERLINE = "\u001B[4m"
    val ANSI_ITALIC= "\u001B[3m"

    @JvmStatic
    fun underline(string: String) = format(string, ANSI_UNDERLINE)

    @JvmStatic
    fun italyc(string: String) = format(string, ANSI_ITALIC)

    private fun format(string: String, ansiString: String) = "$ansiString$string$ANSI_RESET"

}