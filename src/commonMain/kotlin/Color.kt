package io.skerna.ansicolor

/**
 * Object to add color information to strings
 */
object Color {
    internal const val ESCAPE = '\u001B'
    internal const val RESET = "$ESCAPE[0m"

    /**
     * Create a string that will be printed with the specified color as foreground
     * @param string The string to color
     * @param ansiColorCodes The color to use
     * @return The colored string
     */
    fun foreground(string: String, ansiColorCodes: AnsiColorCodes) = color(string, ansiColorCodes.foreground)

    /**
     * Create a string that will be printed with the specified color as background
     * @param string The string to color
     * @param ansiColorCodes The color to use
     * @return The colored string
     */
    fun background(string: String, ansiColorCodes: AnsiColorCodes) = color(string, ansiColorCodes.background)

    private fun color(string: String, ansiString: String) = "$ansiString$string$RESET"


}