package com.example.devinetteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.devinetteapp.R
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var low: Button? = null
    var heigh: Button? = null
    var verif: Button? = null
    var Npartie: Button? = null
    var radioGroup: RadioGroup? = null
    var textView: TextView? = null
    var result: TextView? = null
    var history: TextView? = null
    var timer: TextView? = null
    var editText: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        radioGroup = findViewById(R.id.radioGroup)
        textView = findViewById(R.id.textView)
        low = findViewById(R.id.low)
        editText = findViewById(R.id.editText)
        verif = findViewById(R.id.verif)
        history = findViewById(R.id.history)
        result = findViewById(R.id.resultat)
        Npartie = findViewById(R.id.Npartie)
        heigh = findViewById(R.id.heigh)
        timer = findViewById(R.id.timer)
        val i = IntArray(1)
        val nombre = IntArray(1)
        val score = IntArray(1)
        val counter = IntArray(1)
        low.setOnClickListener(View.OnClickListener {
            radioGroup.setVisibility(View.GONE)
            textView.setText("Choose a number in [1..1000]")
            editText.setVisibility(View.VISIBLE)
            verif.setVisibility(View.VISIBLE)
            result.setVisibility(View.VISIBLE)
            history.setVisibility(View.VISIBLE)
            nombre[0] = 1 + (Math.random() * 1000).toInt()
            result.setText(nombre[0].toString())
            i[0] = 0
            score[0] = 100
        })
        heigh.setOnClickListener(View.OnClickListener {
            radioGroup.setVisibility(View.GONE)
            textView.setText("Choose a number in [1..1000]")
            editText.setVisibility(View.VISIBLE)
            verif.setVisibility(View.VISIBLE)
            result.setVisibility(View.VISIBLE)
            nombre[0] = 1 + (Math.random() * 1000).toInt()
            result.setText(nombre[0].toString())
            i[0] = 0
            score[0] = 100
            timer.setVisibility(View.VISIBLE)
            counter[0] = 0
            object : CountDownTimer(100000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    if (score[0] <= 0) {
                        cancel()
                        i[0]++
                        result.setText(
                            """${nombre[0]} est le nombre cherché !
 tu fais ${i[0]} tentatives 
 votre score: ${score[0]} / 100"""
                        )
                        textView.setText("C'est Perdu ! !")
                        editText.setVisibility(View.GONE)
                        verif.setVisibility(View.GONE)
                        Npartie.setVisibility(View.VISIBLE)
                    }
                    if (textView.getText().toString() === "C'est gagnant !") cancel()
                    timer.setText(counter[0].toString())
                    counter[0]++
                    score[0]--
                }

                override fun onFinish() {
                    result.setText("Temps fini")
                    textView.setText("Perdu  !")
                    timer.setVisibility(View.GONE)
                    editText.setVisibility(View.GONE)
                    verif.setVisibility(View.GONE)
                    Npartie.setVisibility(View.VISIBLE)
                }
            }.start()
        })
        Npartie.setOnClickListener(View.OnClickListener {
            textView.setText("Choose a level!")
            history.setText("Historique :")
            history.setVisibility(View.GONE)
            result.setVisibility(View.GONE)
            radioGroup.setVisibility(View.VISIBLE)
            Npartie.setVisibility(View.GONE)
        })
        verif.setOnClickListener(View.OnClickListener {
            try {
                val nbrEditText = editText.getText().toString().toInt()
                if (nbrEditText < 1 || nbrEditText > 1000) result.setText("intervalle incorrecte") else {
                    if (nbrEditText < nombre[0]) {
                        i[0]++
                        score[0]--
                        result.setText("le nombre chercher est superieur à $nbrEditText")
                    } else {
                        if (nbrEditText > nombre[0]) {
                            i[0]++
                            score[0]--
                            result.setText("le nombre chercher est inferieur à $nbrEditText")
                        } else {
                            i[0]++
                            result.setText(
                                """${nombre[0]} est le nombre cherché !
 tu fais ${i[0]} tentatives 
 votre score: ${score[0]} / 100"""
                            )
                            textView.setText("C'est gagnant !")
                            timer.setVisibility(View.GONE)
                            editText.setVisibility(View.GONE)
                            verif.setVisibility(View.GONE)
                            Npartie.setVisibility(View.VISIBLE)
                        }
                    }
                    history.setText(
                        """${history.getText()}
 tentative ${i[0]}: $nbrEditText"""
                    )
                }
                editText.setText("")
            } catch (e: Exception) {
                Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}