package com.example.samojlov_av_homework_module_11_number_2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.samojlov_av_homework_module_11_number_2.databinding.ActivitySecondQuestionBinding
import com.example.samojlov_av_homework_module_11_number_2.databinding.ActivityThirdQuestionBinding
import com.google.android.material.snackbar.Snackbar

class ThirdQuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdQuestionBinding
    private lateinit var toolbarThirdQuestion: androidx.appcompat.widget.Toolbar
    private lateinit var radioGroupThirdRG: RadioGroup
    private lateinit var questionThirdOptionOne: RadioButton
    private lateinit var questionThirdOptionTwo: RadioButton
    private lateinit var questionThirdOptionThree: RadioButton
    private lateinit var questionThirdTV: TextView
    private val questionNumber = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_third_question)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }
    private fun init() {
        toolbarThirdQuestion = binding.toolbarThirdQuestion
        setSupportActionBar(toolbarThirdQuestion)
        title = getString(R.string.toolbar_title)
        toolbarThirdQuestion.subtitle = getString(R.string.toolbar_subtitle)
        toolbarThirdQuestion.setLogo(R.drawable.toolbar_icon)

        radioGroupThirdRG = binding.radioGroupThirdRG
        questionThirdTV = binding.questionThirdTV
        questionThirdOptionOne = binding.questionThirdOptionOne
        questionThirdOptionTwo = binding.questionThirdOptionTwo
        questionThirdOptionThree = binding.questionThirdOptionThree

        val stroke = intent.getStringExtra("secondMessage")
        val snackbar = Snackbar.make(
            radioGroupThirdRG,
            stroke!!,
            Snackbar.LENGTH_LONG
        )
        val snackbarView =
            snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        snackbarView.setMaxLines(10)
        snackbarView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.toolbar_icon, 0,0,0)
        snackbar.show()

        RadioGroupInit.init(
            questionNumber,
            questionThirdTV,
            questionThirdOptionOne,
            questionThirdOptionTwo,
            questionThirdOptionThree
        )

        radioGroupThirdRG.setOnCheckedChangeListener { _, checkedId ->
            val resultInput = intent.getStringExtra("secondAnswer")
            var result = resultInput!!.toInt()
            val test = Test(questionNumber)
            val radio: RadioButton = findViewById(checkedId)
            val resultThis = test.evaluation(radio.text.toString())

            val strokeOut = if (resultThis == 100) getString(R.string.messageSnackbarCorrect, radio.text)
            else getString(R.string.messageSnackbarInCorrect, radio.text, test.correctAnswers())

            val resultOut = result + resultThis

            val intent = Intent(this, FourthQuestionActivity::class.java)
            intent.putExtra("thirdAnswer", resultOut.toString())
            intent.putExtra("thirdMessage", strokeOut)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("SetTextI18n", "ShowToast")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.toast_exit),
                    Toast.LENGTH_LONG
                ).show()
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}