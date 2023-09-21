package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat


class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    var progressBar: ProgressBar? = null
    var questionProgress: TextView? = null
    var questionText:TextView? = null
    var imgFlg: ImageView? = null
    var optionOne: TextView? = null
    var optionTwo: TextView? = null
    var optionThree: TextView? = null
    var optionFour: TextView? = null

    var questionList: ArrayList<Question>? = null
    var currentQuestionNumber: Int = 0
    var selectedOption: Int? = null
    var size: Int? = null
    var btSubmit: Button? = null
    private var mCorrectAnswers: Int = 0
    var correctAnswer: Int?= null

    var userName:String?= null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_activity)

        progressBar = findViewById(R.id.progressBar)
        questionProgress = findViewById(R.id.questionProgress)
        questionText = findViewById(R.id.questionText)
        imgFlg = findViewById(R.id.imgFlg)

        optionOne = findViewById(R.id.question_option_one)
        optionTwo = findViewById(R.id.question_option_two)
        optionThree = findViewById(R.id.question_option_three)
        optionFour = findViewById(R.id.question_option_four)

        btSubmit = findViewById(R.id.btSubmit)

        optionOne?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)
        btSubmit?.setOnClickListener(this)

        questionList = Constants.getQuestions()
        size = questionList?.size
        userName = intent.getStringExtra(Constants.USER_NAME)
        setQuestion()
        resetDefaultOptions()
    }

    private fun setQuestion() {

        val question: Question = questionList!![currentQuestionNumber]
        resetDefaultOptions()


        if (currentQuestionNumber != size){
            btSubmit?.text = "SUBMIT"
        }else{
            btSubmit?.text = "FINISH"
        }

        progressBar?.progress = currentQuestionNumber

        questionProgress?.text = "${currentQuestionNumber+1}/$size"

        questionText?.text = question.question

        optionOne?.text = question.option1
        optionTwo?.text = question.option2
        optionThree?.text = question.option3
        optionFour?.text = question.option4

        imgFlg?.setImageResource(question.image)

        correctAnswer = question.correctAnswer

        Log.i("Question", currentQuestionNumber.toString())
        Log.i("optionOne?.text", optionOne?.text.toString())
        Log.i("question.option1", question.option1.toString())

        Log.i("optionTwo?.text",optionTwo?.text.toString())
        Log.i("question.option2",question.option2.toString())

        Log.i("optionThree?.text", optionThree?.text.toString())
        Log.i("question.option3", question.option3.toString())

        Log.i("optionFour?.text", optionFour?.text.toString())
        Log.i("question.option4", question.option4.toString())


    }

    private fun resetDefaultOptions(){
        val listOptions = ArrayList<TextView>()

        optionOne?.let {
            listOptions.add(0, it)
        }

        optionTwo?.let {
            listOptions.add(0, it)
        }

        optionThree?.let {
            listOptions.add(0, it)
        }

        optionFour?.let {
            listOptions.add(0, it)
        }

        for (option in listOptions) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(textView: TextView,
                                   selectedOptionNum:Int
    ){
        resetDefaultOptions()
        selectedOption = selectedOptionNum
        Log.i(selectedOption.toString(), "selectedOptionView: $selectedOption")
        textView.setTextColor(
            Color.parseColor("#363A43")
        )
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun answerView(answer: Int?, drawableView: Int) {

        when (answer) {

            1 -> {
                optionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                optionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                optionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                optionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }

    override fun onClick(view: View?) {
        resetDefaultOptions()
        when(view?.id){
            R.id.question_option_one -> {
                optionOne?.let {
                    selectedOptionView(it, 1)
                }
            }

            R.id.question_option_two -> {
                optionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }

            R.id.question_option_three -> {
                optionThree?.let {
                    selectedOptionView(it, 3)
                }
            }

            R.id.question_option_four -> {
                optionFour?.let {
                    selectedOptionView(it, 4)
                }
            }

            R.id.btSubmit -> {

                if (selectedOption == 0){
                    if (currentQuestionNumber < size!!) {
                        setQuestion()
                    }else{
                        val intent =
                            Intent(this, Result::class.java)
                        intent.putExtra(Constants.USER_NAME, userName)
                        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, questionList?.size)
                        startActivity(intent)
                        finish()
                    }
                }
                if (currentQuestionNumber < size!! && selectedOption!=0) {

                    val question: Question = questionList!![currentQuestionNumber]

                    Log.i("Answer", "option"+question.correctAnswer +" : " +currentQuestionNumber)

                    Log.i("ValidateAAnswer", "$selectedOption : ${correctAnswer}")
                    if (selectedOption != correctAnswer) {
                        answerView(selectedOption, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                        answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                    }

                    if (currentQuestionNumber == size) {
                        btSubmit?.text = "FINISH"
                    } else {
                        btSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    currentQuestionNumber++
                    selectedOption = 0
                }else{

                }
            }


        }
    }
}