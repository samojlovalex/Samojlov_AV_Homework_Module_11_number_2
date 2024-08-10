package com.example.samojlov_av_homework_module_11_number_2

class Test(val questionNumber: Int) {
    private val listQuestion = listOf(//Свисок с вопросами
        "1. Кто составлял большую часть декабристского восстания в 1825 году?",
        "2. В каком году отменили крепостное право в России?",
        "3. Кто стал последним императором Российской империи? Подсказка - итоги его правления были печальными, а судьба всех членов семьи - трагичной.",
        "4. Как звали жену Ленина, которая всю свою жизнь была в тени мужа?",
        "5. Какое государство установило иго на Руси продолжительностью почти в 200 лет?"
    )
    private val listAnswerOptionsOneQuestion = listOf(//Варианты ответов
        "Крепостные крестьяне",
        "Священники",
        "Дворяне"
    )
    private val listAnswerOptionsTwoQuestion = listOf(
        "1861",
        "1678",
        "1917"
    )
    private val listAnswerOptionsThreeQuestion = listOf(
        "Александр II",
        "Петр III",
        "Николай II"
    )
    private val listAnswerOptionsFourQuestion = listOf(
        "Тамара",
        "Елена",
        "Надежда"
    )
    private val listAnswerOptionsFiveQuestion = listOf(
        "Византийская империя",
        "Золотая Орда",
        "Османская империя"
    )
    private val listCorrectAnswers = listOf(//Список с индексами правильных ответов
        2,
        0,
        2,
        2,
        1
    )

    fun setQuestion(): String { //Функция выбора текущего вопроса
        val quest = listQuestion[questionNumber - 1]
        return quest
    }

    private fun setQuestList(): List<String> { //Функция выбора вариантов ответов
        val listEmpty: List<String> = listOf()
        val questList = when (questionNumber) {
            1 -> listAnswerOptionsOneQuestion
            2 -> listAnswerOptionsTwoQuestion
            3 -> listAnswerOptionsThreeQuestion
            4 -> listAnswerOptionsFourQuestion
            5 -> listAnswerOptionsFiveQuestion
            else -> listEmpty
        }
        return questList
    }

    fun setQuestOptions(numberQuestionButton: Int): String {
        //Функция назначения вопроса соответстующей кнопке
        val list = setQuestList()
        val option = list[numberQuestionButton - 1]
        return option
    }

    fun correctAnswers(): String {
        //Функция вывода правильного ответа на текущий вопрос
        val stroke = setQuestList()[listCorrectAnswers[questionNumber - 1]]
        return stroke
    }

    fun evaluation(option: String): Int {
        //Функция оценивающая выбор ответа
        var evaluation = 0
        val stroke = correctAnswers()
        if (option.equals(stroke)) evaluation = 100
        return evaluation
    }
}