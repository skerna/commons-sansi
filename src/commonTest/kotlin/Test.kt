package io.skerna.ansicolor

import kotlin.test.Test

class Test
{
    @Test
    fun testUnderline(){
        print("Hola".underline().redBackground().blue())
        print("Hola".italyc())
    }

    @Test
    fun testColors(){
        println("Red".red())
        println("Cyan".cyan())
        println("Green".green())
    }

}