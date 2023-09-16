package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btStart: Button = findViewById(R.id.btStart)
        var etText: AppCompatEditText = findViewById(R.id.etText)

        btStart.setOnClickListener{
            val enteredText = etText.text.toString()

            if(enteredText.isEmpty()) {
                Toast.makeText(
                    this,
                    "Make sure to enter the name ${enteredText}",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                val intent = Intent(this, QuestionsActiity::class.java)
                startActivity(intent)
            }
        }
    }
    }