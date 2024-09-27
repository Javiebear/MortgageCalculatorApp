package com.example.mortgagecalculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mortgagecalculator.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var principleAmount = 0
    var interestRate = 0.0
    var amorizationPeriod = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // setting up the button click
        binding.calculateButton.setOnClickListener {
            handleCalculation(it)
        }
    }

    // this function is called when the user returns to this activity
    // from the intent in ResumeActivity
    override fun onResume() {
        super.onResume()

        // reseting the user inputs
        binding.editTextNumberPrincipleAmount.text.clear()
        binding.editTextNumberDecimalInterest.text.clear()
        binding.editTextNumberAmorization.text.clear()
    }

    // this function will calculate the value
    fun handleCalculation(view: View?){

        if(binding.editTextNumberPrincipleAmount.text.toString() == "" || binding.editTextNumberDecimalInterest.text.toString() == ""
            || binding.editTextNumberAmorization.text.toString() == ""){
            Toast.makeText(this, "Please fill out all the inputs", Toast.LENGTH_SHORT).show()

        }
        else
        {
            // calculating the monthly mortgage
            val principleAmount = binding.editTextNumberPrincipleAmount.text.toString().toInt()
            val interestRate = binding.editTextNumberDecimalInterest.text.toString().toDouble()
            val amorizationPeriod = binding.editTextNumberAmorization.text.toString().toInt()

            val monthlyInterest = (interestRate / 100)/12
            val totalPayments = amorizationPeriod * 12

            val monthlyMortgage = (principleAmount * monthlyInterest * ((1 + monthlyInterest).pow(totalPayments))) /
                    (((1 + monthlyInterest).pow(totalPayments)) - 1)

            // creating an intent to open the next page that shows the results
            val intentResult = Intent(this, ResultActivity::class.java)

            //sending the monthly mortgage to the next page
            intentResult.putExtra("MONTHLY_MORTGAGE", monthlyMortgage)

            startActivity(intentResult)

        }






    }
}