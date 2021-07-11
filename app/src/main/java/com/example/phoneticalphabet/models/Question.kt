package com.example.phoneticalphabet.models

import java.util.*

data class Question(
    val wrongAnswers: List<String>,
    val correctAnswer: String,
    val letter: String
) {
    val allAnswers = (wrongAnswers + correctAnswer).shuffled()

    fun isAnswerCorrect(answer: String): Boolean {
        return answer == correctAnswer
    }

    companion object {
        fun generateQuestion(
            wrongAnswers: List<String>,
            correctAnswer: List<Pair<String, String>>
        ): List<Question> {
            /**
             * Create empty list of Question where all answers are stored
             * Iterate over each correct answer
             * Filter out by correct starting letter
             * Choose three wrong answers
             * Create a Question
             * Add to list
             */

            val questions: MutableList<Question> = mutableListOf()

            for (alphabet in correctAnswer) {
                val wrongs = wrongAnswers
                    .filter { it.startsWith(alphabet.first.lowercase(Locale.getDefault())) }
                    .shuffled()
                    .subList(0, 3)

                questions.add(Question(wrongs, alphabet.second, alphabet.first))
            }

            return questions.shuffled()
        }
    }
}