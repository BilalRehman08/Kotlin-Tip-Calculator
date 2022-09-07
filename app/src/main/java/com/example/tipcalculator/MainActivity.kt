package com.example.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener {
            showResult()
        }
        binding.roundUpSwitch.setOnClickListener {
           showResult()
        }
    }

    private fun showResult(){
        if (binding.costOfService.text.isNotEmpty()){
            calculateTip()
        }else{
            binding.tipResult.text = "Kindly add cost!"
        }
    }

    private fun calculateTip(){
        val stringTextField = binding.costOfService.text.toString()
        val cost = stringTextField.toDouble()
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.50
            else -> 0.50
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp){
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}