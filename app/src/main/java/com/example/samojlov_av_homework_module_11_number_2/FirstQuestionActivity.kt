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
import com.example.samojlov_av_homework_module_11_number_2.databinding.ActivityFirstQuestionBinding


class FirstQuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstQuestionBinding
    private lateinit var toolbarFirstQuestion: androidx.appcompat.widget.Toolbar
    private lateinit var radioGroupOneRG: RadioGroup
    private lateinit var questionOneOptionOne: RadioButton
    private lateinit var questionOneOptionTwo: RadioButton
    private lateinit var questionOneOptionThree: RadioButton
    private lateinit var questionOneTV: TextView
    private val questionNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirstQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_first_question)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }

    private fun init() {
        toolbarFirstQuestion = binding.toolbarFirstQuestion
        setSupportActionBar(toolbarFirstQuestion)
        title = getString(R.string.toolbar_title)
        toolbarFirstQuestion.subtitle = getString(R.string.toolbar_subtitle)
        toolbarFirstQuestion.setLogo(R.drawable.toolbar_icon)

        radioGroupOneRG = binding.radioGroupOneRG
        questionOneTV = binding.questionOneTV
        questionOneOptionOne = binding.questionOneOptionOne
        questionOneOptionTwo = binding.questionOneOptionTwo
        questionOneOptionThree = binding.questionOneOptionThree

        RadioGroupInit.init(
            questionNumber,
            questionOneTV,
            questionOneOptionOne,
            questionOneOptionTwo,
            questionOneOptionThree
        )

        radioGroupOneRG.setOnCheckedChangeListener { _, checkedId ->
            val test = Test(questionNumber)
            val radio: RadioButton = findViewById(checkedId)
            val result = test.evaluation(radio.text.toString())

            val strokeOut = if (result == 100) getString(R.string.messageSnackbarCorrect, radio.text)
            else getString(R.string.messageSnackbarInCorrect, radio.text, test.correctAnswers())

            val intent = Intent(this, SecondQuestionActivity::class.java)
            intent.putExtra("firstAnswer", result.toString())
            intent.putExtra("firstMessage", strokeOut)
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