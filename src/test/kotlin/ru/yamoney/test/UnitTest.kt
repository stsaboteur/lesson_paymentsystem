package ru.yamoney.test

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.comparesEqualTo
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class UnitTest {

    @Test
    fun checkDeposit() {
        val sum = BigDecimal(666.13)
        val depositOperation = Deposit("Peppa", sum)
        val initialBalance = BigDecimal(222)
        val newBalance = depositOperation.calculate(initialBalance)

        assertThat("dep is dep",
                newBalance, comparesEqualTo(initialBalance + sum))
    }
}