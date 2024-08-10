package com.example.samojlov_av_homework_module_11_number_2

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.samojlov_av_homework_module_11_number_2.databinding.ActivityLastBinding
import com.google.android.material.snackbar.Snackbar

class LastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLastBinding
    private lateinit var toolbarLast: androidx.appcompat.widget.Toolbar
    private lateinit var textViewResultTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLastBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_last)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }

    private fun init() {
        toolbarLast = binding.toolbarLast
        setSupportActionBar(toolbarLast)
        title = getString(R.string.toolbar_title)
        toolbarLast.subtitle = getString(R.string.toolbar_subtitle)
        toolbarLast.setLogo(R.drawable.toolbar_icon)

        textViewResultTV = binding.textViewResultTV

        val stroke = intent.getStringExtra("fifthMessage")
        val snackbar = Snackbar.make(
            textViewResultTV,
            stroke!!,
            Snackbar.LENGTH_LONG
        )
        val snackbarView =
            snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        snackbarView.setMaxLines(10)
        snackbarView.setCompoundDrawablesRelativeWithIntrinsicBounds(
            R.drawable.toolbar_icon,
            0,
            0,
            0
        )
        snackbar.show()

        val resultInput = intent.getStringExtra("fifthAnswer")
        val result = resultInput!!.toInt()
        val characteristicsOfTheResult: String
        when (result) {
            in 0..100 -> {
                characteristicsOfTheResult = getString(R.string.scoreOne, result.toString())
                textViewResultTV.setTextColor(Color.parseColor("#ff0000"))
                textViewResultTV.text = characteristicsOfTheResult
            }

            200 -> {
                characteristicsOfTheResult = getString(R.string.scoreTwo, result.toString())
                textViewResultTV.setTextColor(Color.parseColor("#ffa500"))
                textViewResultTV.text = characteristicsOfTheResult
            }

            300 -> {
                characteristicsOfTheResult = getString(R.string.soreThree, result.toString())
                textViewResultTV.setTextColor(Color.parseColor("#ffff00"))
                textViewResultTV.text = characteristicsOfTheResult
            }

            400 -> {
                characteristicsOfTheResult = getString(R.string.scoreFour, result.toString())
                textViewResultTV.setTextColor(Color.parseColor("#99ff99"))
                textViewResultTV.text = characteristicsOfTheResult
            }

            500 -> {
                characteristicsOfTheResult = getString(R.string.scoreFive, result.toString())
                textViewResultTV.setTextColor(Color.parseColor("#008000"))
                textViewResultTV.text = characteristicsOfTheResult
            }
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