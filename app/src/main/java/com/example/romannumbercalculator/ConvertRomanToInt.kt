package com.example.romannumbercalculator

class ConvertRomanToInt {
    companion object {
        fun romanToInt(s: String): Int {
            val romanMap = mapOf(
                'I' to 1,
                'V' to 5,
                'X' to 10,
                'L' to 50,
                'C' to 100,
                'D' to 500,
                'M' to 1000
            )

            var result = 0
            var prevValue = 0

            for (i in s.length - 1 downTo 0) {
                val curValue = romanMap[s[i]]!!

                if (curValue < prevValue) {
                    result -= curValue
                } else {
                    result += curValue
                }

                prevValue = curValue
            }

            return result
        }
        fun intToRoman(num: Int): String {
            val values = listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
            val numerals = listOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")

            var remaining = num
            var result = ""

            for (i in values.indices) {
                while (remaining >= values[i]) {
                    remaining -= values[i]
                    result += numerals[i]
                }
            }

            return result
        }
    }
}