package com.example.finalprj

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dice)

        val seekbar_dice = findViewById<SeekBar>(R.id.seekBar_dice)
        val text_dicepoint = findViewById<TextView>(R.id.text_dicepoint)
        val text_odd = findViewById<TextView>(R.id.text_odd)
        val text_winrate = findViewById<TextView>(R.id.text_winrate)
        val text_payout = findViewById<TextView>(R.id.text_payout)
        val input_bet = findViewById<EditText>(R.id.input_betdice)
        val text_balance = findViewById<TextView>(R.id.balance)
        val btn_roll = findViewById<Button>(R.id.btn_roll)


        var balance = intent.getIntExtra("balance", 0)
        text_balance.text = balance.toString()

        btn_roll.setOnClickListener(){
            val bet = input_bet.text.toString().toInt()
            if (bet > balance){
                Toast.makeText(this, "Not enough balance!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val odd = 1/(((12-(seekbar_dice.progress.toFloat()+1))/12)*1.05)
            val payout = odd*input_bet.text.toString().toFloat()
            val result = (1..12).random()
            balance = balance - bet
            if (result > seekbar_dice.progress+1){
                balance = balance  + payout.toInt()
                text_balance.text = balance.toString()
                Toast.makeText(this, "You rolled a ${result} and earned  ${payout.toInt()} dollars !", Toast.LENGTH_SHORT).show()
            } else {
                text_balance.text = balance.toString()
                Toast.makeText(this, "You rolled a ${result} and lost ${bet} dollars !", Toast.LENGTH_SHORT).show()
            }
        }

        input_bet.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus){
                if (input_bet.text.toString().isEmpty()){
                    input_bet.setText("0")
                }
            }
            text_payout.text = "%.2f".format(1/(((12-(seekbar_dice.progress.toFloat()+1))/12)*1.05)*input_bet.text.toString().toFloat())
        }

        seekbar_dice.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                text_dicepoint.text = "${progress+1}"
                val winrate = ((12-(progress.toFloat()+1))/12)*100
                val odd = 1/(((12-(progress.toFloat()+1))/12)*1.05)
                text_winrate.text = "%.1f".format(winrate)
                text_odd.text = "%.2f".format(odd)
                text_payout.text = "%.2f".format(odd*input_bet.text.toString().toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })


    }
    override fun onBackPressed() {
        val balance = findViewById<TextView>(R.id.balance).text.toString().toInt()
        intent.putExtra("balance", balance)
        setResult(RESULT_OK, intent)
        finish()
        super.onBackPressed()
    }
}