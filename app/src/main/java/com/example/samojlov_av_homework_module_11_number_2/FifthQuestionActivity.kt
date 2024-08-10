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
import com.example.samojlov_av_homework_module_11_number_2.databinding.ActivityFifthQuestionBinding
import com.google.android.material.snackbar.Snackbar

class FifthQuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthQuestionBinding
    private lateinit var toolbarFifthQuestion: androidx.appcompat.widget.Toolbar
    private lateinit var radioGroupFifthRG: RadioGroup
    private lateinit var questionFifthOptionOne: RadioButton
    private lateinit var questionFifthOptionTwo: RadioButton
    private lateinit var questionFifthOptionThree: RadioButton
    private lateinit var questionFifthTV: TextView
    private val questionNumber = 5
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFifthQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_fifth_question)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }
    private fun init() {
        toolbarFifthQuestion = binding.toolbarFifthQuestion
        setSupportActionBar(toolbarFifthQuestion)
        title = getString(R.string.toolbar_title)
        toolbarFifthQuestion.subtitle = getString(R.string.toolbar_subtitle)
        toolbarFifthQuestion.setLogo(R.drawable.toolbar_icon)

        radioGroupFifthRG = binding.radioGroupFifthRG
        questionFifthTV = binding.questionFifthTV
        questionFifthOptionOne = binding.questionFifthOptionOne
        questionFifthOptionTwo = binding.questionFifthOptionTwo
        questionFifthOptionThree = binding.questionFifthOptionThree

        val stroke = intent.getStringExtra("fourthMessage")
        val snackbar = Snackbar.make(
            radioGroupFifthRG,
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
            questionFifthTV,
            questionFifthOptionOne,
            questionFifthOptionTwo,
            questionFifthOptionThree
        )

        radioGroupFifthRG.setOnCheckedChangeListener { _, checkedId ->
            val resultInput = intent.getStringExtra("fourthAnswer")
            val result = resultInput!!.toInt()
            val test = Test(questionNumber)
            val radio: RadioButton = findViewById(checkedId)
            val resultThis = test.evaluation(radio.text.toString())

            val strokeOut = if (resultThis == 100) getString(R.string.messageSnackbarCorrect, radio.text)
            else getString(R.string.messageSnackbarInCorrect, radio.text, test.correctAnswers())

            val resultOut = result + resultThis

            val intent = Intent(this, LastActivity::class.java)
            intent.putExtra("fifthAnswer", resultOut.toString())
            intent.putExtra("fifthMessage", strokeOut)
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