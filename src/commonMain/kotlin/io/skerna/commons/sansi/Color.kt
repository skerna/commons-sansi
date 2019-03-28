package io.skerna.commons.sansi

import kotlin.jvm.JvmStatic

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
    @JvmStatic
    fun foreground(string: String, ansiColorCodes: AnsiColorCodes) = color(string, ansiColorCodes.foreground)

    /**
     * Create a string that will be printed with the specified color as background
     * @param string The string to color
     * @param ansiColorCodes The color to use
     * @return The colored string
     */
    @JvmStatic
    fun background(string: String, ansiColorCodes: AnsiColorCodes) = color(string, ansiColorCodes.background)



    /**
     * Render ANsi template style
     * example
     * [ANSI.RED] Hola soy red
     * [ANSI.BLUE] Yo soy blue
     * Normal text [ANSI.BLUE] 1.0.0
     * [ANSI.RED]=(Test)  [ANSI.CYAN]=(Test)
     */
    fun renderAnsi(target:String): String {
        val regexScopedColors = "\\[Ansi.(\\w*)\\]=\\(([^()]*)\\)".toRegex()
        val regexUnScopedColors = "(\\[Ansi.(\\w*)\\])\\s*([^=]*)".toRegex()
        val stringBuilder = StringBuilder()

        for (line in target.lines()) {
            val stage1 = renderInnerVars(line,regexScopedColors);
            val stage2 = renderGlobals(stage1,regexUnScopedColors);
            stringBuilder.append(stage2+"\n")
        }
        return stringBuilder.toString()
    }

    fun renderInnerVars(line: String,regex:Regex): String {
        var noColorized = ""
        var compiledString = line;
        val result = regex.findAll(line)
        for (matchResult in result.iterator()) {
            val group = (matchResult.groups[0]?.value ?: "")
            val colorName = (matchResult.groups[1]?.value ?: "")
            val content = (matchResult.groups[2]?.value ?: "")
            val colorForeground = AnsiColorCodes.fromString(colorName)
            var colorizedContent = (Color.foreground(content, colorForeground))
            compiledString = compiledString.replace(group, colorizedContent)
        }
        return compiledString;
    }

    fun renderGlobals(line: String,regex: Regex): String {
        var noColorized = ""
        var compiledString = line;
        val result = regex.findAll(line)
        for (matchResult in result.iterator()) {
            var group = (matchResult.groups[0]?.value ?: "")
            val tag = (matchResult.groups[1]?.value ?: "")
            val colorName = (matchResult.groups[2]?.value ?: "")
            //val content = (matchResult.groups[3]?.value?:"")
            val content = group.replace(tag, "")
            val colorForeground = AnsiColorCodes.fromString(colorName)
            var colorizedContent = (Color.foreground(content, colorForeground))
            compiledString = compiledString.replace(group, colorizedContent)
        }
        return compiledString;
    }

    private fun color(string: String, ansiString: String) = "$ansiString$string$RESET"


}