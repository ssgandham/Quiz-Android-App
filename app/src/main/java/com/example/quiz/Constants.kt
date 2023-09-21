package com.example.quiz

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    fun getQuestions(): ArrayList<Question>{

        var question1 = Question(
            id=1,
            question = "Whats the flag of the country?",
            image = R.drawable.ic_flag_of_india,
            option1 = "India",
            option2 = "Nepal",
            option3 = "Bangladesh",
            option4 = "Uzbekistaan",
            correctAnswer = 1
        )

        var question2 = Question(
            id=1,
            question = "Whats the flag of the country?",
            image = R.drawable.ic_flag_of_argentina,
            option1 = "India",
            option2 = "Nepal",
            option3 = "Arentina",
            option4 = "Uzbekistaan",
            correctAnswer = 3
        )

        var question3 = Question(
            id=1,
            question = "Whats the flag of the country?",
            image = R.drawable.ic_flag_of_belgium,
            option1 = "France",
            option2 = "Belgium",
            option3 = "Japan",
            option4 = "Uzbekistaan",
            correctAnswer = 2
        )

        var question4 = Question(
            id=1,
            question = "Whats the flag of the country?",
            image = R.drawable.ic_flag_of_brazil,
            option1 = "India",
            option2 = "Brazil",
            option3 = "Bangladesh",
            option4 = "Uzbekistaan",
            correctAnswer = 2
        )



        var question5 = Question(
            id=1,
            question = "Whats the flag of the country?",
            image = R.drawable.ic_flag_of_kuwait,
            option1 = "India",
            option2 = "Nepal",
            option3 = "Bangladesh",
            option4 = "Kuwait",
            correctAnswer = 4
        )

        var question6 = Question(
            id=1,
            question = "Whats the flag of the country?",
            image = R.drawable.ic_flag_of_fiji,
            option1 = "India",
            option2 = "Fiji",
            option3 = "Bangladesh",
            option4 = "Uzbekistaan",
            correctAnswer = 2
        )

        var question7 = Question(
            id=1,
            question = "Whats the flag of the country?",
            image = R.drawable.ic_flag_of_germany,
            option1 = "India",
            option2 = "Nepal",
            option3 = "Germany",
            option4 = "Uzbekistaan",
            correctAnswer = 3
        )
        val questionList = ArrayList<Question>()

        val questionsToAdd = listOf(
            question1, question2, question3, question4, question5, question6, question7
        )
        questionList.addAll(0, questionsToAdd)

        return questionList
    }
}