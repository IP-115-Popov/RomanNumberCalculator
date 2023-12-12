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
            var equation = tvRezult.text.toString()
            tvRezult.text = ConvertRomanToInt.intToRoman(Сalculator.calculate(equation))
        }
        bthErase.setOnClickListener {
            tvRezult.text = ""
        }
    }
}