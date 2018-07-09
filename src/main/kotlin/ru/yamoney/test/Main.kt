package ru.yamoney.test

const val USAGE_MESSAGE = """
java -jar app.jar [COMMAND] [ARGUMENTS]
            Commands:
            payment %USER% %SUM% %SHOP_ID%
            deposit %USER% %SUM%
            balance %USER%
            shop_info %SHOP_ID%
"""

fun main(args: Array<String>) {
    try {
        App().run(args)
    } catch (e: Throwable) {
        e.printStackTrace()
        println(USAGE_MESSAGE)
    }
}

