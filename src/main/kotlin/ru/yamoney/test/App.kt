package ru.yamoney.test

import java.math.BigDecimal


class App {
    private val billing = Billing()

    fun run(args: Array<String>) {
        when (args.command()) {
            "PAYMENT" -> billing.addOperation(Payment(args[1], BigDecimal(args[2]), args[3]))
            "DEPOSIT" -> billing.addOperation(Deposit(args[1], BigDecimal(args[2])))
            "BALANCE" -> billing.getUserBalance(args[1])
            "SHOP_INFO" -> billing.getShopIdOperations(args[1])
            else -> throw IllegalArgumentException("Unknown command")
        }
    }
}


private fun Array<String>.command(): String = this[0].toUpperCase()



