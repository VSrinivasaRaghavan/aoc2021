package day10

import java.io.File

fun main() {
    val input = File("src/main/resources/day10/input.txt").readLines()
    val incompleteLines = mutableListOf(listOf<Char>())
    val invalidBrackets = mutableListOf<Char>()
    var isLineWithInvalidBracket = false

    input.forEach {
        val stack = mutableListOf<Char>()
        isLineWithInvalidBracket = false
        for (bracket in it) {
            when (bracket) {
                '(', '[', '{', '<' -> stack.add(bracket)
                ')', ']', '}', '>' -> {
                    val requiredCloseBracket = correspondingCloseBracket(stack.last())
                    if (requiredCloseBracket != bracket) {
                        invalidBrackets.add(bracket)
                        isLineWithInvalidBracket = true
                        break
                    }

                    stack.removeLast()
                }
            }
        }
        if (!isLineWithInvalidBracket && stack.isNotEmpty()) {
            incompleteLines.add(stack)
        }
    }
    val scores = mutableListOf<Long>()
    incompleteLines.forEach {
        scores.add(calculateAutoCompleteScoreBasedOnIncompleteBrackets(it))
    }
    print(scores.sorted()[scores.size/2])
}

fun calculateAutoCompleteScoreBasedOnIncompleteBrackets(incompleteBrackets: List<Char>): Long {
    var score = 0L
    incompleteBrackets.reversed().forEach {
        val closingBracket = correspondingCloseBracket(it)
        val points = getPointsForMissingCloseBracket(closingBracket)
        score *= 5
        score += points
    }
    return score
}

private fun getPointsForMissingCloseBracket(closingBracket: Char) =
    when (closingBracket) {
        ')' -> 1
        ']' -> 2
        '}' -> 3
        '>' -> 4
        else -> 0
    }

private fun correspondingCloseBracket(openBracket: Char): Char {
    return when (openBracket) {
        '(' -> ')'
        '[' -> ']'
        '{' -> '}'
        '<' -> '>'
        else -> ' '
    }
}