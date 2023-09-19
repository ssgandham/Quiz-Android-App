package com.example.quiz

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.media.tv.TvView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
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
    var currentQuestionNumber: Int? = null
    var size: Int? = null
    var btSubmit: Button? = null

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

        questionList = Constants.getQuestions()
        currentQuestionNumber = 0
        size = questionList?.size

        setQuestion(currentQuestionNumber!!)
        setOptionsView()
    }

    private fun setQuestion(
        currentQuestionNumber: Int
    ) {
        val question: Question = questionList!![currentQuestionNumber]
        progressBar?.progress = currentQuestionNumber

        questionProgress?.text = "$currentQuestionNumber/$size"

        questionText?.text = question.question

        optionOne?.text = question.option1
        optionTwo?.text = question.option2
        optionThree?.text = question.option3
        optionFour?.text = question.option4

        imgFlg?.setImageResource(question.image)

        if (currentQuestionNumber == size){
            btSubmit?.text = "SUBMIT"
        }else{
            btSubmit?.text = "FINISH"
        }
    }

    private fun setOptionsView(){
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

        for(option in listOptions){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuestionsActivity,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(textView: TextView,
                                   selectedOptionNum:Int
    ){
        setOptionsView()
        textView.setTextColor(Color.RED)
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(this@QuestionsActivity,
            R.drawable.default_option_border_bg)
    }

    override fun onClick(view: View?) {
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
        }
    }
}