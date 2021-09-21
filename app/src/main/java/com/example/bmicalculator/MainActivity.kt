package com.example.bmicalculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var key_pad: View
    lateinit var ll_bmi_result: LinearLayout
    lateinit var tv_weight_value: TextView
    lateinit var tv_height_value: TextView
    lateinit var tv_bmi_value: TextView

    var number: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        key_pad = findViewById(R.id.key_board)
        ll_bmi_result = findViewById(R.id.ll_bmi_result)
        findViewById<Button>(R.id.b_go).setOnClickListener(this)
        tv_weight_value = findViewById<TextView>(R.id.tv_weight_value)
        tv_weight_value.setOnClickListener(this)
        tv_height_value = findViewById<TextView>(R.id.tv_height_value)
        tv_height_value.setOnClickListener(this)
        findViewById<Button>(R.id.b_one).setOnClickListener(this)
        findViewById<Button>(R.id.b_two).setOnClickListener(this)
        findViewById<Button>(R.id.b_three).setOnClickListener(this)
        findViewById<Button>(R.id.b_four).setOnClickListener(this)
        findViewById<Button>(R.id.b_five).setOnClickListener(this)
        findViewById<Button>(R.id.b_six).setOnClickListener(this)
        findViewById<Button>(R.id.b_seven).setOnClickListener(this)
        findViewById<Button>(R.id.b_eight).setOnClickListener(this)
        findViewById<Button>(R.id.b_nine).setOnClickListener(this)
        findViewById<Button>(R.id.b_zero).setOnClickListener(this)
        findViewById<Button>(R.id.b_dot).setOnClickListener(this)
        findViewById<Button>(R.id.b_ac).setOnClickListener(this)
        tv_bmi_value = findViewById(R.id.tv_bmi_value)

    }

    override fun onClick(view: View?) {
        val view_id = view?.id

        when (view_id) {

            R.id.b_go -> {
                val weight: Double = tv_weight_value.text.toString().toDouble()
                val height: Double = tv_height_value.text.toString().toDouble()
                calculateBmi(height, weight)
                viewBmiResult()
            }

            R.id.tv_weight_value -> {
                viewKeyPad()
                selectValueColor("weight")
                number = ""
            }

            R.id.tv_height_value -> {
                viewKeyPad()
                selectValueColor("height")
                number = ""

            }

            R.id.b_one, R.id.b_two, R.id.b_three, R.id.b_four,
            R.id.b_five, R.id.b_six, R.id.b_seven, R.id.b_eight,
            R.id.b_nine, R.id.b_zero, R.id.b_dot -> {
                val value: String = (view as Button).text.toString()
                if (tv_height_value.currentTextColor == Color.parseColor("#FF5722")) {
                    setValue("height", value)
                } else {
                    setValue("weight", value)
                }
            }

            R.id.b_ac ->{
                number=""
                if (tv_height_value.currentTextColor == Color.parseColor("#FF5722")) {
                    setValue("height", "")
                } else {
                    setValue("weight", "")
                }
            }

            else -> {
                key_pad.visibility = View.GONE
                ll_bmi_result.visibility = View.VISIBLE

            }
        }
    }

    fun viewKeyPad() {
        ll_bmi_result.visibility = View.GONE
        key_pad.visibility = View.VISIBLE

    }

    fun viewBmiResult() {
        key_pad.visibility = View.GONE
        ll_bmi_result.visibility = View.VISIBLE
    }

    fun selectValueColor(height_weight: String) {
        if (height_weight == "height") {
            tv_height_value.setTextColor(Color.parseColor("#FF5722"))
            tv_weight_value.setTextColor(Color.parseColor("#605C5C"))
        } else {
            tv_weight_value.setTextColor(Color.parseColor("#FF5722"))
            tv_height_value.setTextColor(Color.parseColor("#605C5C"))
        }
    }

    fun setValue(height_weight: String, value: String) {

        if (height_weight === "height") {
            number += value
            tv_height_value.text = number
        } else {

            number += value
            tv_weight_value.text = number
        }
    }

    fun calculateBmi(height: Double, weight: Double) {
        val bmi: Double = weight / ((height / 100) * (height / 100))
        tv_bmi_value.text = String.format("%.1f", bmi)

    }
}