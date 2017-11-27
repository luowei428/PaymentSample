package com.sample.paymentsample

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by weiweiluo on 27/11/17.
 */
class UtilsTest {
    @Test
    fun testIsPrime() {
        assertEquals(false, Utils.isPrimeNumber(-1))
    }
}