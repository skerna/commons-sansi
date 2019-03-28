# SKERNA SANSI

Es un multiplatform implementación de ANSI Colors, usando extension functions de Kotlin

Objetos introducidos en tu sistema: 

- Color singlenton
- Format singlenton 

### Plataformas
- JAVA
- JAVSCRIPT
- KOTLIN

### Utilidad

![BOLT](./docs/media/demo.png)


Añade facilmente ANSI colors a tu texto y caracteres en la terminal. Esta es una manera rapida de evitar copiar y pegar o aprender las secuencias de escape.

Crea banner que muestre información util e identificable en la shell.

```kotlin 
    print("
     _____ _   __ ___________ _   _   ___  
    /  ___| | / /|  ___| ___ \ \ | | / _ \ 
    \ `--.| |/ / | |__ | |_/ /  \| |/ /_\ \
     `--. \    \ |  __||    /| . ` ||  _  |
    /\__/ / |\  \| |___| |\ \| |\  || | | |
    \____/\_| \_/\____/\_| \_\_| \_/\_| |_/

    version: ${"1.0.0".green().underline()}
    port: ${"8080".cyan()}
    ".red)
                                        
```
### Ejemplos

Kotlin lang
```Kotlin 
    println("This is a red string!".red())
    println("This is a cyan string!".cyan())
```

Java Lang

```
    System.out.println(Kolor.foreground("Hola", Color.RED))
    System.out.println(Kolor.foreground("Hola", Color.CYAN))
```

### Nivel de dependencias

Si estas usando kotlin, la lib es practicamente cero dependencias,
en el caso de JAVA, la unica dependencia es la STDLIB, y puede
ser considerada como una ligera libreria utilitaria 


JVM
```gradle
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
```

