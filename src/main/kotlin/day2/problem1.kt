package day2

import java.io.File

fun main() {
    val inputList = File("src/main/resources/day2/input.txt").readLines()
    var horizontal = 0
    var depth = 0

    inputList.forEach {
        val command = it.split(" ")
        val direction = command[0]
        val distance = command[1].toInt()

        when (direction) {
            "up" -> depth -= distance
            "down" -> depth += distance
            "forward" -> horizontal += distance
        }
    }

    print(horizontal * depth)
}