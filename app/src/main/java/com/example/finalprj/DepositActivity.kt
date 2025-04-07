package com.example.finalprj

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DepositActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_deposit)

        val text_balance = findViewById<TextView>(R.id.balance_amount)
        val btn_addmoney = findViewById<TextView>(R.id.btn_addmoney)
        val btn_back = findViewById<TextView>(R.id.btn_back)
        var balance = intent.getIntExtra("balance", 0)
        text_balance.text = balance.toString()

        btn_addmoney.setOnClickListener(){
            balance = balance + 100
            text_balance.text = (balance).toString()
        }

        btn_back.setOnClickListener(){
            intent.putExtra("balance", balance)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}