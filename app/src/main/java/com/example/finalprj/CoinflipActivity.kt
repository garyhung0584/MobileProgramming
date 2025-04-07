package com.example.finalprj

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CoinflipActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coinflip)

        val text_balance = findViewById<TextView>(R.id.balance)

        val input_bet = findViewById<EditText>(R.id.input_betcoin)

        val btn_head = findViewById<ToggleButton>(R.id.btn_head)
        val btn_tail = findViewById<ToggleButton>(R.id.btn_tail)
        val btn_flip = findViewById<Button>(R.id.btn_flip)


        var balance = intent.getIntExtra("balance", 0)
        text_balance.text = balance.toString()


        btn_head.setOnClickListener() {
            btn_head.textOn = "${input_bet.text} \n on head"
            if (btn_head.isChecked) {
                btn_tail.isChecked = false
                btn_head.isChecked = false
                btn_head.isChecked = true
            }
        }

        btn_tail.setOnClickListener() {
            btn_tail.textOn = "${input_bet.text} \n on tail"
            if (btn_tail.isChecked) {
                btn_head.isChecked = false
                btn_tail.isChecked = false
                btn_tail.isChecked = true
            }
        }
        btn_flip.setOnClickListener() {
            val bet = input_bet.text.toString().toInt()
            if (bet > balance) {
                Toast.makeText(this, "Not enough balance!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!btn_head.isChecked && !btn_tail.isChecked) {
                Toast.makeText(this, "Please select head or tail!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val result = (0..1).random()
            balance = balance - bet
            if (result == 0 && btn_head.isChecked || result == 1 && btn_tail.isChecked) {
                balance = (balance.toFloat() + bet.toFloat() * 1.99).toInt()
                text_balance.text = balance.toString()
                Toast.makeText(this, "You flipped a ${if (result == 0) "head" else "tail"} and earned ${bet * 1.99} dollars !", Toast.LENGTH_SHORT).show()
            } else {
                text_balance.text = balance.toString()
                Toast.makeText(this, "You flipped a ${if (result == 0) "head" else "tail"} and lost ${bet} dollars !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        val balance = findViewById<TextView>(R.id.balance).text.toString().toInt()
        intent.putExtra("balance", balance)
        setResult(RESULT_OK, intent)
        finish()
        super.onBackPressed()
    }
}