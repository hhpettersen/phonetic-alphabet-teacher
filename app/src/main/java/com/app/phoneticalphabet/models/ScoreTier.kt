package com.app.phoneticalphabet.models

import com.app.phoneticalphabet.R

enum class ScoreTier {
    BRONZE, SILVER, GOLD;

    companion object {
        fun getScoreTier(score: Int): ScoreTier {
            return when {
                score < 15 -> BRONZE
                score < 23 -> SILVER
                else -> GOLD
            }
        }

        fun getTierTint(tier: ScoreTier): Int {
            return when (tier) {
                BRONZE -> R.color.bronze
                SILVER -> R.color.silver
                GOLD -> R.color.gold
            }
        }

        fun getTierText(tier: ScoreTier): String {
            return when (tier) {
                BRONZE -> "BRONZE"
                SILVER -> "SILVER"
                GOLD -> "GOLD"
            }
        }
    }
}