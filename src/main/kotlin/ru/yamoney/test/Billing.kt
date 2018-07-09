package ru.yamoney.test

import java.io.File
import java.math.BigDecimal

class Billing {
    private val file = File("operations")

    fun getShopIdOperations(shopId: String) {
        file.readLines()
                .map(this::parseOperation)
                .filter { it is Payment }
                .map { it as Payment }
                .filter { it.shopId == shopId }
                .forEach {
                    println(it)
                }
    }

    fun getUserBalance(user: String): BigDecimal {
        var balance = BigDecimal.ZERO
        file.readLines()
                .map(this::parseOperation)
                .filter { it.user == user }
                .forEach {
                    balance = it.apply(balance)
                }
        println("$user balance: ${balance.toPlainString()}")
        return balance
    }

    fun addOperation(operation: Operation) {
        file.appendText(operation.toStringRecord() + "\n")
    }

    private fun parseOperation(record: String): Operation {
        val parameters = record.split(" ")
        return when (parameters[0]) {
            "deposit" -> Deposit(parameters[1], BigDecimal(parameters[2]))
            "payment" -> Payment(parameters[1], BigDecimal(parameters[2]), parameters[3])
            else -> throw IllegalArgumentException("File corrupted")
        }
    }
}

interface Operation {
    val user: String
    fun toStringRecord(): String
    fun apply(oldBalance: BigDecimal): BigDecimal
}


data class Deposit(
        override val user: String,
        val sum: BigDecimal
) : Operation {
    override fun apply(oldBalance: BigDecimal): BigDecimal = oldBalance.add(sum)
    override fun toStringRecord(): String = "deposit $user ${sum.setScale(2).toPlainString()}"
}

data class Payment(
        override val user: String,
        val sum: BigDecimal,
        val shopId: String
) : Operation {
    override fun apply(oldBalance: BigDecimal): BigDecimal = oldBalance.subtract(sum)
    override fun toStringRecord(): String = "payment $user ${sum.setScale(2).toPlainString()} $shopId"
}

