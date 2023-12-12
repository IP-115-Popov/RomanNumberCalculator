package com.example.romannumbercalculator

class Сalculator {
    companion object {
        fun calculate(equation: String): Int {
            //получение последовательности чисел
            val numberSequence = equation.split(Regex("[+\\-*/]")).toMutableList()

            // Получение последовательности всех operator
            val pattern = Regex("[ICVDXML]*([+\\-*/])[ICVDXML]*")
            val matches = pattern.findAll(equation)
            val operatorSequence = matches.map { it.groupValues[1] }.toMutableList()

            //вычисление результата
            val operatorPriority = mapOf(
                "+" to 1,
                "-" to 1,
                "*" to 2,
                "/" to 2,
            )
            var rez = 0

            val stackNumbers = mutableListOf<Int>()
            val stackOperations = mutableListOf<String>()
            //считываем первое выражение
            if (numberSequence.size >= 2 && operatorSequence.size >= 1) {

                stackNumbers.add(ConvertRomanToInt.romanToInt(numberSequence.removeLast()))
                stackOperations.add(operatorSequence.removeLast())

                while (!numberSequence.isEmpty() && !operatorSequence.isEmpty()) {
                    stackNumbers.add(ConvertRomanToInt.romanToInt(numberSequence.removeLast()))
                    stackOperations.add(operatorSequence.removeLast()) //read next op

                    var curerntOp = stackOperations[stackOperations.count() -2]
                    var nextOp = stackOperations[stackOperations.count()-1]

                    var priorityCurerntOp: Int = operatorPriority[curerntOp] ?: -1
                    var priorityNextOp: Int = operatorPriority[nextOp] ?: -1
                    if (priorityCurerntOp >= priorityNextOp) {
                        var arg1 = stackNumbers.removeLast()
                        var arg2 = stackNumbers.removeLast()
                        rez = when (curerntOp) {
                            "+" -> arg1 + arg2
                            "-" -> arg1 - arg2
                            "*" -> arg1 * arg2
                            "/" -> arg1 / arg2
                            else -> 0
                        }
                        stackNumbers.add(rez)
                    }
                }
                if (numberSequence.size >= 1)
                {
                    stackNumbers.add(ConvertRomanToInt.romanToInt(numberSequence.removeLast()))
                    var curerntOp = stackOperations[stackOperations.count()-1]
                    var arg1 = stackNumbers.removeLast()
                    var arg2 = stackNumbers.removeLast()
                    rez = when (curerntOp) {
                        "+" -> arg1 + arg2
                        "-" -> arg1 - arg2
                        "*" -> arg1 * arg2
                        "/" -> arg1 / arg2
                        else -> 0
                    }
                }
            }
            return rez;
        }
    }
}