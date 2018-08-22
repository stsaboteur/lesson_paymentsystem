package ru.yamoney.test

import java.math.BigDecimal

const val USAGE_MESSAGE = """
java -jar app.jar [COMMAND] [ARGUMENTS]
            Commands:
            payment %USER% %SUM% %SHOP_ID%
            deposit %USER% %SUM%
            balance %USER%
            shop_info %SHOP_ID%
"""

private val billing = BillingImpl()

fun main(args: Array<String>) {
    try {
        when (args.command()) {
            "PAYMENT" -> billing.addOperation(Payment(args[1], BigDecimal(args[2]), args[3]))
            "DEPOSIT" -> billing.addOperation(Deposit(args[1], BigDecimal(args[2])))
            "BALANCE" -> billing.getUserBalance(args[1])
            "SHOP_INFO" -> billing.getShopIdOperations(args[1])
            else -> throw IllegalArgumentException("Unknown command")
        }
    } catch (e: Throwable) {
        e.printStackTrace()
        println(USAGE_MESSAGE)
    }
}

private fun Array<String>.command(): String = this[0].toUpperCase()



