package ru.yamoney.test

import com.google.gson.Gson
import java.io.File
import java.math.BigDecimal

class BillingImpl : Billing {
    private val file = File("operations")
    private val gson = Gson()

    override fun getShopIdOperations(shopId: String) {
        file.readLines()
                .map { it.toOperation() }
                .filter { it is Payment && it.shopId == shopId }
                .forEach {
                    println(it)
                }
    }

    override fun getUserBalance(user: String): BigDecimal {
        var balance = BigDecimal.ZERO
        file.readLines()
                .map { it.toOperation() }
                .filter { it.user == user }
                .forEach {
                    balance = it.calculate(balance)
                }
        println("$user balance: ${balance.toPlainString()}")
        return balance
    }

    override fun addOperation(operation: Operation) {
        file.appendText(operation.toFileRecord() + "\n")
    }

    private fun Operation.toFileRecord(): String = "${this::class.java.name}||${gson.toJson(this)}"

    private fun String.toOperation(): Operation {
        val operation = this.substringBefore("||")
        val json = this.substringAfter("||")
        return gson.fromJson(json, Class.forName(operation)) as Operation
    }
}

