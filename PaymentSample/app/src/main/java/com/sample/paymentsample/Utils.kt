package com.sample.paymentsample

/**
 * Created by weiweiluo on 27/11/17.
 */
public class Utils {
    companion object {
        fun isPrimeNumber(input: Int): Boolean {
            var isPrime = false

            for (index in 2..input/2) {
                if (input % index == 0) {
                    return false
                }
            }

            return isPrime
        }
    }
}