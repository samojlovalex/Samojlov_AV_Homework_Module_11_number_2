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
import com.example.samojlov_av_homework_module_11_number_2.databinding.ActivityFourthQuestionBinding
import com.google.android.material.snackbar.Snackbar

class FourthQuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFourthQuestionBinding
    private lateinit var toolbarFourthQuestion: androidx.appcompat.widget.Toolbar
    private lateinit var radioGroupFourthRG: RadioGroup
    private lateinit var questionFourthOptionOne: RadioButton
    private lateinit var questionFourthOptionTwo: RadioButton
    private lateinit var questionFourthOptionThree: RadioButton
    private lateinit var questionFourthTV: TextView
    private val questionNumber = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFourthQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_fourth_question)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }
    private fun init() {
        toolbarFourthQuestion = binding.toolbarFourthQuestion
        setSupportActionBar(toolbarFourthQuestion)
        title = getString(R.string.toolbar_title)
        toolbarFourthQuestion.subtitle = getString(R.string.toolbar_subtitle)
        toolbarFourthQuestion.setLogo(R.drawable.toolbar_icon)

        radioGroupFourthRG = binding.radioGroupFourthRG
        questionFourthTV = binding.questionFourthTV
        questionFourthOptionOne = binding.questionFourthOptionOne
        questionFourthOptionTwo = binding.questionFourthOptionTwo
        questionFourthOptionThree = binding.questionFourthOptionThree

        val stroke = intent.getStringExtra("thirdMessage")
        val snackbar = Snackbar.make(
            radioGroupFourthRG,
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
            questionFourthTV,
            questionFourthOptionOne,
            questionFourthOptionTwo,
            questionFourthOptionThree
        )

        radioGroupFourthRG.setOnCheckedChangeListener { _, checkedId ->
            val resultInput = intent.getStringExtra("thirdAnswer")
            val result = resultInput!!.toInt()
            val test = Test(questionNumber)
            val radio: RadioButton = findViewById(checkedId)
            val resultThis = test.evaluation(radio.text.toString())

            val strokeOut = if (resultThis == 100) getString(R.string.messageSnackbarCorrect, radio.text)
            else getString(R.string.messageSnackbarInCorrect, radio.text, test.correctAnswers())

            val resultOut = result + resultThis

            val intent = Intent(this, FifthQuestionActivity::class.java)
            intent.putExtra("fourthAnswer", resultOut.toString())
            intent.putExtra("fourthMessage", strokeOut)
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