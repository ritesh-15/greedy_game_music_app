package com.example.musicapp.utils

import java.math.BigInteger

class FormatNumber {
    fun format(number: Long):String {
        var numberString = ""
        numberString = if (Math.abs(number / 1000000) > 1) {
            (number / 1000000).toString() + "m"
        } else if (Math.abs(number / 1000) > 1) {
            (number / 1000).toString() + "k"
        } else {
            number.toString()
        }
        return numberString
    }
}