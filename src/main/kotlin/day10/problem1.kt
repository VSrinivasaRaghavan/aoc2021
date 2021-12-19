package day10

import java.io.File

fun main() {
    val input = File("src/main/resources/day10/input.txt").readLines()
    val invalidBrackets = mutableListOf<Char>()
    input.forEach {
        val stack = mutableListOf<Char>()
        for (bracket in it) {
            when(bracket){
                '(','[','{','<' -> stack.add(bracket)
                ')',']','}','>' -> {
                    val requiredCloseBracket = correspondingCloseBracket(stack.last())
                    if(requiredCloseBracket != bracket) {
                        invalidBrackets.add(bracket)
                        break
                    }

                    stack.removeLast()
                }
            }
        }
    }

    print(calculateIllegalCharactersOccurrenceScore(invalidBrackets))
}

private fun correspondingCloseBracket(openBracket: Char): Char {
    return when(openBracket){
        '(' -> ')'
        '[' -> ']'
        '{' -> '}'
        '<' -> '>'
        else -> ' '
    }
}

private fun calculateIllegalCharactersOccurrenceScore(invalidBrackets: List<Char>): Int {
    var score = 0
    invalidBrackets.forEach {
        when(it){
            ')' -> score+=3
            ']' -> score+=57
            '}' -> score+=1197
            '>' -> score+=25137
        }
    }

    return score
}