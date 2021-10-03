package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var tvNumbers: TextView
    var number = ""
    var numbers = arrayListOf<Float>()
    var operations = arrayListOf<String>()

    lateinit var btnNumber0: Button
    lateinit var btnNumber1: Button
    lateinit var btnNumber2: Button
    lateinit var btnNumber3: Button
    lateinit var btnNumber4: Button
    lateinit var btnNumber5: Button
    lateinit var btnNumber6: Button
    lateinit var btnNumber7: Button
    lateinit var btnNumber8: Button
    lateinit var btnNumber9: Button

    lateinit var btnAddition: Button
    lateinit var btnSubtraction: Button
    lateinit var btnMultiplication: Button
    lateinit var btnDivision: Button
    lateinit var btnEqualization: Button

    lateinit var btnDelete: Button
    lateinit var btnCancel: Button
    lateinit var btnNegativeNumbers: Button
    lateinit var btnDecimal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tvNumbers = findViewById(R.id.tvNumbers)

        btnNumber0 = findViewById(R.id.btnNumber0)
        btnNumber1 = findViewById(R.id.btnNumber1)
        btnNumber2 = findViewById(R.id.btnNumber2)
        btnNumber3 = findViewById(R.id.btnNumber3)
        btnNumber4 = findViewById(R.id.btnNumber4)
        btnNumber5 = findViewById(R.id.btnNumber5)
        btnNumber6 = findViewById(R.id.btnNumber6)
        btnNumber7 = findViewById(R.id.btnNumber7)
        btnNumber8 = findViewById(R.id.btnNumber8)
        btnNumber9 = findViewById(R.id.btnNumber9)

        btnAddition = findViewById(R.id.btnAddition)
        btnSubtraction = findViewById(R.id.btnSubtraction)
        btnMultiplication = findViewById(R.id.btnMultiplication)
        btnDivision = findViewById(R.id.btnDivision)
        btnEqualization = findViewById(R.id.btnEqualization)

        btnDelete = findViewById(R.id.btnDelete)
        btnCancel = findViewById(R.id.btnCancel)
        btnNegativeNumbers = findViewById(R.id.btnNegativeNumbers)
        btnDecimal = findViewById(R.id.btnDecimal)

        btnNumber0.setOnClickListener { addNumberToTvNumbers("0") }
        btnNumber1.setOnClickListener { addNumberToTvNumbers("1") }
        btnNumber2.setOnClickListener { addNumberToTvNumbers("2") }
        btnNumber3.setOnClickListener { addNumberToTvNumbers("3") }
        btnNumber4.setOnClickListener { addNumberToTvNumbers("4") }
        btnNumber5.setOnClickListener { addNumberToTvNumbers("5") }
        btnNumber6.setOnClickListener { addNumberToTvNumbers("6") }
        btnNumber7.setOnClickListener { addNumberToTvNumbers("7") }
        btnNumber8.setOnClickListener { addNumberToTvNumbers("8") }
        btnNumber9.setOnClickListener { addNumberToTvNumbers("9") }

        btnAddition.setOnClickListener { addOperationToTvNumbers("+") }
        btnSubtraction.setOnClickListener { addOperationToTvNumbers("-") }
        btnMultiplication.setOnClickListener { addOperationToTvNumbers("*") }
        btnDivision.setOnClickListener { addOperationToTvNumbers("/") }
        btnEqualization.setOnClickListener { addOperationToTvNumbers("=") }

        btnNegativeNumbers.setOnClickListener { addNumberToTvNumbers("-") }
        btnDecimal.setOnClickListener { addNumberToTvNumbers(".") }

        btnDelete.setOnClickListener { deleteLastEnteredNumber() }
        btnCancel.setOnClickListener { deleteAllTvNumbers() }


    }

    fun addNumberToTvNumbers(number: String){
        tvNumbers.text = tvNumbers.text.toString() + number
        this.number += number
    }

    fun addOperationToTvNumbers(operation: String){
        var number = 0f
        if(this.number.isNotEmpty()) number= this.number.toFloat()
        numbers.add(number)
        Log.d("MainActivity", numbers.toString())
        this.number = ""
        operations.add(operation)
        Log.d("MainActivity", operations.toString())
        if(operation == "=") calculate()
        else tvNumbers.text = tvNumbers.text.toString() + operation
    }

    fun deleteLastEnteredNumber(){
        if(tvNumbers.text.isNotEmpty()) tvNumbers.text = tvNumbers.text.substring(0, tvNumbers.text.length-1)
    }

    fun deleteAllTvNumbers(){
        numbers.clear()
        operations.clear()
        this.number = ""
        tvNumbers.text = ""
    }

    fun calculate(){
        var iNumber = 1
        var iOperation = 0
        var total = numbers[0]
        var operation = operations[iOperation]
        while (operation != "="){
            when(operation){
                "+" -> total += numbers[iNumber]
                "-" -> total -= numbers[iNumber]
                "*" -> total *= numbers[iNumber]
                "/" -> {
                    if(numbers[iNumber] == 0f) Toast.makeText(this, "Can't divie by ZERO", Toast.LENGTH_LONG).show()
                    else total /= numbers[iNumber]
                }
            }
            iOperation++
            iNumber++
            operation = operations[iOperation]
        }

        numbers.clear()
        operations.clear()

        deleteAllTvNumbers()
        addNumberToTvNumbers(total.toString())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putFloatArray("numbers", numbers.toFloatArray())
        outState.putStringArrayList("operations", operations)
        outState.putString("number", number)
        outState.putString("tvNumbers", tvNumbers.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        operations = savedInstanceState.getStringArrayList("operations")!!
        number = savedInstanceState.getString("number")!!
        tvNumbers.text = savedInstanceState.getString("tvNumbers")
        for(num in savedInstanceState.getFloatArray("numbers")!!){
            numbers.add(num)
        }
    }
}