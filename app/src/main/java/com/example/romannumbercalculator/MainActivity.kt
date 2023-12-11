package com.example.romannumbercalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.romannumbercalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw IllegalStateException("Binding for ActivityMain must not be null")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvRezult: TextView = binding.tvRezult
        val btnEqually:Button = binding.btnEqually
        val bthErase:Button = binding.bthErase

        val bthOne: Button = binding.bthOne
        val bthOneHundred:Button = binding.bthOneHundred
        val bthPlus:Button = binding.bthPlus
        val bthFive:Button = binding.bthFive
        val bthFiveHundred:Button = binding.bthFiveHundred
        val bthMinus:Button = binding.bthMinus
        val bthTen:Button = binding.bthTen
        val bthThousand:Button = binding.bthThousand
        val bthMultiply:Button = binding.bthMultiply
        val bthFifty:Button = binding.bthFifty
        val bthDivide:Button = binding.bthDivide

        val numericButton = setOf(
            binding.bthOne, binding.bthOneHundred, binding.bthPlus, binding.bthFive, binding.bthFiveHundred,
            binding.bthMinus,binding.bthTen,binding.bthThousand,binding.bthMultiply,binding.bthFifty, binding.bthDivide
        )
        for (button in numericButton)
            button.setOnClickListener {
                tvRezult.text = tvRezult.text.toString() + button.text.toString()
            }
        btnEqually.setOnClickListener {
            //получение чисел и операторов
            var equation = tvRezult.text.toString()
            val numberList = equation.split(Regex("[+\\-*/]"))

            val pattern = Regex("[ICVDXML]*([+\\-*/])[ICVDXML]*")
            val matches = pattern.findAll(equation)

            // Получение всех найденных operator в список
            val operatorList = matches.map { it.groupValues[1] }.toList()
            //вычисление результата
            val numberListInt = mutableListOf<Int>()
            var rez = 0
            numberList.forEach {
                numberListInt.add(romanToInt(it))
            }
            operatorList.forEach {

            }


            tvRezult.text = tvRezult.text.toString() + " = "
        }
        bthErase.setOnClickListener {
            tvRezult.text = ""
        }
    }
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
}