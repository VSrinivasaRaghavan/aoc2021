package day8

import java.io.File

fun main() {
    val input = File("src/main/resources/day8/input.txt").readLines()
    var numberOccurrencesOfUniqueSegments = 0
    input.forEach {
        val outputValues = it.split("|")[1]
        outputValues.split(" ").forEach { output ->
            when (output.length) {
                2, 3, 4, 7 -> ++numberOccurrencesOfUniqueSegments
            }
        }
    }

    println(numberOccurrencesOfUniqueSegments)

}