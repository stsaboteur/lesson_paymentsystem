package ru.yamoney.test

import java.math.BigDecimal

interface Billing {
    fun getShopIdOperations(shopId: String)
    fun getUserBalance(user: String): BigDecimal
    fun addOperation(operation: Operation)
}