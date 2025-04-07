package com.example.finalprj

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        data?.extras?.let{
            if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
                val balance = it.getInt("balance")

                findViewById<TextView>(R.id.text_balance).text = balance.toString()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.btn_deposit).setOnClickListener{
            val intent = Intent(this, DepositActivity::class.java )
            val balance = getBalance()
            intent.putExtra("balance",balance)
            startActivityForResult(intent, 1)
        }

        findViewById<Button>(R.id.btn_coinfilp).setOnClickListener{
            val intent = Intent(this, CoinflipActivity::class.java )
            val balance = getBalance()
            intent.putExtra("balance",balance)
            startActivityForResult(intent, 1)
        }



        findViewById<Button>(R.id.btn_dice).setOnClickListener{
            val intent = Intent(this, DiceActivity::class.java )
            val balance = getBalance()
            intent.putExtra("balance",balance)
            startActivityForResult(intent, 1)
        }


    }
    fun getBalance(): Int {
        val text_balance = findViewById<TextView>(R.id.text_balance)
        val balance = text_balance.text.toString().toInt()
        return balance
    }
}