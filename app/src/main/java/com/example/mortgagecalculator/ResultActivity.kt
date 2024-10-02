package com.example.mortgagecalculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mortgagecalculator.databinding.ActivityResultBinding


class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intent = intent
        val monthlyMortgage = intent.getDoubleExtra("MONTHLY_MORTGAGE", 0.0)

        // displaying the data
        binding.textViewMonthlyPayment.text = "$" + String.format("%.2f",monthlyMortgage)

        binding.buttonBack.setOnClickListener {
            handleBack(it)
        }

    }

    // this will send the use back to the main page
    fun handleBack(view: View?){
        val intentBack = Intent(this, MainActivity::class.java)
        startActivity(intentBack)
    }
}