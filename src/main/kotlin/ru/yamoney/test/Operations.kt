package ru.yamoney.test

import java.math.BigDecimal

interface Operation {
    val user: String
    fun calculate(oldBalance: BigDecimal): BigDecimal
}

data class Deposit(
        override val user: String,
        val sum: BigDecimal
) : Operation {
    override fun calculate(oldBalance: BigDecimal): BigDecimal = oldBalance.add(sum)
}

data class Payment(
        override val user: String,
        val sum: BigDecimal,
        val shopId: String
) : Operation {
    override fun calculate(oldBalance: BigDecimal): BigDecimal = oldBalance.subtract(sum)
}