package io.skerna.commons.sansi

import kotlin.test.Test

class Test {
    val targetTest = """
                   ________   ______  _____ ______    ________
                  / / / / /   | ___ \/ __  \| ___ \   \ \ \ \ \
                 / / / / /    | |_/ /`' / /'| |_/ /    \ \ \ \ \
                < < < < <     |    /   / /  | ___ \     > > > > >
                 \ \ \ \ \    | |\ \ ./ /___| |_/ /    / / / / /
                  \_\_\_\_\   \_| \_|\_____/\____/    /_/_/_/_/


            [Ansi.GREEN]  ______   __  __   ______   ______  ______   __    __   ______
            [Ansi.GREEN] /\  ___\ /\ \_\ \ /\  ___\ /\__  _\/\  ___\ /\ "-./  \ /\  ___\
            [Ansi.GREEN] \ \___  \\ \____ \\ \___  \\/_/\ \/\ \  __\ \ \ \-./\ \\ \___  \
            [Ansi.GREEN]  \/\_____\\/\_____\\/\_____\  \ \_\ \ \_____\\ \_\ \ \_\\/\_____\
            [Ansi.GREEN]   \/_____/ \/_____/ \/_____/   \/_/  \/_____/ \/_/  \/_/ \/_____/

            Version : [Ansi.BLUE] 1.0.0
            Estopa:  [Ansi.RED] 1.0.0
            Vertx :  [Ansi.GREEN]=(Vertx awesome)  AND Skerna : [Ansi.BLUE]=(Skerna awesome)

    """.trimIndent()

    @Test
    fun testUnderline() {
        print("Hola".underline().redBackground().blue())
        print("Hola".italyc())
    }

    @Test
    fun testColors() {
        println("Red".red())
        println("Cyan".cyan())
        println("Green".green())
    }

    @Test
    fun testBanner() {
        val result= renderBammer(targetTest)
        println(result)
    }

    fun renderBammer(target:String): String {
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
}