package com.example.culculate

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.culculate.databinding.ActivityMainBinding
import com.ezylang.evalex.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SetTextI18n")

    private lateinit var binding: ActivityMainBinding
    private  val numberStringBuilder = StringBuilder()

    private val historyList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setLosteners()

    }

    private fun setLosteners() = with(binding) {
        binding.buttonZero.setOnClickListener {
            numberStringBuilder.append(0)
            resoltTextVie.text = numberStringBuilder
        }

        binding.buttonOne.setOnClickListener {
            numberStringBuilder.append(1)
            resoltTextVie.text = numberStringBuilder
        }

        binding.buttonTwo.setOnClickListener {
            numberStringBuilder.append(2)
            resoltTextVie.text = numberStringBuilder
        }

        binding.buttonThree.setOnClickListener {
            numberStringBuilder.append(3)
            resoltTextVie.text = numberStringBuilder
        }

        binding.buttonFour.setOnClickListener {
            numberStringBuilder.append(4)
            resoltTextVie.text = numberStringBuilder
        }

        binding.buttonFive.setOnClickListener {
            numberStringBuilder.append(5)
            resoltTextVie.text = numberStringBuilder
        }

        binding.buttonSix.setOnClickListener {
            numberStringBuilder.append(6)
            resoltTextVie.text = numberStringBuilder
        }

        binding.buttonSeven.setOnClickListener {
            numberStringBuilder.append(7)
            resoltTextVie.text = numberStringBuilder
        }

        binding.buttonEight.setOnClickListener {
            numberStringBuilder.append(8)
            resoltTextVie.text = numberStringBuilder
        }
        binding.buttonNine.setOnClickListener {
            numberStringBuilder.append(9)
            resoltTextVie.text = numberStringBuilder
        }

        binding.buttonPlus.setOnClickListener {
            numberStringBuilder.append("+")
            resoltTextVie.text = numberStringBuilder
        }

        binding.buttonMinus.setOnClickListener {
            numberStringBuilder.append("-")
            resoltTextVie.text = numberStringBuilder
        }

        binding.buttonMultiply.setOnClickListener {
            numberStringBuilder.append("*")
            resoltTextVie.text = numberStringBuilder
        }

        binding.buttonDivide.setOnClickListener {
            numberStringBuilder.append("/")
            resoltTextVie.text = numberStringBuilder
        }




        binding.buttonErase.setOnClickListener {
            if (numberStringBuilder.isNotEmpty()) {
                numberStringBuilder.deleteCharAt(numberStringBuilder.length - 1)
                resoltTextVie.text = numberStringBuilder.toString()
            }
        }



        binding.buttonClear.setOnClickListener {

            numberStringBuilder.clear()
            resoltTextVie.text = ""

        }


        var commaPressed = false // флаг, указывающий на наличие запятой в числе

        binding.buttonPoint.setOnClickListener {
            if (!commaPressed) { // если запятой еще нет
                numberStringBuilder.append(".")
                resoltTextVie.text = numberStringBuilder
                commaPressed = true // устанавливаем флаг, что запятая была добавлена
            }
        }


        binding.buttonEquel.setOnClickListener {
            saveToHistory()
            calculate(resoltTextVie)
        }

        binding.buttonHistory.setOnClickListener {
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            intent.putExtra("historyList", historyList.toTypedArray())
            startActivity(intent)
        }
    }

    private fun saveToHistory() {
        val stringExpression = numberStringBuilder.toString()
        historyList.add(stringExpression)
    }

    private fun calculate(textView: TextView){
        try {
            val stringExpression = numberStringBuilder.toString()
            val expression = Expression(stringExpression)
            val expresionResult = expression.evaluate().numberValue

            val decimalFormat = DecimalFormat("#.###") // задаем формат вывода чисел
            val resultString = decimalFormat.format(expresionResult) // форматируем результат
            textView.text = resultString

            numberStringBuilder.clear()
            numberStringBuilder.append(resultString.toString())

        } catch (t: Throwable) {
            Toast.makeText(this@MainActivity, "Exception: $t", Toast.LENGTH_LONG)
        }
    }
}