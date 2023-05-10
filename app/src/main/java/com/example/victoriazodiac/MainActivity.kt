package com.example.victoriazodiac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var dateText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultTextView = findViewById(R.id.resultTextView)
        dateText = findViewById(R.id.editTextDate)
        dateText.setHint("10-04");
        dateText.doOnTextChanged { text, start, before, count ->
            var date = text?.split("-", ".", "/")
            if (date?.count() == 2) {
                var day = date[0]?.toIntOrNull()
                var month = date[1]?.toIntOrNull()
                if (day != null && month != null) {
                    getZodiac(month, day)
                    return@doOnTextChanged
                }
            }
            resultTextView.text = resources.getString(R.string.unknown_sign_text)
        }
    }

    private fun getZodiac(month: Int, dayOfMonth: Int) {
        val point = when(month * 100 + dayOfMonth) {
            in 100..120, in 1223..1231 -> R.string.capricorn_text
            in 121..219 -> R.string.aquarius_text
            in 220..320 -> R.string.pisces_text
            in 321..420 -> R.string.aries_text
            in 421..522 -> R.string.taurus_text
            in 523..621 -> R.string.gemini_text
            in 622..722 -> R.string.cancer_text
            in 723..822 -> R.string.leo_text
            in 823..922 -> R.string.virgo_text
            in 923..1022 -> R.string.libra_text
            in 1023..1121 -> R.string.scorpio_text
            in 1122..1222 -> R.string.sagittarius_text
            else -> R.string.unknown_sign_text
        }
        resultTextView.text = resources.getString(point)
    }
}