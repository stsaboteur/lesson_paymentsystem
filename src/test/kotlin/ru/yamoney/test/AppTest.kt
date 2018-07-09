package ru.yamoney.test

import org.junit.Test
import kotlin.test.assertEquals

class AppTest {

    @Test
    fun test() {
        App().run(arrayOf("deposit", "def", "43"))
    }
}
