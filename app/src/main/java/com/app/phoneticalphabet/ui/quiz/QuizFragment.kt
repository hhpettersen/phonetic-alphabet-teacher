package com.app.phoneticalphabet.ui.quiz

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.phoneticalphabet.R
import com.app.phoneticalphabet.databinding.QuizFragmentBinding
import com.app.phoneticalphabet.models.Question
import com.app.phoneticalphabet.ui.postquiz.PostQuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private var _binding: QuizFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var questions: List<Question>
    private lateinit var buttons: List<Button>
    private var round = 0
    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = QuizFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questions = Question.generateQuestion(QuizWords.words, QuizWords.natoAlphabet)

        setScore()

        populateQuiz(questions[round])
    }

    private fun populateQuiz(question: Question) {

        setQuestionProgress()

        buttons = listOf(
            binding.firstQuestionButton,
            binding.secondQuestionButton,
            binding.thirdQuestionButton,
            binding.fourthQuestionButton
        )

        val answers = question.allAnswers

        // Populating question
        binding.questionText.text = "Letter: ${question.letter}"

        // Populating answer-buttons with the breed-name
        buttons.forEachIndexed { index, view ->
            view.text = answers[index].replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            view.setOnClickListener { button ->
                buttonUi(button, question.isAnswerCorrect(answers[index]))
            }
        }
    }
    private fun buttonUi(view: View, isCorrectAnswer: Boolean) {
        if (isCorrectAnswer) {
            score++
            setScore()
            view.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.correctGreen, null))
        } else {
            view.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.wrongRed, null))
        }
        nextQuestion()
    }

    private fun nextQuestion() {
        // increasing the round
        round++

        // ending quiz when number of questions have been reached
        if(round == 26) {
            endQuiz()
        } else {
            // making UI not clickable
            freezeUi()

            // after 1,5 sec delay UI is clickable, color resets and starting new round
            Handler(Looper.getMainLooper()).postDelayed({
                populateQuiz(questions[round])
                buttons.forEach {
                    it.isClickable = true
                    it.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.green, null))
                }
            }, 1000)
        }
    }

    private fun endQuiz() {
        freezeUi()
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToPostQuizFragment(score))
        }, 1000)
    }

    private fun freezeUi() {
        buttons.forEach {
            it.isClickable = false
        }
    }


    private fun setScore() {
        binding.scoreText.text = "Current score: $score"
    }

    private fun setQuestionProgress() {
        binding.questionProgressText.text = "${round + 1}/26"
    }

}
