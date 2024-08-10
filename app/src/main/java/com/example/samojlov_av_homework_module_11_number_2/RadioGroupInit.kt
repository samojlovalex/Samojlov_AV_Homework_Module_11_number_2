package com.example.samojlov_av_homework_module_11_number_2

import android.view.View
import android.widget.RadioButton
import android.widget.TextView


class RadioGroupInit {//Класс для инициализации и назначения текста элементов RadioGroup
    companion object {
        fun init(
            questionNumber: Int,
            textView: TextView,
            radioButtonOne: RadioButton,
            radioButtonTwo: RadioButton,
            radioButtonThree: RadioButton
        ) {
//            var result = 0
            val test = Test(questionNumber)

            textView.text = test.setQuestion()
            radioButtonOne.text = test.setQuestOptions(1)
            radioButtonTwo.text = test.setQuestOptions(2)
            radioButtonThree.text = test.setQuestOptions(3)

            val radioButtonClickListener = View.OnClickListener { view ->
                val radioButton = view as RadioButton
                when (radioButton) {
                    radioButtonOne -> ""
                    radioButtonTwo -> ""
                    radioButtonThree -> ""
                }
            }

            radioButtonOne.setOnClickListener(radioButtonClickListener)
            radioButtonTwo.setOnClickListener(radioButtonClickListener)
            radioButtonThree.setOnClickListener(radioButtonClickListener)
        }
    }
}